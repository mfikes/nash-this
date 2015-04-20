(ns foo.bar)

(set-print-fn! #(js/java.lang.System.out.print %))

(deftype MyCustomAtom [state]
  IDeref
  (-deref [_] state))

(prn @(MyCustomAtom. 10))

(prn 
  (let [v (volatile! 1)]
    (volatile? v)
    (not (volatile? (atom 1)))
    (vreset! v 2)
    (vswap! v inc)
    @v))

(prn (let [xform (comp (map inc)
                  (filter even?)
                  (dedupe)
                  (mapcat range)
                  (partition-all 3)
                  (partition-by #(< (apply + %) 7))
                  (mapcat flatten)
                  (random-sample 1.0)
                  (take-nth 1)
                  (keep #(when (odd? %) (* % %)))
                  (keep-indexed #(when (even? %1) (* %1 %2)))
                  (replace {2 "two" 6 "six" 18 "eighteen"})
                  (take 11)
                  (take-while #(not= 300 %))
                  (drop 1)
                  (drop-while string?)
                  (remove string?))
          data (vec (interleave (range 18) (range 20)))]
  (sequence xform data)))

(prn (transduce (map-indexed (fn [i x] [i x])) conj [] [1 2 3]))
