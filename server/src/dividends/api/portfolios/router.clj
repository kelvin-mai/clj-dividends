(ns dividends.api.portfolios.router
  (:require [dividends.api.portfolios.handler :as portfolios]
            [dividends.api.statements.handler :as statements]
            [dividends.api.holdings.handler :as holdings]))

(def routes
  ["/portfolios"
   ["" {:get portfolios/handle-get-all
        :post {:parameters {:body {:name string?}}
               :handler portfolios/handle-create}}]
   ["/:id" {:parameters {:path {:id uuid?}}}
    ["" {:get portfolios/handle-get-by-id
         :put {:parameters {:body {:name string?}}
               :handler portfolios/handle-update}
         :delete portfolios/handle-delete}]
    ["/holdings" {:get holdings/handle-get-all}]
    ["/statements" {:get statements/handle-get-all}]]])
