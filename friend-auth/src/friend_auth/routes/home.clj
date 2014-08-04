(ns friend-auth.routes.home
  (:require [compojure.core :refer :all]
            [friend-auth.views.layout :as layout]
            [ring.util.response :as resp]
            [cemerick.friend :as friend]
            [cemerick.friend.credentials :refer (hash-bcrypt)]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])))

(def users (atom {"friend" {:username "friend"
                            :password (hash-bcrypt "clojure")
                            :pin "1234" ;; only used by multi-factor
                            :roles #{::user}}
                  "friend-admin" {:username "friend-admin"
                                  :password (hash-bcrypt "clojure")
                                  :pin "1234" ;; only used by multi-factor
                                  :roles #{::admin}}}))

(derive ::admin ::user)

(defn home []
  (layout/common [:h1 "Hello World!"]))

(defroutes home-routes
  (GET "/" [] (home)))

(defroutes sec-home-routes
  (GET "/" [] (home))
  (GET "/requires-authentication" req
    (friend/authenticated (str "You have successfully authenticated as " (friend/current-authentication))))
  (GET "/logout" req
    (friend/logout* (resp/redirect (str (:context req) "/")))))

(def secured-app
    (friend/authenticate
        sec-home-routes
        {
            :allow-anon? false
            :unauthenticated-handler #(workflows/http-basic-deny "Friend demo" %)
            :workflows [(workflows/http-basic
                            :credential-fn #(creds/bcrypt-credential-fn @users %)
                            :realm "Friend demo")]
        }))
