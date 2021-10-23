(ns dividends.services.router
  (:require [taoensso.timbre :as log]
            [integrant.core :as ig]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.coercion.spec :refer [coercion]]
            [dividends.router.routes :refer [api-routes]]
            [dividends.router.middleware :refer [global-middleware]]))

(defmethod ig/init-key :reitit/routes
  [_ {:keys [db]}]
  (log/info "initializing routes")
  (ring/ring-handler
   (ring/router
    api-routes
    {:data {:env {:db db}
            :coercion coercion
            :muuntaja m/instance
            :middleware global-middleware}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler))))
