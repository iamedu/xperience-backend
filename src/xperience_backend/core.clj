(ns xperience-backend.core
  (:require [vertx.core :as vertx]
            [vertx.eventbus :as eb]
            [xperience-backend.events :as ev]))

(def web-config
  {:port 8080
   :host "localhost"
   :web_root "web/app"
   :bridge true
   :inbound_permitted [{:address "vertx.basicauthmanager.login"}
                       {:address "vertx.basicauthmanager.logout"}
                       {:address "xperience.search.list"
                        :requires_auth true}
                       {:address "xperience.search.save"
                        :requires_auth true}
                       {:address "vertx.mongopersistor"
                        :requires_auth true}]
   :outbound_permitted []})

(defn handle-persistor [err deploy-id]
  (eb/send "vertx.mongopersistor"
           {:action "save"
            :collection "users"
            :document {:firstname "Eduardo"
                       :lastname "Diaz"
                       :username "iamedu@gmail.com"
                       :password "password"
                       :company "uxmarketing"}}))

(defn init
  []
  (vertx/deploy-module "io.vertx~mod-mongo-persistor~2.0.0-final"
                       :handler handle-persistor)
  (vertx/deploy-module "io.vertx~mod-auth-mgr~2.0.0-final")
  (ev/register-handlers)
  (vertx/deploy-module "io.vertx~mod-web-server~2.0.0-final"
                       :config web-config))
