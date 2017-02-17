(ns gilded-rose.core-spec
  (:require [speclj.core :refer :all]
            [clojure.test :refer :all]
            [gilded-rose.core :refer [update-quality]]))

(deftest a-test
  (testing "base case"
    (let [
          result '({:name "+5 Dexterity Vest", :sell-in 9, :quality 19} {:name "+6 Dexterity Vest", :sell-in 9, :quality 0} {:name "Aged Brie", :sell-in 1, :quality 1} {:name "Elixir of the Mongoose", :sell-in 4, :quality 6} {:name "Sulfuras, Hand Of Ragnaros", :sell-in -1, :quality 80} {:name "Backstage passes to a TAFKAL80ETC concert", :sell-in 14, :quality 21} {:name "Conjured", :sell-in 14, :quality 16})
          ]
      (is (= (gilded-rose.core/update-current-inventory) result))))
  )

