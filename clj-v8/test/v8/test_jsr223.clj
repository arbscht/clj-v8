(ns v8.test-jsr223
  (:require v8.jsr223)
  (:use [midje.sweet]))

(defn non-empty-string? [s]
  (and (string? s)
       (> (count s) 0)))

(fact "engine factory knows its engine"
      (.getParameter (v8.jsr223.V8ScriptEngineFactory.) javax.script.ScriptEngine/NAME)
      =>
      "v8")

(fact "engine factory knows v8's version"
      (.getParameter (v8.jsr223.V8ScriptEngineFactory.) javax.script.ScriptEngine/ENGINE_VERSION)
      =>
      non-empty-string?)

(fact "engine factory knows its supported extensions, including for *.js files"
      (.getExtensions (v8.jsr223.V8ScriptEngineFactory.))
      =>
      (fn [exts]
        (some (partial = "js") exts)))

(fact "engine factory knows its supported mime types, including application/javascript and text/javascript"
      (.getMimeTypes (v8.jsr223.V8ScriptEngineFactory.))
      =>
      (fn [mimes]
        (and (some (partial = "application/javascript") mimes)
             (some (partial = "text/javascript") mimes))))

(fact "engine factory produces a compliant engine"
      (.getScriptEngine (v8.jsr223.V8ScriptEngineFactory.))
      =>
      (partial instance? javax.script.ScriptEngine))

(fact "engine factory knows how to call a Java method from JS"
      (.getMethodCallSyntax (v8.jsr223.V8ScriptEngineFactory.) "java.lang.Math" "max" (into-array String ["1" "2"]))
      =>
      "java.lang.Math.max(1,2)")

(fact "script engine can eval correctly"
      (.eval (v8.jsr223.V8ScriptEngine.) "123")
      =>
      "123")

(fact "script engine can eval readFile"
      (.eval (v8.jsr223.V8ScriptEngine.) "readFile('test/v8/test.txt');")
      =>
      "123\n")

(fact "multiple script engines can work in parallel"
      (pmap #(.eval (v8.jsr223.V8ScriptEngine.) %) (repeat 20 "(function(){ return 5; })();"))
      =>
      (repeat 20 "5"))

(fact "multiple script executions can work with the same context"
      (let [engine (v8.jsr223.V8ScriptEngine.)]
        (.eval engine "x = 17; y = {a: 6};")
        (.eval engine "x;") => "17"
        (.eval engine "y.a;") => "6"))
