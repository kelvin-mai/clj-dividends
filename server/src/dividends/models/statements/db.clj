(ns dividends.models.statements.db
  (:require [dividends.utils.query :as q]))

(defn get-all-portfolio-id [db portfolio-id]
  (q/db-query! db {:select [:*]
                   :from [:statements]
                   :where [:= :portfolio-id portfolio-id]}))

(defn create [db data]
  (q/db-query-one! db {:insert-into :statements
                       :values [data]}))

(defn update-by-id [db id data]
  (q/db-query-one! db {:update :statements
                       :set data
                       :where [:= :id id]}))

(defn delete-by-id [db id]
  (q/db-query-one! db {:delete-from :statements
                       :where [:= :id id]}))
