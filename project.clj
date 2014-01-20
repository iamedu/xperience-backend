(defproject xperience-backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http "0.7.8"]
                 [twitter-api "0.7.5"]
                 [instagram-api "0.1.7"]
                 [org.jcodec/jcodec "0.1.6-3"]]
  :plugins [[lein-vertx "0.2.0-SNAPSHOT"]]
  :vertx {:main xperience-backend.core/init
          :author "Eduardo Diaz"
          :keywords ["Video wall"]
          :developers ["Eduardo Diaz"]})
