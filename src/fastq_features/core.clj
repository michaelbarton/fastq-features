(ns fastq_features.core
  (:gen-class)
  (:require [clojure.string    :as   string])
  (:use     [clojure.tools.cli :only (cli)]
            [clojure.java.io]))

(def feature-methods
  {:length (fn [r] (count (:sequence r)))})

(defn evaluate-methods [mthds reads]
  (mapcat
    (fn [r]
      (for [m (keys mthds)] [(:id r) (name m) ((mthds m) r)]))
    reads))

(defn read-id [id]
  (string/replace
    (first (string/split id #"\s+"))
    #"^@"
    ""))

(defn reads [fastq-lines]
  (map (fn [[id s _ q]] {:id (read-id id) :sequence s :scores q})
       (partition 4 fastq-lines)))

(defn -main [& args]
  (let [[options [fastq-file]] (cli args)
        results (->>
                  (reads (line-seq (reader fastq-file)))
                  (evaluate-methods feature-methods)
                  (map #(string/join "\t" %)))]
    (doseq [r results] (println r))))
