package org.gradle.sample;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.gradle.sample.dependencies.lib.ModelProvider;

public class Main {
    public static void main(String[] args) throws Exception {
        ModelProvider.getDefault().setProjectPath(System.getProperty("project.path"));
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/gradle/*");
        jerseyServlet.setInitOrder(1);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "org.gradle.sample.dependencies.web");

        String webDir = Main.class.getClassLoader().getResource("org/gradle/sample/dependencies/webresources").toExternalForm();
        ServletHolder staticServlet = context.addServlet(DefaultServlet.class,"/*");
        staticServlet.setInitParameter("resourceBase", webDir);
        staticServlet.setInitParameter("pathInfoOnly","true");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { context, new DefaultHandler() });
        server.setHandler(handlers);

        server.start();
        System.out.println("Jetty server started at http://localhost:8080/");
        server.join();
    }
}
