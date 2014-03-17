(ns picture-gallery.handler
  (:require [compojure.core :refer [defroutes]]
            [compojure.route :as route]
            [picture-gallery.routes.home :refer [home-routes]]
            [picture-gallery.routes.auth :refer [auth-routes]]
            [noir.util.middleware :as noir-middleware]
            [picture-gallery.routes.upload :refer [upload-routes]]
            [noir.session :as session]))

(defn init []
    (println "picture-gallery is starting"))

(defn destroy []
    (println "picture-gallery is shutting down"))

; the underscore _ simply indicates that the argument will be ignored
(defn user-page [_]
    (session/get :user))

(defroutes app-routes
    (route/resources "/")
    (route/not-found "Not Found"))

(def app
    (noir-middleware/app-handler
        [auth-routes home-routes upload-routes app-routes]
        ; access rules
        :access-rules [user-page]))
        ;(handler/site)
        ;(wrap-base-url)))


