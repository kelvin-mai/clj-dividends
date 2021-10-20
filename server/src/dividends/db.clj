(ns dividends.db
  (:require [taoensso.timbre :as log]
            [integrant.core :as ig]
            [next.jdbc :as jdbc]
            [next.jdbc.connection :as connection])
  (:import [com.zaxxer.hikari HikariDataSource]))

(defmethod ig/init-key :postgres/db
  [_ {:keys [config]}]
  (let [options (:jdbc config)
        datasource (connection/->pool HikariDataSource options)]
    (log/info "starting database connection pool starting")
    (connection/->pool HikariDataSource options)))

(defmethod ig/halt-key! :postgres/db
  [_ datasource]
  (log/info "closing database connection pool")
  (.close ^HikariDataSource (:connectable datasource)))
