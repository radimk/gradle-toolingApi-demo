package org.gradle.sample.plugin.dependencies;

import java.io.Serializable;
import java.util.List;

/**
 * This is the implementation of the custom tooling model. It must be serializable and must have methods and properties that
 * are compatible with the custom tooling model interface. It may or may not implement the custom tooling model interface.
 */
public class DefaultDependenciesModel<N extends GraphNode, E extends GraphLink> implements Serializable {
    private List<N> nodes;
    private List<E> edges;

    public DefaultDependenciesModel(List<N> nodes, List<E> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    public List<N> getNodes() {
        return nodes;
    }

    public List<E> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "DefaultDependenciesModel{" +
                "nodes=" + nodes +
                ", edges=" + edges +
                '}';
    }
}
