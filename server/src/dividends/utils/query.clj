(ns dividends.utils.query
  (:require [next.jdbc :as jdbc]
            [honeysql.core :as sql]))

(defn db-query! [db query]
  (jdbc/execute! db 
                 (sql/format query)
                 {:return-keys true}))

(defn db-query-one! [db query]
  (jdbc/execute-one! db
                     (sql/format query)
                     {:return-keys true}))
