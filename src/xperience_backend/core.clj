(ns xperience-backend.core
  (:require [vertx.core :as vertx]))

(defn init
  []
  (vertx/deploy-module "io.vertx~mod-web-server~2.0.0-final"
                       :config {:port 8080
                                :host "localhost"
                                :web_root "web/app"}))

