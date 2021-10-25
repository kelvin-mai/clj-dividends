(ns dividends.services.router
  (:require [taoensso.timbre :as log]
            [integrant.core :as ig]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.coercion.spec :refer [coercion]]
            [dividends.api.core :as api]
            [dividends.router.middleware :refer [global-middleware]]
            [dividends.router.exception :refer [handle-exception]]))

(defmethod ig/init-key :reitit/routes
  [_ {:keys [db]}]
  (log/info "initializing routes")
  (ring/ring-handler
   (ring/router
    api/routes
    {:data {:env {:db db}
            :coercion coercion
            :muuntaja m/instance
            :middleware global-middleware}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler
     {:not-found (handle-exception 404 "Route not found")
      :method-not-allowed (handle-exception 405 "Method not allowed")
      :not-acceptable (handle-exception 406 "Not acceptable")}))))
