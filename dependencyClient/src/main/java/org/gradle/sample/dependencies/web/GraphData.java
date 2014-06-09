package org.gradle.sample.dependencies.web;

import org.gradle.sample.plugin.dependencies.GraphLink;
import org.gradle.sample.plugin.dependencies.GraphNode;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class GraphData {
    private List<GraphNode> nodes;
    private List<GraphLink> links;

    public GraphData() {
    }

    public GraphData(List<GraphNode> nodes, List<GraphLink> links) {
        this.nodes = nodes;
        this.links = links;
    }

    public List<GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<GraphNode> nodes) {
        this.nodes = nodes;
    }

    public List<GraphLink> getLinks() {
        return links;
    }

    public void setLinks(List<GraphLink> links) {
        this.links = links;
    }
}
