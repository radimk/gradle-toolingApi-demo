package org.gradle.sample.plugin.dependencies;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.tooling.provider.model.ToolingModelBuilderRegistry;

import javax.inject.Inject;

/**
 * A plugin that exposes a custom tooling model.
 */
public class DependenciesPlugin implements Plugin<Project> {
    private final ToolingModelBuilderRegistry registry;

    /**
     * Need to use a {@link ToolingModelBuilderRegistry} to register the custom tooling model, so inject this into
     * the constructor.
     */
    @Inject
    public DependenciesPlugin(ToolingModelBuilderRegistry registry) {
        this.registry = registry;
    }

    public void apply(Project project) {
        // Register a builder for the custom tooling model
        registry.register(new ConfigurationDependenciesModelBuilder());
    }
}
