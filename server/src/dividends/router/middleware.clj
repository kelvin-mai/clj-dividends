(ns dividends.router.middleware
  (:require [reitit.ring.coercion :as coercion]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [dividends.router.exception :as exception]))

(def wrap-env
  {:name ::env
   :compile
   (fn [{:keys [env]} _]
     (fn [handler]
       (fn [request]
         (handler (assoc request :env env)))))})

(def global-middleware
  [muuntaja/format-middleware
   exception/exception-middleware
   coercion/coerce-request-middleware
   coercion/coerce-response-middleware
   wrap-env])
