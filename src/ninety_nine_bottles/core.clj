(ns ninety-nine-bottles.core
  (:require [clojure.string :as string]))

(def plans
  {:default {:amount1 identity
             :measure-word1 "bottles"
             :amount2 identity
             :measure-word2 "bottles"
             :sentence "Take one down and pass it around"
             :amount3 dec
             :measure-word3 "bottles"}
   0 {:amount1 (constantly "No more")
      :amount2 (constantly "no more")
      :sentence "Go to the store and buy some more"
      :amount3 (constantly 99)}
   1 {:measure-word1 "bottle"
      :measure-word2 "bottle"
      :sentence "Take it down and pass it around"
      :amount3 (constantly "no more")}
   2 {:measure-word3 "bottle"}})

(defn verse [line]
  (let [d (:default plans)
        {:keys [amount1 measure-word1
                amount2 measure-word2
                sentence
                amount3 measure-word3]
         :or {amount1 (:amount1 d) measure-word1 (:measure-word1 d)
              amount2 (:amount2 d) measure-word2 (:measure-word2 d)
              sentence (:sentence d)
              amount3 (:amount3 d) measure-word3 (:measure-word3 d)}}
        (get plans line)]
    (str (amount1 line) " " measure-word1 " of beer on the wall, "
         (amount2 line) " " measure-word2 " of beer.\n"
         sentence ", "
         (amount3 line) " " measure-word3 " of beer on the wall.\n")))

(defn verses [start-line end-line]
  (->> (range end-line (inc start-line))
       (map verse)
       (reverse)
       (string/join "\n")))

(defn song []
  (verses 99 0))
