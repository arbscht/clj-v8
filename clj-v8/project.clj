(defproject clj-v8 "0.1.5"
  :description "A Clojure wrapper for the v8 Javascript library"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [net.java.dev.jna/jna "3.5.1"]
                 [clj-v8-native/clj-v8-native "0.1.5-SNAPSHOT"]]
  :dev-dependencies [[midje "1.3.1"]]
  :aot [v8.jsr223]
  :profiles {:dev {:dependencies [[midje "1.6.3" :exclusions [org.clojure/clojure]]]
                   :plugins [[lein-midje "3.1.3"]]}}
  :jvm-opts ["-Djava.library.path=target/native/macosx/x86_64:target/native/linux/x86_64:target/native/linux/x86:native/macosx/x86_64:native/linux/x86_64:native/linux/x86:"])
