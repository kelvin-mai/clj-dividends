(ns dividends.models.holdings.handler
  (:require [dividends.models.holdings.db :as holdings.db]))

(defn handle-get-all [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    {:status 200
     :body (holdings.db/get-all-portfolio-id db id)}))
