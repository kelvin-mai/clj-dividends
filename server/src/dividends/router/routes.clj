(ns dividends.router.routes
  (:require [taoensso.timbre :as log]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [dividends.router.middleware :as mw]))

(def health-route
  ["/health-check"
   {:name ::health-check
    :get (fn [_]
           {:status 200
            :body {:ping "pong"}})}])

(defn create-router
  [config]
  (log/info "initializing routes")
  (ring/ring-handler
    (ring/router
      [["/api"
        health-route]]
      {:data {:muuntaja m/instance
              :middleware [exception/exception-middleware
                           muuntaja/format-middleware
                           mw/wrap-env]}})
    (ring/routes
      (ring/redirect-trailing-slash-handler))))
