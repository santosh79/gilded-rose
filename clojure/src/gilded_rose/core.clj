(ns gilded-rose.core)

(defmulti get-quality (fn [it]
                        (:name it)))

(defmethod get-quality "Aged Brie"
  [item]
  (merge item {:quality (inc (:quality item))}))

(defmethod get-quality "Sulfuras, Hand Of Ragnaros"
  [item]
  item)

(defmethod get-quality "Conjured"
  [item]
  (merge item {:quality (dec (dec (:quality item)))}))

(defmethod get-quality "Backstage passes to a TAFKAL80ETC concert"
  [it]
  (merge it {:quality
             (cond
               (<= (:sell-in it) 0) 0
               (< (:sell-in it) 5) (+ 3 (:quality it))
               (< (:sell-in it) 10) (+ 2 (:quality it))
               :else (inc (:quality it)))
             }))

(defmethod get-quality :default
  [item]
  (merge item {:quality (dec (:quality item))}))

(defn get-sell-in [item]
  (merge item {:sell-in (dec (:sell-in item))}))

(defn handle-negative-quality
  [item]
  (merge item {:quality (max 0 (:quality item))}))

(defn handle-greater-than-50-quality
  [item]
  (if (= "Sulfuras, Hand Of Ragnaros" (:name item))
         item
         (merge item {:quality (min 50 (:quality item))})))

(defn update-quality [items]
  (map (fn[item]
         (-> item get-sell-in get-quality handle-negative-quality handle-greater-than-50-quality)) items))

(defn item [item-name, sell-in, quality]
  {:name item-name, :sell-in sell-in, :quality quality})

(defn update-current-inventory[]
  (let [inventory 
    [
      (item "+5 Dexterity Vest" 10 20)
      (item "+6 Dexterity Vest" 10 0)
      (item "Aged Brie" 2 0)
      (item "Elixir of the Mongoose" 5 7)
      (item "Sulfuras, Hand Of Ragnaros" 0 80)
      (item "Backstage passes to a TAFKAL80ETC concert" 15 20)
      (item "Conjured" 15 18)
    ]]
    (update-quality inventory)
    ))
