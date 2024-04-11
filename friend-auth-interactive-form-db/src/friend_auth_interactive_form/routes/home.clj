(ns friend-auth-interactive-form.routes.home
  (:require [compojure.core :refer :all]
            [friend-auth-interactive-form.views.layout :as layout]
            [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])
            [cemerick.friend.credentials :refer (hash-bcrypt)]
            [ring.util.response :as resp]
            [friend-auth-interactive-form.models.user :as user]))

(def users (atom {"friend" {:username "friend"
                            :password (hash-bcrypt "clojure")
                            :pin "1234" ;; only used by multi-factor
                            :roles #{::user}}
                  "friend-admin" {:username "friend-admin"
                                  :password (hash-bcrypt "clojure")
                                  :pin "1234" ;; only used by multi-factor
                                  :roles #{::admin}}}))

(derive ::admin ::user)

(defn home [req]
  (layout/render-page "index" {
    :message "Friend Interactive Form Example"
    :logged-message (if-let [identity (friend/identity req)]
                        (apply str "Logged in, with these roles: "
                            (interpose ", " (-> identity friend/current-authentication :roles)))
                        "anonymous user")
    :logged-in (not (nil? (friend/identity req)))
    }))

(defn login [req]
  (layout/render-page "login" {:message "Please login:"}))

(defroutes home-routes
  (GET "/" req (home req))
  (GET "/login" req (login req))
  (GET "/logout" req
    (friend/logout* (resp/redirect (str (:context req) "/"))))
  (GET "/requires-authentication" req
    (friend/authenticated (layout/render-page "message" {:message "Thanks for authenticating!"})))
  (GET "/role-user" req
    (friend/authorize #{"user"} (layout/render-page "message" {:message "You're a user!"})))
  (GET "/role-admin" req
    (friend/authorize #{"admin"} (layout/render-page "message" {:message "You're an admin!"}))))

(defn verify-credentials [creds]
    (let [valid-user (creds/bcrypt-credential-fn @users creds)]
        (println (str "Verifying Credentials: " creds))
        (println (str "Valid user: " valid-user))
        valid-user))

(defn verify-db-credentials [creds]
    (let [user-db (user/find-users-with-username (:username creds))]
        (println (str "user-db:" user-db))
        (if (nil? user-db)
            nil
            (creds/bcrypt-credential-fn {(:username creds) user-db} creds))))

(def page (friend/authenticate
              home-routes
              {:allow-anon? true
               :login-uri "/login"
               :default-landing-uri "/"
               :unauthorized-handler #(-> (layout/render-page "message" {:message (str "You do not have sufficient privileges to access " (:uri %))})
                                        resp/response
                                        (resp/status 401))
               :credential-fn #(verify-db-credentials %)
               :workflows [(workflows/interactive-form)]}))
