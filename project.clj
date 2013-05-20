(defproject fastq-features "0.1.0"
  :main         fastq_features.core
  :description  "Generate ML features from FASTQ files"
  :dependencies [[org.clojure/clojure   "1.4.0"]
                 [org.clojure/tools.cli "0.2.2"]]
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}})
  
