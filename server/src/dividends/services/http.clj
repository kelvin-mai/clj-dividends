(ns dividends.services.http
  (:require [taoensso.timbre :as log]
            [integrant.core :as ig]
            [org.httpkit.server :as http]))

(defmethod ig/init-key :http/server
  [_ {:keys [router config]}]
  (let [port (:http-port config)]
    (log/info "server started on port" port)
    (http/run-server router {:port port})))

(defmethod ig/halt-key! :http/server
  [_ server]
  (log/info "server stopping")
  (server :timeout 100))
