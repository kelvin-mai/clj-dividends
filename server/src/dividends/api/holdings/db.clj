(ns dividends.api.holdings.db
  (:require [dividends.utils.query :as q]))

(defn get-all-portfolio-id [db portfolio-id]
  (q/db-query! db {:select [:*]
                   :from [:holdings]
                   :where [:= :portfolio_id portfolio-id]}))
