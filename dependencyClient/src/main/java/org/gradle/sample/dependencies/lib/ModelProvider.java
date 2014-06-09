package org.gradle.sample.dependencies.lib;

import org.gradle.sample.plugin.dependencies.ConfigurationDependenciesModel;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class ModelProvider {

    private static final ModelProvider INSTANCE = new ModelProvider();

    public static ModelProvider getDefault() {
        return INSTANCE;
    }

    private String path;
    private ProjectConnection connection;

    private ModelProvider() {
    }

    public void setProjectPath(String path) {
        this.path = path;
        connection = null;
    }

    private synchronized void initConnection() {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(path));

        connection = connector.connect();
    }
    public ConfigurationDependenciesModel getConfigurationDependencies() {
        initConnection();
        // Load the custom model for the project
        ConfigurationDependenciesModel model = connection.getModel(ConfigurationDependenciesModel.class);
        return model;
    }
}
