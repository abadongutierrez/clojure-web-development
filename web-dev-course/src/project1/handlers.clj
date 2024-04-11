(ns project1.handlers)

(defn test3-handler [request]
    (throw (RuntimeException. "Error!"))
    {:body "test3"})