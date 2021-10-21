(ns dividends.router.routes
  (:require [taoensso.timbre :as log]
            [reitit.ring :as ring]
            [dividends.router.middleware :as mw]
            [dividends.api.portfolio.router :as portfolio]))

(def health-route
  ["/health-check"
   {:name ::health-check
    :get (fn [_]
           {:status 200
            :body {:ping "pong"}})}])

(def api-routes
  [["/api"
    health-route
    portfolio/routes]])
