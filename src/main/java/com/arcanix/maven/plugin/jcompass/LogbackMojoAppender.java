package com.arcanix.maven.plugin.jcompass;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;
import org.apache.maven.plugin.logging.Log;

/**
 * @author ricardjp@arcanix.com (Jean-Philippe Ricard)
 */
public final class LogbackMojoAppender extends AppenderBase<ILoggingEvent> {

    public synchronized void append(ILoggingEvent eventObject) {

        Log log = (Log) this.getContext().getObject("maven.log");

        switch (eventObject.getLevel().toInt()) {
            case Level.TRACE_INT:
            case Level.DEBUG_INT:
                log.debug(eventObject.getFormattedMessage());
                break;
            case Level.WARN_INT:
                log.warn(eventObject.getFormattedMessage());
                break;
            case Level.ERROR_INT:
                log.error(eventObject.getFormattedMessage());
                break;
            case Level.INFO_INT:
            case Level.ALL_INT:
                log.info(eventObject.getFormattedMessage());
                break;
            case Level.OFF_INT:
                // do nothing
        }

    }

}
