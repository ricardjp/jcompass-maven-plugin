package com.arcanix.maven.plugin.jcompass;

import com.arcanix.jcompass.CompassNotifier;
import org.apache.maven.plugin.logging.Log;

import java.io.File;

/**
 * @author ricardjp@arcanix.com (Jean-Philippe Ricard)
 */
public final class MavenCompassNotifier implements CompassNotifier {

    private final Log log;

    public MavenCompassNotifier(Log log) {
        this.log = log;
    }

    @Override
    public void onCompilationStarted() {
        this.log.info("Updating stylesheets...");
    }

    @Override
    public void onCompilationEnded() {
        this.log.info("Done updating stylesheets");
    }

    @Override
    public void onFileChanged(File file) {
        this.log.info("File change detected to: " + file.getAbsolutePath());
    }

    @Override
    public void onFileCreated(File file) {
        this.log.info("File creation detected: " + file.getAbsolutePath());
    }

    @Override
    public void onFileDeleted(File file) {
        this.log.info("File deletion detected to: " + file.getAbsolutePath());
    }

    @Override
    public void onStylesheetSaved(File file) {
        this.log.info("  - File: " + file.getAbsolutePath() + " successfully updated");
    }

    @Override
    public void onSpriteSaved(File file) {
        this.log.info("  - Sprite: " + file.getAbsolutePath() + " successfully updated");
    }

    @Override
    public void onStylesheetError(File file, String message) {
        this.log.error("  - An error occured while updating file: " + file.getAbsolutePath() + ": " + message);
    }
}
