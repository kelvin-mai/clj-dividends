(ns dividends.router.exception
  (:require [taoensso.timbre :as log]
            [reitit.ring.middleware.exception :as exception]))

(defn handle-exception [message exception request]
  {:status 500
   :body {:success false
          :message message
          :exception (.getClass exception)
          :data (ex-data exception)
          :uri (:uri request)}})

(def exception-middleware
  (exception/create-exception-middleware
    (merge
      exception/default-handlers
      {::exception/default (partial handle-exception "default")
       ::exception/wrap (fn [handler e request]
                          (log/error e (:uri request))
                          (handler e request))})))
