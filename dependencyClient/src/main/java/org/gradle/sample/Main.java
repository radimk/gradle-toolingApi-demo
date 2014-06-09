package org.gradle.sample;

import org.gradle.sample.plugin.dependencies.ConfigurationDependenciesModel;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        String projectPath = System.getProperty("project.path");
        ProjectConnection connection = GradleConnector.newConnector()
                .forProjectDirectory(new File(projectPath))
                .connect();

        try {
            ConfigurationDependenciesModel model = connection.getModel(ConfigurationDependenciesModel.class);
            System.out.println("Model: " + model.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        System.exit(0);
    }
}
