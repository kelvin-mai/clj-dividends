(ns dividends.models.portfolios.handler
  (:require [dividends.models.portfolios.db :as portfolios.db]
            [dividends.models.holdings.db :as holdings.db]
            [dividends.models.statements.db :as statements.db]))

(defn handle-get-all [{:keys [env]}]
  (let [{:keys [db]} env]
    {:status 200
     :body (portfolios.db/get-all db)}))

(defn handle-get-by-id [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])
        portfolio (portfolios.db/get-by-id db id)
        holdings (holdings.db/get-all-portfolio-id db (:portfolios/id portfolio))
        statements (statements.db/get-all-portfolio-id db (:portfolios/id portfolio))]
    {:status 200
     :body (assoc portfolio 
                  :holdings holdings
                  :statements statements)}))

(defn handle-create [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)]
    {:status 201
     :body (portfolios.db/create db data)}))

(defn handle-update [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])
        data (:body parameters)]
    {:status 200
     :body (portfolios.db/update-by-id db id data)}))

(defn handle-delete [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    {:status 200
     :body {:success true
            :deleted (portfolios.db/delete-by-id db id)}}))
