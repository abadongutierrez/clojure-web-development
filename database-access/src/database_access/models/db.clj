(ns database-access.models.db
  (:require [clojure.java.jdbc :as sql]))

;Map of connection parameters
;This has a downside: the connection informationis stored directly in the source
(def db {:subprotocol "postgresql"
  :subname "//localhost/my_website"
  :user "postgres"
  :password ""})

(defn create-users-table []
  (sql/with-connection db
    (sql/create-table
      :users                          ;keyword specifying the table name
      [:id "varchar(32) PRIMARY KEY"] ;Column id (using a vector to represent it)
      [:pass "varchar(100)"])))       ;Column pass

;Select records
(defn get-user [id]
  (sql/with-connection db
    (sql/with-query-results
      res
      ["select * from users where id = ?" id] (first res))))

;Insert records
(defn add-user [user]
  (sql/with-connection db
    (sql/insert-record :users user)))

;Insert using values
(defn add-user [id pass]
  (sql/with-connection db
    (sql/insert-rows :users
      [id pass])))

(defn update-user-pass [id pass]
  (sql/with-connection db
    (sql/update-values :users
      ["id=?" id] {:pass pass})))

(defn delete-user [id]
  (sql/with-connection db
    (sql/delete-rows :users
      ["id=?" id])))

;if
;(sql/transaction ...)

(defn create-employee-table []
  (sql/with-connection db
    (sql/create-table
      :employee
      [:name "varchar(50)"]
      [:occupation "varchar(50)"]
      [:place "varchar(50)"]
      [:country "varchar(50)"])))

(defn insert-rows []
  (sql/with-connection db
    (sql/insert-rows
      :employee
      ["Albert Einstein", "Engineer", "Ulm", "Germany"]
      ["Alfred Hitchcock", "Movie Director", "London", "UK"]
      ["Wernher Von Braun", "Rocket Scientist", "Wyrzysk", "Poland"]
      ["Sigmund Freud", "Neurologist", "Pribor", "Czech Republic"]
      ["Mahatma Gandhi", "Lawyer", "Gujarat", "India"]
      ["Sachin Tendulkar", "Cricket Player", "Mumbai", "India"]
      ["Michael Schumacher", "F1 Racer", "Cologne", "Germany"])))

(defn read-employees []
  (sql/with-connection db
    (sql/with-query-results rs ["select * from employee"] (doall rs))))
