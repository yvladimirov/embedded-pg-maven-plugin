package com.github.yvladimirov.maven.embededpg.goal;

import com.github.yvladimirov.maven.embededpg.pg.PgManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * yvladimirov on 26.03.18.
 */
@Mojo(name = "start")
public class StartGoalMojo extends AbstractMojo {
    @Parameter(property = "dbName", required = true)
    private String dbName;

    @Parameter(defaultValue = "15432", property = "dbPort", required = true)
    private int dbPort;

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;


    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Postgresql started... port: " + dbPort);
        PgManager.getInstance().start(dbPort);
        getLog().info("Postgresql start");
        getLog().info("Create DB: " + dbName);
        PgManager.getInstance().createDB(dbName);
        getLog().info("Populate system property");
        PgManager.getInstance().populateProperty(project, dbName);
    }
}
