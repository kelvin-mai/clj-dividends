(ns dividends.core
  (:require [dividends.config :as config]
            dividends.db
            dividends.router
            dividends.server))

(defn -main []
  (config/read-config :prod))
