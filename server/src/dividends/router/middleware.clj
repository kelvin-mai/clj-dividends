(ns dividends.router.middleware
  (:require [reitit.ring.coercion :as coercion]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.muuntaja :as muuntaja]))

(def wrap-env
  {:name ::env
   :compile
   (fn [{:keys [env]} _]
     (fn [handler]
       (fn [request]
         (handler (assoc request :env env)))))})

(def global-middleware
  [exception/exception-middleware
   muuntaja/format-middleware
   coercion/coerce-request-middleware
   coercion/coerce-response-middleware
   wrap-env])
