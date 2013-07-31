package com.arcanix.maven.plugin.jcompass;

import com.arcanix.jcompass.CompassCompiler;
import com.arcanix.jcompass.CompassWatcher;
import org.apache.commons.logging.LogFactory;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * @author ricardjp@arcanix.com (Jean-Philippe Ricard)
 */
@Mojo(name = "watch")
public class WatchMojo extends AbstractMojo {

    @Parameter( property = "update-stylesheets.configFile")
    private String configFile;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        // suppress logging from commons-logging (dependency via commons-vfs2)
        LogFactory.getFactory().setAttribute(
                "org.apache.commons.logging.Log",
                "org.apache.commons.logging.impl.NoOpLog");

        try {
            CompassWatcher watcher = new CompassWatcher(new CompassCompiler(
                    new File(this.configFile), new MavenCompassNotifier(getLog())));
            watcher.watch();
            getLog().info("Watching for changes...");
            synchronized (watcher) {
                watcher.wait();
            }
        } catch (InterruptedException e) {
            getLog().info("Watcher stopped");
        } catch (Exception e) {
            throw new MojoFailureException(e.getMessage());
        }

    }
}
