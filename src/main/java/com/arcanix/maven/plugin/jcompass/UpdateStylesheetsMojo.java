package com.arcanix.maven.plugin.jcompass;

import ch.qos.logback.classic.LoggerContext;
import com.arcanix.jcompass.CompassCompiler;
import com.arcanix.jcompass.CompassCompilerException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author ricardjp@arcanix.com (Jean-Philippe Ricard)
 */
@Mojo(name = "update-stylesheets")
public class UpdateStylesheetsMojo extends AbstractMojo {

    @Parameter( property = "update-stylesheets.configFile")
    private String configFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        // TODO find  way to register automatically if possible
        // registering slf4j - maven logging bridge
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.putObject("maven.log", this.getLog());

        try {
            new CompassCompiler(new File(this.configFile)).compile();
        } catch (Exception e) {
            throw new MojoFailureException(e.getMessage());
        }

    }
}
