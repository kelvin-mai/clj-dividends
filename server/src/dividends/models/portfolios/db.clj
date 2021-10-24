(ns dividends.models.portfolios.db
  (:require [dividends.utils.query :as q]))

(defn get-all [db]
  (q/db-query! db {:select [:*]
                   :from [:portfolios]}))

(defn get-by-id [db id]
  (q/db-query-one! db {:select [:*]
                       :from [:portfolios]
                       :where [:= :id id]}))

(defn create [db data]
  (q/db-query-one! db {:insert-into :portfolios
                       :values [data]}))

(defn update-by-id [db id data]
  (q/db-query-one! db {:update :portfolios
                       :set data
                       :where [:= :id id]}))

(defn delete-by-id [db id]
  (q/db-query-one! db {:delete-from :portfolios
                       :where [:= :id id]}))
