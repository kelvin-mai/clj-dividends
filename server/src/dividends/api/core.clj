(ns dividends.api.core
  (:require [dividends.api.portfolios :as portfolios]))

(def health-route
  ["/health-check"
   {:get (fn [_]
           {:status 200
            :body {:ping "pong"}})}])

(def routes
  [["/api"
    health-route
    portfolios/routes]])
