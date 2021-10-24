(ns dividends.portfolios.router-test
  (:require [clojure.test :refer [use-fixtures
                                  deftest
                                  testing
                                  is]]
            [dividends.test-utils.system :refer [test-system use-system]]
            [dividends.test-utils.router :refer [request]]))

(deftest portfolio-router-test
  (let [{router :reitit/routes} @test-system]
    (testing "can create portfolio"
      (let [created (request
                     router
                     {:request-method :post
                      :uri "/api/portfolios"
                      :body-params {:name "test"}})]
        (is (= "test" (:name created)))))))
