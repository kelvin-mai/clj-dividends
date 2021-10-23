(ns dividends.api.holdings.handler
  (:require [dividends.api.holdings.db :as db]))

(defn handle-get-all [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    {:status 200
     :body (db/get-all-portfolio-id db id)}))
