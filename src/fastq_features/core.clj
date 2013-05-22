(ns fastq_features.core
  (:gen-class)
  (:require [clojure.string    :as   string])
  (:use     [clojure.tools.cli :only (cli)]
            [clojure.java.io]))

(def feature-method
  {:length [(fn [r] (count (:sequence r)))
            ["length"]]})

(defn evaluate-method [[fnc headers] rs]
  (cons
    (cons "id" (map str headers))
    (map #(list (:id %) (fnc %)) rs)))

(defn read-id [id]
  (string/replace
    (first (string/split id #"\s+"))
    #"^@"
    ""))

(defn reads [fastq-lines]
  (map (fn [[id s _ q]] {:id (read-id id) :sequence s :scores q})
       (partition 4 fastq-lines)))

(defn -main [& args]
  (let [[options [feature fastq-file]] (cli args)
         rs (reads (line-seq (reader fastq-file)))]
    (doseq [i (evaluate-method (->> feature keyword feature-method) rs)]
      (println (string/join "\t" i)))))
