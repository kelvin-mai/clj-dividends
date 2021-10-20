(ns dividends.core
  (:require [dividends.services.config :as config]))

(defn -main []
  (config/read-config :prod))
