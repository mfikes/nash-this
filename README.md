# nash-this

Illustrates a collision problem in Nashorn related to `this.state=state` in ctors.

Thanks to [Sebastian Bensusan](https://github.com/bensu) for helping put this together!

# Running

1. Download a "Quick Start" `cljs.jar` from https://github.com/clojure/clojurescript/releases
2. Place the `cljs.jar` in the `nash-this` dir.
3. Build using `java -cp cljs.jar:src clojure.main release.clj`
4. Run using `$JAVA_HOME/bin/jjs out/main.js`

With 8u40 or later you will see output like:

```
10
NaN
()
[[NaN 1] [NaN 2] [NaN 3]]
```

You should instead see:

```
10
3
(36 200 10)
[[0 1] [1 2] [2 3]]
```

You can cause the correct output to be emitted by simply revising the name of the `state` field in `foo.bar/MyCustomAtom` to be something else, say, `horse`:

```clojure
(deftype MyCustomAtom [horse]
  IDeref
  (-deref [_] horse))
```
