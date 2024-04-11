(defproject friend-auth-interactive-form "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [com.cemerick/friend "0.2.1"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler friend-auth-interactive-form.handler/app
         :init friend-auth-interactive-form.handler/init
         :destroy friend-auth-interactive-form.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]}})
