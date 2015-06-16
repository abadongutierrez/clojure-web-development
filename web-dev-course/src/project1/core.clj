(ns project1.core
    (:require [project1.handlers :as handlers]
              [ring.middleware.resource :as resource]
              [ring.middleware.file-info :as file-info]
              [clojure.string]))

(defn wrap-case-middleware [handler]
    (fn [request]
        (let [request (update-in request [:uri] clojure.string/lower-case)
              response (handler request)]
            (if (string? (:body response))
                (update-in response [:body] clojure.string/capitalize)
                response))))

(defn wrap-exception-middleware [handler]
    (fn [request]
        (try
            (handler request)
            (catch Throwable e 
                {:status 505
                 :body (str "Error (with Middleware): " (.getMessage e) "\n\n" 
                    (apply str (interpose "\n" (.getStackTrace e))))}))))

(defn not-found-middleware [handler]
    (fn [request]
        (or (handler request)
             {:status 404 
              :body (str "Not found (with middleware!): " (:uri request))})))

(defn simple-log-middleware [handler]
    (fn [request]
        (println (str "Requested Uri: " (:uri request)))
        (handler request)))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn example-handler []
    {:body "Hello Clojure"})

(defn on-init []
    (println "Initializing example webapp"))

(defn on-destroy []
    (println "Destroying example webapp"))

(defn test1-handler [request]
    {:body "test1"})

(defn test2-handler [request]
    {:status 301 :headers {"Location" "http://www.nearsoft.com"}})

(defn router-handler [request]
    (condp = (:uri request)
        "/test1" (test1-handler request)
        "/test2" (test2-handler request)
        "/test3" (handlers/test3-handler request)
        nil))

(defn wrapping-handler [request]
    (try
        (if-let [resp (router-handler request)]
            resp
            {:status 404 :body (str "Not found " (:uri request))})
    (catch Throwable e
        {:status 505 :body (str "Error: " (.getMessage e) "\n" (.getStackTrace e))})))

;; the same as (def full-handler (not-found-middleware (simple-log-middleware router-handler)))
(def full-handler (->
    router-handler
    not-found-middleware
    (resource/wrap-resource "public")
    file-info/wrap-file-info
    wrap-case-middleware
    wrap-exception-middleware
    simple-log-middleware))