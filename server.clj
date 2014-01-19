(ns experience.server
  (:require [vertx.core :as core]))

(core/deploy-module "io.vertx~mod-web-server~2.0.0-final"
                    :config {:port 8080
                             :host "localhost"
                             :web_root "web/app"})

