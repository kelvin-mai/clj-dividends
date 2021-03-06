(ns user
  (:require [clojure.tools.namespace.repl :as tools-ns]
            [integrant.repl :as ig-repl :refer [go halt]]
            [integrant.repl.state :as state]
            [nrepl.server]
            [cider.nrepl :refer [cider-nrepl-handler]]
            [dividends.services.config :as config]))

(tools-ns/set-refresh-dirs "dev" "server/src")

(ig-repl/set-prep!
 (fn []
   (config/read-config :dev)))

(declare router db)

(defn start-interactive []
  (go)
  (def router (:reitit/routes state/system))
  (def db (:postgres/db state/system))
  :ready!)

(defn restart []
  (halt)
  (tools-ns/refresh :after 'user/start-interactive))

(defn start-dev [& _]
  (nrepl.server/start-server :port 1234
                             :bind "0.0.0.0"
                             :handler cider-nrepl-handler)
  (start-interactive))

(comment
  (halt)
  (restart)
  state/system
  ;
  (dividends.utils.query/db-query! db {:select [:*]
                                       :from [:portfolios]})
  (router {:request-method :get
           :uri "/api/health-check"})
  (->> (router {:request-method :get
                :uri "/api/portfolios/b1a724c4-b59c-48cc-bcdf-038f5d371b79/statements"})
       :body
       (muuntaja.core/decode "application/json"))
  (->> (router {:request-method :put
                :uri "/api/portfolios/a8f27dff-263b-47c4-b0be-31ae7cd04c42"
                :body-params {:name "dev"}})
       :body
       (muuntaja.core/decode "application/json")))
