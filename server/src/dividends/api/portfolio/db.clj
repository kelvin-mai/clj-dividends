(ns dividends.api.portfolio.db
  (:require [next.jdbc :as jdbc]
            [dividends.utils.query :as q]))

(defn get-all [db]
  (q/db-query! db {:select [:*]
                   :from [:portfolio]}))

(defn get-by-id [db id]
  (q/db-query-one! db {:select [:*]
                       :from [:portfolio]
                       :where [:= :id id]}))

(defn create [db data]
  (q/db-query-one! db {:insert-into :portfolio
                       :values [data]}))

(defn update-by-id [db id data]
  (q/db-query-one! db {:update :portfolio
                       :set data
                       :where [:= :id id]}))

(defn delete-by-id [db id]
  (q/db-query-one! db {:delete-from :portfolio
                       :where [:= :id id]}))
