(require 'cljs.closure)

(cljs.closure/build "src"
  {:output-to "out/main.js"
   :optimizations :advanced
   :pseudo-names true
   :externs ["externs.js"]})

(System/exit 0)
