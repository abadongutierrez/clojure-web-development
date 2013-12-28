(defproject compojure-hello-world "0.1.0-SNAPSHOT"
  :description "Compojure Hello World"
  :url "http://example.com/FIXME"
  :dependencies [
    [org.clojure/clojure "1.5.1"]
    [compojure "1.1.6"]
    [hiccup "1.0.4"]
    [ring-server "0.3.0"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler compojure-hello-world.handler/app
         :init compojure-hello-world.handler/init
         :destroy compojure-hello-world.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}})
