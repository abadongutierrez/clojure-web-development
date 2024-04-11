(ns friend-auth-interactive-form.views.layout
  (:require [hiccup.page :refer [html5 include-css]]
            [clostache.parser :refer [render-resource]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to friend-auth-interactive-form"]
     (include-css "/css/screen.css")]
    [:body body]))

(defn render-page [template data]
    (render-resource (str "templates/" template ".mustache") data))
