(ns friend-auth-interactive-form.models.user
    (:require [clojure.java.jdbc :as sql]
              [friend-auth-interactive-form.models.db :as db]
              [cemerick.friend.credentials :refer (hash-bcrypt)]))

(defn insert-user [username plain-password pin]
    (sql/with-db-transaction [conn db/db-connection]
        (sql/execute! conn
            [(str "INSERT INTO fdb_user(username, password, pin) VALUES(?, ?, ?)")
            username (hash-bcrypt plain-password) pin])))

(defn insert-role [username role]
    (sql/with-db-transaction [conn db/db-connection]
        (sql/execute! conn
            [(str "INSERT INTO fdb_user_role(username, rolename) VALUES(?, ?)")
            username role])))

(defn find-roles-by-user [username]
    (let [roles (sql/with-db-transaction [conn db/db-connection] 
        (sql/query conn
            ["SELECT rolename FROM fdb_user_role WHERE username = ?" (str username)]))]
        (set (map #(:rolename %) roles))
    ))

(defn find-users-with-username [username]
    (let [users (sql/with-db-transaction [conn db/db-connection]
                    (sql/query conn
                        ["SELECT * FROM fdb_user WHERE username = ?" (str username)]))
          user (first users)]
          (if (nil? user) nil
              (assoc user :roles (find-roles-by-user username))
          )))

(defn add-default-users []
    (insert-user "friend" "clojure" 123)
    (insert-user "friend-admin" "clojure" 123))

(defn add-default-roles []
    (insert-role "friend" "user")
    (insert-role "friend-admin" "user")
    (insert-role "friend-admin" "admin"))

(defn add-default-data []
    (add-default-users)
    (add-default-roles))