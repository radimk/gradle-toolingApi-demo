package org.gradle.sample.dependencies.web;

import org.gradle.sample.dependencies.lib.ModelProvider;
import org.gradle.sample.plugin.dependencies.ConfigurationDependenciesModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("dependencies")
public class DependenciesResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DependenciesResource.class);

    private final ModelProvider modelProvider;

    public DependenciesResource() {
        modelProvider = ModelProvider.getDefault();
    }

    @GET
    @Produces("application/json")
    public GraphData getMyBean() {
        ConfigurationDependenciesModel model = modelProvider.getConfigurationDependencies();
        LOGGER.info("Retrieved dependencies: {}", model);
        GraphData graph = new GraphData(
                model.getNodes(),
                model.getEdges());
        return graph;
    }
}
