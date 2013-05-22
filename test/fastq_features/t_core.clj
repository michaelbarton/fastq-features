(ns fastq_features.t-core
  (:use midje.sweet)
  (:use [fastq_features.core]))

(facts "about `reads`"

  (fact "returns an empty list from an empty list"
    (reads '()) => '())

  (fact "returns a read from a single fastq entry"
    (reads '("@SRR042334.1" "TGAC" "+SRR042334.1" "hhhh")) =>
        '({:id "SRR042334.1" :scores "hhhh" :sequence "TGAC"})))

(facts "about `feature-methods`"

  (facts "about `length`"
    (fact "returns the length of a read"
      ((->> feature-method :length first) {:sequence "TGAC"}) => 4))) 

(facts "about `evaluate-method`"
  (fact "returns a sequence of results with header"
    (evaluate-method (feature-method :length) '({:id "read.1" :sequence "ATGC"})) => 
        '(("id" "length") ("read.1" 4))
        
        ))
