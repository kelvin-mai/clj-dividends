(ns dividends.api.portfolios.handler
  (:require [dividends.api.portfolios.db :as db]))

(defn handle-get-all [{:keys [env]}]
  (let [{:keys [db]} env]
    {:status 200
     :body (db/get-all db)}))

(defn handle-get-by-id [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    {:status 200
     :body (db/get-by-id db id)}))

(defn handle-create [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)]
    {:status 201
     :body (db/create db data)}))

(defn handle-update [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])
        data (:body parameters)]
    {:status 200
     :body (db/update-by-id db id data)}))

(defn handle-delete [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    {:status 200
     :body {:success true
            :deleted (db/delete-by-id db id)}}))
