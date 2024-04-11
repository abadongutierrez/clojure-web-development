(ns friend-auth.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [friend-auth.routes.home :refer [home-routes sec-home-routes secured-app]]))

(defn init []
  (println "friend-auth is starting"))

(defn destroy []
  (println "friend-auth is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> 
      ;; app-routes has to be the last argument
      (routes secured-app app-routes)
      (handler/site)
      (wrap-base-url)))


