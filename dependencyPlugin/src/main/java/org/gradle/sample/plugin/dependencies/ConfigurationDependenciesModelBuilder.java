package org.gradle.sample.plugin.dependencies;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.result.ResolutionResult;
import org.gradle.api.tasks.diagnostics.internal.graph.nodes.RenderableDependency;
import org.gradle.api.tasks.diagnostics.internal.graph.nodes.RenderableModuleResult;
import org.gradle.tooling.provider.model.ToolingModelBuilder;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationDependenciesModelBuilder implements ToolingModelBuilder {
    @Override
    public boolean canBuild(String modelName) {
        return modelName.equals(ConfigurationDependenciesModel.class.getName());
    }

    @Override
    public Object buildAll(String s, Project project) {
        List<DefaultGraphNode> nodes = new ArrayList<DefaultGraphNode>();
        List<DefaultGraphLink> edges = new ArrayList<DefaultGraphLink>();
        buildRecursively(project, nodes, edges);

        return new DefaultDependenciesModel<DefaultGraphNode, DefaultGraphLink>(nodes, edges);
    }

    private void buildRecursively(Project project, List<DefaultGraphNode> nodes, List<DefaultGraphLink> edges) {
        for (Project child : project.getSubprojects()) {
            buildRecursively(child, nodes, edges);
        }
        // new DependencyReportRenderer()
        DefaultGraphNode projectNode = new DefaultGraphNode(project.getPath());
        nodes.add(projectNode);
        for (Configuration c : project.getConfigurations()) {
            if (!"testCompile".equals(c.getName())) {
                continue;
            }
            DefaultGraphNode configNode = new DefaultGraphNode(c.getName());
            nodes.add(configNode);
            edges.add(new DefaultGraphLink(projectNode.getName(), configNode.getName()));
            ResolutionResult result = c.getIncoming().getResolutionResult();
            RenderableDependency root = new RenderableModuleResult(result.getRoot());
            buildConfiguration(root, configNode, nodes, edges);
        }
    }

    private void buildConfiguration(RenderableDependency root, DefaultGraphNode parentNode,
                                    List<DefaultGraphNode> nodes, List<DefaultGraphLink> edges) {
        for (RenderableDependency child : root.getChildren()) {
            DefaultGraphNode childNode = new DefaultGraphNode(child.getId().toString());
            if (!nodes.contains(childNode)) {
                nodes.add(childNode);
            }
            edges.add(new DefaultGraphLink(parentNode.getName(), childNode.getName()));
            buildConfiguration(child, childNode, nodes, edges);
        }
    }
}
