(defproject minimal-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[bidi "2.1.6"]
                 [camel-snake-kebab "0.4.2"]
                 [integrant "0.8.0"]
                 [metosin/ring-http-response "0.9.3"]
                 [org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [ring/ring-json "0.5.1"]]
  :main ^:skip-aot minimal-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:source-paths ["dev/src"]
                   :resource-paths ["dev/resources"]
                   :dependencies [[clj-http "3.11.0"]
                                  [integrant/repl "0.3.2"]
                                  [orchestra "2020.09.18-1"]
                                  [pjstadig/humane-test-output "0.10.0"]]
                   :plugins [[jonase/eastwood "0.3.12"]
                              [lein-cloverage "1.2.1"]
                              [lein-kibit "0.1.8"]]
                   :aliases {"test-coverage" ^{:doc "Execute cloverage."}
                             ["cloverage" "--ns-exclude-regex" "^(:?dev|user)$" "--codecov" "--junit"]
                             "lint" ^{:doc "Execute eastwood and kibit."}
                             ["do"
                              ["eastwood" "{:source-paths [\"src\"]
                                            :test-paths []}"]
                              ["kibit"]]}
                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]}
             :repl {:repl-options {:init-ns user}}})
