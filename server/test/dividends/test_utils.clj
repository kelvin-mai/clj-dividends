(ns dividends.test-utils
  (:require [integrant.core :as ig]
            [dividends.config :as config]
            dividends.core))

(def test-system (atom nil))

(defn use-system []
  (fn [test-fn]
    (reset! test-system
            (let [ig-config (config/read-config :test)]
              (ig/init ig-config)))
    (test-fn)
    (ig/halt! @test-system)
    (reset! test-system nil)))
