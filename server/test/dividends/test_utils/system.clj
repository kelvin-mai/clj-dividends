(ns dividends.test-utils.system
  (:require [integrant.core :as ig]
            [dividends.services.config :as config]))

(def test-system (atom nil))

(defn use-system []
  (fn [test-fn]
    (reset! test-system
            (let [ig-config (config/read-config :test)]
              (ig/init ig-config)))
    (test-fn)
    (ig/halt! @test-system)
    (reset! test-system nil)))
