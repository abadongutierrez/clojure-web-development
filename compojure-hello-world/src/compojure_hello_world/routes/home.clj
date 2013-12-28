(ns compojure-hello-world.routes.home
  (:require [compojure.core :refer :all]
            [compojure-hello-world.views.layout :as layout]
            [hiccup.form :refer [form-to text-field label submit-button]]
            [ring.util.response :refer [redirect]])
  (:import [java.net URLEncoder]))

; renders the form to ask for the user's name
(defn home []
  (layout/common [:div
  	[:h1 "Hello World!"]
  	[:p "Now that you are here please enter your name:"]
  	(form-to [:post "/name"]
        [:div#name (label "name" "Name") (text-field "name")]
        [:div#last-name (label "last-name" "Last name") (text-field "last-name")]
        [:div (submit-button "Go")])]))

; receives the name and last-name and redirect
(defn enter-name [name last-name]
    (redirect
        (str "/name?name=" 
            (URLEncoder/encode name "UTF-8") "&last-name=" (URLEncoder/encode last-name "UTF-8"))))

; shows the actual greeting
(defn show-greeting [name last-name]
	(layout/common [:div
		(str "Hello! " name " " last-name)]))

(defroutes home-routes
    (GET "/" [] (home))
    (context "/name" []
        (POST "/" [name last-name] (enter-name name last-name))
        (GET "/" [name last-name] (show-greeting name last-name))))