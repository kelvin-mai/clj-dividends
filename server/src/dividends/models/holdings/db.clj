(ns dividends.models.holdings.db
  (:require [dividends.utils.query :as q]))

(defn get-all-portfolio-id [db portfolio-id]
  (q/db-query! db {:select [:*]
                   :from [:holdings]
                   :where [:= :portfolio-id portfolio-id]}))
