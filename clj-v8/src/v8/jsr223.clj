(ns v8.jsr223)

(gen-class
 :name v8.jsr223.V8ScriptEngine
 :extends javax.script.AbstractScriptEngine
 :impl-ns v8.jsr223.script-engine
 :main false)

(gen-class
 :name v8.jsr223.V8ScriptEngineFactory
 :implements [javax.script.ScriptEngineFactory]
 :impl-ns v8.jsr223.script-engine-factory
 :main false)
