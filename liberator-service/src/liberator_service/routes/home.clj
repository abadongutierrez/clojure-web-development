(ns liberator-service.routes.home
  (:require [compojure.core :refer :all]
            [liberator.core :refer [defresource resource]]
            [cheshire.core :refer [generate-string]]
            [noir.io :as io]
            [clojure.java.io :refer [file]]))

; The resource will check whether the file exists and when it was last modified.
; the file will be served only if it exists and we made changes to it since it was last requested
(defresource home
    :available-media-types ["text/html"]
    :exists?
        (fn [context]
            [(io/get-resource "/home.html")
                {::file (file (str (io/resource-path) "/home.html"))}])
    :handle-ok
        (fn [{{{resource :resource} :route-params} :request}]
            (clojure.java.io/input-stream (io/get-resource "/home.html")))
    :last-modified
        (fn [{{{resource :resource} :route-params} :request}]
            (.lastModified (file (str (io/resource-path) "/home.html")))))

; atom to hold the list of users
(def users (atom ["John" "Jane"]))

; resource to return the users as json
(defresource get-users
    :allowed-methods [:get] ; only GET
    :handle-ok (fn [_] (generate-string @users))
    :available-media-types ["application/json"])

; resource to add a user
(defresource add-user
    :allowed-methods [:post] ; only POST
    :malformed? ; prevent handling blank users
        (fn [context]
            (let [params (get-in context [:request :form-params])] 
                (empty? (get params "user"))))
    :handle-malformed ; (if malformed? decision returns true :handle-malformed is executed)
        "user name cannot be empty!"
    :post! ; use the post! action
        (fn [context]             
            (let [params (get-in context [:request :form-params])]
                (swap! users conj (get params "user"))))
    :handle-created
        (fn [_] (generate-string @users))
    :available-media-types ["application/json"])

(defroutes home-routes
    (ANY "/" request home)
    (context "/users" [_]
        ; TODO a resource to handle all methods
        (POST "/" request add-user)
        (GET "/" request get-users)))
