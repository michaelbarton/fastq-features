(ns fastq_features.core
  (:gen-class)
  (:use [clojure.tools.cli :only (cli)]))

(defn -main [& args]
  (let [[options arguments] (cli args)] 
    (println "Hellow world")))
