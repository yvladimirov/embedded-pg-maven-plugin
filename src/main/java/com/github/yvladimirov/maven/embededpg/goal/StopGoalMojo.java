package com.github.yvladimirov.maven.embededpg.goal;

import com.github.yvladimirov.maven.embededpg.pg.PgManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * yvladimirov on 26.03.18.
 */
@Mojo(name = "stop")
public class StopGoalMojo extends AbstractMojo {
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Postgresql stoping...");
        PgManager.getInstance().stop();
        getLog().info("Postgresql stop");
    }
}
