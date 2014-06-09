package org.gradle.sample.dependencies.lib;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.io.Resources;
import org.gradle.sample.plugin.dependencies.ConfigurationDependenciesModel;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ModelProvider {

    private static final ModelProvider INSTANCE = new ModelProvider();

    public static ModelProvider getDefault() {
        return INSTANCE;
    }

    private String path;
    private ProjectConnection connection;
    private String initScriptPath;

    private ModelProvider() {
    }

    public void setProjectPath(String path) {
        this.path = path;
        connection = null;
    }

    private synchronized void initConnection() throws IOException, URISyntaxException {
        GradleConnector connector = GradleConnector.newConnector();
        connector.forProjectDirectory(new File(path));

        connection = connector.connect();

        Path pluginJarPath = Paths.get(ConfigurationDependenciesModel.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        String pluginDirPath = pluginJarPath.toFile().getParentFile().getAbsolutePath();
        String script = Resources.toString(ModelProvider.class.getResource("init-dependencies.gradle"), Charsets.UTF_8);
        script = script.replace("<PLUGIN_DIR>", pluginDirPath);

        Path initScript = Files.createTempFile("init-dependencies", ".gradle");
        initScriptPath = initScript.toFile().getAbsolutePath();
        Files.write(initScript, script.getBytes(Charsets.UTF_8));
        initScript.toFile().deleteOnExit();
    }

    public ConfigurationDependenciesModel getConfigurationDependencies() {
        try {
            initConnection();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
        // Load the custom model for the project
        ConfigurationDependenciesModel model = connection.model(ConfigurationDependenciesModel.class)
                .withArguments("--init-script", initScriptPath)
                .get();
        return model;
    }
}
