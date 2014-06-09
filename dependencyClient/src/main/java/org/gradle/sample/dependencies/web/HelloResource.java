package org.gradle.sample.dependencies.web;

import org.gradle.sample.dependencies.lib.ModelProvider;
import org.gradle.sample.plugin.dependencies.ConfigurationDependenciesModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("hello")
public class HelloResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

    private final ModelProvider modelProvider;

    public HelloResource() {
        modelProvider = ModelProvider.getDefault();
    }

    @GET
    public String hello() {
        ConfigurationDependenciesModel model = modelProvider.getConfigurationDependencies();
        LOGGER.info("Retrieved dependencies: {}", model);
        return model.sayHello();
    }
}
