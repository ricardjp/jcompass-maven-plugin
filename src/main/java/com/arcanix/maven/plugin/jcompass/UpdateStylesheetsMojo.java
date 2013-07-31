package com.arcanix.maven.plugin.jcompass;

import com.arcanix.jcompass.CompassCompiler;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

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
        try {
            new CompassCompiler(new File(this.configFile), new MavenCompassNotifier(getLog())).compile();
        } catch (Exception e) {
            throw new MojoFailureException(e.getMessage());
        }

    }
}
