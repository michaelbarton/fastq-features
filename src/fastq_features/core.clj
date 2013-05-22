(ns fastq_features.core
  (:gen-class)
  (:require [clojure.string    :as   string])
  (:use     [clojure.tools.cli :only (cli)]
            [clojure.java.io]))

(defn read-id [id]
  (string/replace
    (first (string/split id #"\s+"))
    #"^@"
    ""))

(defn reads [fastq-lines]
  (map (fn [[id s _ q]] {:id (read-id id) :sequence s :scores q})
       (partition 4 fastq-lines)))

(defn length [r]
  (count (:sequence r)))

(defn -main [& args]
  (let [[options [feature fastq-file]] (cli args)
         fastq-lines                   (line-seq (reader fastq-file))]
    ))
