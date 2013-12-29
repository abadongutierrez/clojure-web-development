(defproject liberator-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
      [compojure "1.1.6"]
      [hiccup "1.0.4"]
      [ring-server "0.3.0"]
      ;Liberator is a Clojure library for writing RESTful services modeled after webmachine
      [liberator "0.10.0"]
      ;Cheshire is a fast and easy-to-use JSON parsing library.
      [cheshire "5.2.0"]
      [lib-noir "0.7.6"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler liberator-service.handler/app
         :init liberator-service.handler/init
         :destroy liberator-service.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}})
