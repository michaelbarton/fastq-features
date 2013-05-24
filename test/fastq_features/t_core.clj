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
      ((feature-methods :length) {:sequence "TGAC"}) => 4)))

(facts "about `evaluate-methods`"
  (fact "evaluates methods and returns a sequence of results"
    (let [dummy-methods {:a (fn [r] :dummy.1)
                         :b (fn [r] :dummy.2)}
          dummy-reads   [{:id "read.1"} {:id "read.2"}]]

      (evaluate-methods dummy-methods dummy-reads) =>
      '(["read.1" "a" :dummy.1]
        ["read.1" "b" :dummy.2]
        ["read.2" "a" :dummy.1]
        ["read.2" "b" :dummy.2]))))
