(ns xperience-backend.events
  (:require [vertx.eventbus :as eb]))

(defn lookup-user-search [message user]
  (let [company (-> user :results first :company)]
    (println user)
    (eb/send "vertx.mongopersistor"
             {:action "find"
              :collection "searches"
              :matcher {:company company}}
             (partial eb/reply* message))))

(defn save-user-search [search message user]
  (let [company (-> user :results first :company)]
    (eb/send "vertx.mongopersistor"
             {:action "save"
              :collection "searches"
              :document (assoc search :company company)}
             (partial eb/reply* message))))


(defn pre-lookup-user [after-lookup {:keys [username]}]
  (let [message eb/*current-message*]
    (eb/send "vertx.mongopersistor"
             {:action "find"
              :collection "users"
              :matcher {:username username}
              :limit 1}
             (partial after-lookup message))))

(def lookup-search
  (partial pre-lookup-user lookup-user-search))

(defn save-search [{:keys [username search]}]
  (let [save-fn (partial save-user-search search)]
    (pre-lookup-user save-fn {:username username})))

(defn register-handlers []
  (println "Registering handlers")
  (eb/on-message "xperience.search.list" lookup-search lookup-search)
  (eb/on-message "xperience.search.save" save-search save-search))
