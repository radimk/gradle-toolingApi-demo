package org.gradle.sample.plugin.dependencies;

import java.io.Serializable;

public interface GraphNode extends Serializable {
    String getName();

    void setName(String name);
}
