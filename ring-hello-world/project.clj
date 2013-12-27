(defproject ring-hello-world "0.1.0-SNAPSHOT"
  :description "Ring Hello World"
  :url "http://example.com/FIXME"
  :dependencies [
    [org.clojure/clojure "1.5.1"]
    [ring/ring-core "1.2.1"]
    [ring-server "0.3.0"]]
  :plugins [
    [lein-ring "0.8.7"]]
  :ring {
    :handler ring-hello-world.handler/app
    :init ring-hello-world.handler/init
    :destroy ring-hello-world.handler/destroy
  }
  :aot :all
  :profiles {
    :production {
      :ring {
        :open-browser? false, :stacktraces? false, :auto-reload? false
      }
    }
    :dev {
      :dependencies [
        [ring-mock "0.1.5"]
        [ring/ring-devel "1.2.0"]]
    }
  })
