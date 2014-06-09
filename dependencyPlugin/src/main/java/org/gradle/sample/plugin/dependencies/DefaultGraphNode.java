package org.gradle.sample.plugin.dependencies;

import java.io.Serializable;

public class DefaultGraphNode implements GraphNode {
    private String name;

    public DefaultGraphNode() {
    }

    public DefaultGraphNode(String name) {

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultGraphNode that = (DefaultGraphNode) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 5;
    }

    @Override
    public String toString() {
        return "DefaultGraphNode{" +
                "name='" + name + '\'' +
                '}';
    }
}
