(defproject database-access "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.4"]
                 [ring-server "0.3.0"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [postgresql/postgresql "9.1-901.jdbc4"]
                 [clj-pdf "1.11.6"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler database-access.handler/app
         :init database-access.handler/init
         :destroy database-access.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}})
