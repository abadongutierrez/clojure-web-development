(ns picture-gallery.routes.auth
    (:require [hiccup.form :refer :all]
              [compojure.core :refer :all]
              [picture-gallery.routes.home :refer :all]
              [picture-gallery.views.layout :as layout]
              [noir.session :as session]
              [noir.response :as resp]
              [noir.validation :as validd]
              [picture-gallery.models.db :as db]
              [noir.util.crypt :as crypt]))

(defn error-item [[error]]
    [:div.error error])

(defn valid? [id pass pass1]
    (validd/rule (validd/has-value? id)
                [:id "user id is required"])
    (validd/rule (validd/min-length? pass 5)
                [:pass "password must be at least 5 characters"])
    (validd/rule (= pass pass1)
                [:pass "entered passwords do not match"])
    (not (validd/errors? :id :pass :pass1)))

(defn control [id label field]
    (list
        (validd/on-error id error-item)
        label field
        [:br]))

(defn registration-page [& [id]]
    (layout/base
        (form-to [:post "/register"]
                 (control :id
                    (label "user-id" "user id")
                    (text-field {:tabindex 1} "id" id))
                 (control :pass
                    (label "pass" "password")
                    (password-field {:tabindex 2} "pass"))
                 (control :pass1
                    (label "pass1" "retype password")
                    (password-field  {:tabindex 3} "pass1"))
                 (submit-button {:tabindex 4} "create account"))))

(defn format-error [id ex]
    (cond
        (and (instance? org.postgresql.util.PSQLException ex)
             (= 0 (.getErrorCode ex)))
        (str "The user with id " id " already exists!")
        :else
        "An error has ocurred while processing the request"))

(defn handle-registration [id pass pass1]
    (if (valid? id pass pass1)
        (try
            ; insert user in database
            (db/create-user {:id id :password (crypt/encrypt pass)})
            ; store user id in session
            (session/put! :user id)
            (resp/redirect "/")
            (catch Exception ex
                (validd/rule false [:id (format-error id ex)])
                (registration-page)))
        (registration-page id)))

(defn handle-login [id pass]
    (let [user (db/get-user id)]
        (if (and user (crypt/compare pass (:password user)))
            (session/put! :user id)))
    (resp/redirect "/"))

(defn handle-logout []
    (session/clear!)
    (resp/redirect "/"))

(defroutes auth-routes
    (GET "/register" []
        (registration-page))
    (POST "/register" [id pass pass1]
        (handle-registration id pass pass1))
    (POST "/login" [id pass]
        (handle-login id pass))
    (GET "/logout" []
        (handle-logout)))