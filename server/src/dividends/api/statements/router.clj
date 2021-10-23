(ns dividends.api.statements.router
  (:require [dividends.api.statements.handler :as statements]))

(def routes
  ["/statements"
   ["/:id" {:parameters {:path uuid?}
            }]
   ])
