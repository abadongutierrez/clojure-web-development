(ns friend-auth-interactive-form.models.user
    (:require [clojure.java.jdbc :as sql]
              [friend-auth-interactive-form.models.db :as db]
              [cemerick.friend.credentials :refer (hash-bcrypt)]))

(defn insert-user [username plain-password pin]
    (sql/with-db-transaction [conn db/db-connection]
        (sql/execute! conn
            [(str "INSERT INTO fdb_user(username, password, pin) VALUES(?, ?, ?)")
            username (hash-bcrypt plain-password) pin])))

(defn find-users-with-username [username]
    (let [users (sql/with-db-transaction [conn db/db-connection]
                    (sql/query conn
                        ["SELECT * FROM fdb_user WHERE username ILIKE ?" username]))]
        (first users)))

(defn add-default-users []
    (insert-user "friend" "clojure" 123)
    (insert-user "friend-admin" "clojure" 123))