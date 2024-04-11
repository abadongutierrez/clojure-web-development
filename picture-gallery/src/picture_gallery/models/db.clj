(ns picture-gallery.models.db
    (:require [clojure.java.jdbc :as sql]
              [noir.util.crypt :as crypt]))

(def db
    {:subprotocol "postgresql"
     :subname "//localhost/gallery"
     :user "postgres"})
   
; Parameters passed to defmacro are not evaluated by default
; To evaluate the parameter we use the tilde ~ . Using the ~ notation
; indicates that we would like to replace the name with the value it refers to (unquoting).
; The ~@ notation is called unquote splicing. This notation is used when we're dealing with
; a sequence. In this case body consists of a list representing the function's body.
; The backtick (`) sign means that we wish to treat the following list as data instead of executing
; it. This is the opposite of unquoting, and it's referred to as syntax-quoting.
(defmacro with-db [f & body]
    `(sql/with-connection ~db (~f ~@body)))

; user is a map with two keys matching the column names in the table
(defn create-user [user]
    (with-db
        sql/insert-record
        :users user))

(defn get-user [id]
    (with-db
        sql/with-query-results
        res ["select * from users where id = ?" id] (first res)))