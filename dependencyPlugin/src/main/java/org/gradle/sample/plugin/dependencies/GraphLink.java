package org.gradle.sample.plugin.dependencies;

import java.io.Serializable;

public interface GraphLink extends Serializable {
    String getSource();
    void setSource(String source);

    String getTarget();
    void setTarget(String target);
}
