(ns dividends.api.portfolio.handler
  (:require [dividends.api.portfolio.db :as db]))

(defn handle-get-all [{:keys [env]}]
  (let [{:keys [db]} env]
    (db/get-all db)))

(defn handle-get-by-id [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    (db/get-by-id db id)))

(defn handle-create [{:keys [env parameters]}]
  (let [{:keys [db]} env
        data (:body parameters)]
    (db/create db data)))

(defn handle-update [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])
        data (:body parameters)]
    (db/update-by-id db id data)))

(defn handle-delete [{:keys [env parameters]}]
  (let [{:keys [db]} env
        id (get-in parameters [:path :id])]
    (db/delete-by-id db id)))
