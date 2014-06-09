# Tooling API demo

A sample project showing how to create a custom model that can be applied through a plugin
and later accessed through Gradle Tooling API to visualize dependencies of some configuration.

It consists of three parts:

* `dependencyPlugin` is the Gradle plugin that registers [ToolingModelBuilder](http://www.gradle.org/docs/current/javadoc/org/gradle/tooling/provider/model/ToolingModelBuilder.html).
* `dependencyClient` is a simple application that runs Jetty to serve a page using D3.js to render model created by plugin.
* `sampleBuild` something to test the plugin+client pair.

## Running

Execute

`./gradlew :dependencyPlugin:publish`

This will publish plugin JAR into Ivy directory to make it accessible to `sampleBuild`

Run the application with

`./gradlew :dependencyClient:run`

and open http://localhost:8080/ in the browser.