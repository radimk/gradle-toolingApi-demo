package org.gradle.sample.plugin.dependencies;

import java.util.List;

/**
 * This is a custom tooling model serving information about resolved dependencies.
 */
public interface ConfigurationDependenciesModel {

    List<GraphNode> getNodes();

    List<GraphLink> getEdges();
}
