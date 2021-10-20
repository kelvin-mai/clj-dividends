(ns dividends.config
  (:require [taoensso.timbre :as log]
            [aero.core :as aero]
            [integrant.core :as ig]))

(defmethod aero/reader 'ig/ref
  [_ _ value]
  (ig/ref value))

(defmethod ig/init-key :system/config
  [_ config]
  (log/info "system starting with config" config)
  config)

(defn read-config
  ([] (read-config :prod))
  ([profile]
   (aero/read-config 
     "resources/config.edn"
     {:profile profile})))
