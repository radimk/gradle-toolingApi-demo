package org.gradle.sample.plugin.dependencies;

import org.gradle.api.Project;
import org.gradle.tooling.provider.model.ToolingModelBuilder;

public class ConfigurationDependenciesModelBuilder implements ToolingModelBuilder {
    @Override
    public boolean canBuild(String modelName) {
        return modelName.equals(ConfigurationDependenciesModel.class.getName());
    }

    @Override
    public Object buildAll(String s, Project project) {
        return new DefaultDependenciesModel();
    }
}
