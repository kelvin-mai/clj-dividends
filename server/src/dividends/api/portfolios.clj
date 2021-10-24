(ns dividends.api.portfolios
  (:require [dividends.models.portfolios.handler :as portfolios]
            [dividends.models.holdings.handler :as holdings]
            [dividends.models.statements.handler :as statements]))

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
