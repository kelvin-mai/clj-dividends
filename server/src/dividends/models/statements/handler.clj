(ns dividends.models.statements.handler 
  (:require [dividends.models.statements.db :as statements.db]))

(defn handle-get-all [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    {:status 200
     :body (statements.db/get-all-portfolio-id db id)}))
