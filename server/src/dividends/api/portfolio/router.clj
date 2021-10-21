(ns dividends.api.portfolio.router
  (:require [dividends.api.portfolio.handler :as portfolio]))

(def routes
  ["/portfolio"
   ["" {:get portfolio/handle-get-all
        :post {:parameters {:body {:name string?}}
               :handler portfolio/handle-create}}]
   ["/:id" {:get {:parameters {:path {:id string?}}
                  :handler portfolio/handle-get-by-id}
            :post {:parameters {:body {:name string?}}
                   :handler portfolio/handle-get-by-id}
            :put {:parameters {:path {:id string?}
                               :body {:name string?}}
                  :handler portfolio/handle-get-by-id}
            :delete {:parameters {:path {:id string?}}
                     :handler portfolio/handle-get-by-id}}]])
