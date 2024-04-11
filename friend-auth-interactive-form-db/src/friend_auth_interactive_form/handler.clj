(ns friend-auth-interactive-form.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [friend-auth-interactive-form.routes.home :refer [page]]))

(defn init []
  (println "friend-auth-interactive-form is starting"))

(defn destroy []
  (println "friend-auth-interactive-form is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes page app-routes)
      (handler/site)
      (wrap-base-url)))


