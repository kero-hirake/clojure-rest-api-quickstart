(ns minimal-api.boundary.db.core
  (:require 
   [clojure.spec.alpha :as s]
   [clojure.string :as str]
   [integrant.core :as ig]))

(s/def ::data any?)
(s/def ::db (s/keys :req-un [::data]))

(defrecord Boundary [data])

(defmethod ig/init-key ::db [_ _]
  (->Boundary (atom {:todo {"todo1" {"task" "build an API"} 
                             "todo2" {"task" "?????"}
                             "todo3" {"task" "profit!"}}})))

(defn select-all [{:keys [data]} table]
  (get @data table))

(defn select-by-key [{:keys [data]} table k]
  (get-in @data [table k]))

(defn insert! [{:keys [data]} table key-prefix v]
  (let [id (->> (keys (get @data table))
                (map #(-> %
                          (str/replace-first key-prefix "")
                          Long/parseLong))
                (apply max)
                inc)
        k (str key-prefix id)]
    (swap! data assoc-in [table k] v)
    k)) 

(defn update-by-key! [{:keys [data]} table k v]
  (swap! data assoc-in [table k] v)
  nil)

(defn delete-by-key! [{:keys [data]} table k]
  (swap! data update table dissoc k)
  nil)

