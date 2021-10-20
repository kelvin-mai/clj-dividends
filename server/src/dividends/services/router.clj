(ns dividends.services.router
  (:require [taoensso.timbre :as log]
            [integrant.core :as ig]
            [dividends.router.routes :refer [create-router]]))

(defmethod ig/init-key :reitit/routes
  [_ config]
  (log/info "initializing routes")
  (create-router nil))
