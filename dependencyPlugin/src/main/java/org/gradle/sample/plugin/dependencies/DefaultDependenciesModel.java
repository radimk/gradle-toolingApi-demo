package org.gradle.sample.plugin.dependencies;

import java.io.Serializable;
import java.util.List;

/**
 * This is the implementation of the custom tooling model. It must be serializable and must have methods and properties that
 * are compatible with the custom tooling model interface. It may or may not implement the custom tooling model interface.
 */
public class DefaultDependenciesModel implements Serializable {
    public String sayHello() {
        return "Hello world!";
    }
}
