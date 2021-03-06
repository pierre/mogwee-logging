/*
 * Copyright 2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.mogwee.logging;

import org.apache.log4j.Level;

public class Logger
{
    private static final Logger LOG = new Logger(org.apache.log4j.Logger.getLogger(Logger.class.getName()));

    private final org.apache.log4j.Logger log4j;

    /**
     * Returns a logger for the calling class.
     * <p/>
     * Specifically, throws and catches an exception, then uses the stack trace to determine the calling class.
     * The fully-qualified name of that class is used to get a Log4J logger, when is then wrapped.
     * Typical usage is to use this method to initialize a static member variable, e.g.,
     * {@code private static final Logger LOG = Logger.getLogger();}
     * <p/>
     * As this is not the sort of thing you want (or need) to be doing hundreds of times a second,
     * a warning is logged if this method is not called from a static constructor.
     *
     * @return a logger
     */
    public static Logger getLogger()
    {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        // stacktrace[0] is getStackTrace()
        // stacktrace[1] is getLogger()
        StackTraceElement element = stacktrace[2];
        String name = element.getClassName();

        if (!"<clinit>".equals(element.getMethodName())) {
            LOG.warnf(
                "Logger %s wasn't allocated in static constructor -- did you forget to make the field static? (%s:%s)",
                name,
                element.getFileName(),
                element.getLineNumber()
            );
        }

        return new Logger(org.apache.log4j.Logger.getLogger(name));
    }

    private Logger(org.apache.log4j.Logger log4j)
    {
        this.log4j = log4j;
    }

    /**
     * Logs a formatted message and stack trace if DEBUG logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string
     */
    public final void debugf(Throwable cause, String message, Object... args)
    {
        logf(Level.DEBUG, cause, message, args);
    }

    /**
     * Logs a formatted message if DEBUG logging is enabled.
     *
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string
     */
    public final void debugf(String message, Object... args)
    {
        logf(Level.DEBUG, null, message, args);
    }

    /**
     * @param cause   an exception to print stack trace of
     * @param message a message to log
     * @deprecated You meant to call {@link #debug(Throwable, String)}.
     */
    @Deprecated
    public final void debugf(Throwable cause, String message)
    {
        log4j.debug(message, cause);
    }

    /**
     * @param message a message to log
     * @deprecated You meant to call {@link #debug(String)}.
     */
    @Deprecated
    public final void debugf(String message)
    {
        log4j.debug(message);
    }

    /**
     * Logs a message and stack trace if DEBUG logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a message
     */
    public final void debug(Throwable cause, String message)
    {
        log4j.debug(message, cause);
    }

    /**
     * Logs a message if DEBUG logging is enabled.
     *
     * @param message a message
     */
    public final void debug(String message)
    {
        log4j.debug(message);
    }

    /**
     * Logs a formatted message and stack trace if INFO logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void infof(Throwable cause, String message, Object... args)
    {
        logf(Level.INFO, cause, message, args);
    }

    /**
     * Logs a formatted message if INFO logging is enabled.
     *
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void infof(String message, Object... args)
    {
        logf(Level.INFO, null, message, args);
    }

    /**
     * Logs a message and stack trace if INFO logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a message
     */
    public final void info(Throwable cause, String message)
    {
        log4j.info(message, cause);
    }

    /**
     * Logs a message if INFO logging is enabled.
     *
     * @param message a message
     */
    public final void info(String message)
    {
        log4j.info(message);
    }

    /**
     * @param cause   an exception to print stack trace of
     * @param message a message to log
     * @deprecated You meant to call {@link #info(Throwable, String)}.
     */
    @Deprecated
    public final void infof(Throwable cause, String message)
    {
        log4j.info(message, cause);
    }

    /**
     * @param message a message to log
     * @deprecated You meant to call {@link #info(String)}.
     */
    @Deprecated
    public final void infof(String message)
    {
        log4j.info(message);
    }

    /**
     * Logs a formatted message and stack trace if WARN logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void warnf(Throwable cause, String message, Object... args)
    {
        logf(Level.WARN, cause, message, args);
    }

    /**
     * Logs a formatted message if WARN logging is enabled.
     *
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void warnf(String message, Object... args)
    {
        logf(Level.WARN, null, message, args);
    }

    /**
     * Logs a message and stack trace if WARN logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a message
     */
    public final void warn(Throwable cause, String message)
    {
        log4j.warn(message, cause);
    }

    /**
     * Logs a message if WARN logging is enabled.
     *
     * @param message a message
     */
    public final void warn(String message)
    {
        log4j.warn(message);
    }

    /**
     * @param cause   an exception to print stack trace of
     * @param message a message to log
     * @deprecated You meant to call {@link #warn(Throwable, String)}.
     */
    @Deprecated
    public final void warnf(Throwable cause, String message)
    {
        log4j.warn(message, cause);
    }

    /**
     * @param message a message to log
     * @deprecated You meant to call {@link #warn(String)}.
     */
    @Deprecated
    public final void warnf(String message)
    {
        log4j.warn(message);
    }

    /**
     * Logs a formatted message and stack trace if ERROR logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void errorf(Throwable cause, String message, Object... args)
    {
        logf(Level.ERROR, cause, message, args);
    }

    /**
     * Logs a formatted message if ERROR logging is enabled.
     *
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void errorf(String message, Object... args)
    {
        logf(Level.ERROR, null, message, args);
    }

    /**
     * Logs a message and stack trace if ERROR logging is enabled.
     *
     * @param cause   an exception to print stack trace of
     * @param message a message
     */
    public final void error(Throwable cause, String message)
    {
        log4j.error(message, cause);
    }

    /**
     * Logs a message if ERROR logging is enabled.
     *
     * @param message a message
     */
    public final void error(String message)
    {
        log4j.error(message);
    }

    /**
     * @param cause   an exception to print stack trace of
     * @param message a message to log
     * @deprecated You meant to call {@link #error(Throwable, String)}.
     */
    @Deprecated
    public final void errorf(Throwable cause, String message)
    {
        log4j.error(message, cause);
    }

    /**
     * @param message a message to log
     * @deprecated You meant to call {@link #error(String)}.
     */
    @Deprecated
    public final void errorf(String message)
    {
        log4j.error(message);
    }

    /**
     * Logs a formatted message and stack trace if DEBUG logging is enabled
     * or a formatted message and exception description if INFO logging is enabled.
     *
     * @param cause   an exception to print stack trace of if DEBUG logging is enabled
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void infoDebugf(final Throwable cause, final String message, final Object... args)
    {
        logDebugf(Level.INFO, cause, message, args);
    }

    /**
     * Logs a message and stack trace if DEBUG logging is enabled
     * or a formatted message and exception description if INFO logging is enabled.
     *
     * @param cause   an exception to print stack trace of if DEBUG logging is enabled
     * @param message a message
     */
    public final void infoDebug(final Throwable cause, final String message)
    {
        logDebug(Level.INFO, cause, message);
    }

    /**
     * @param cause   an exception to print stack trace of if DEBUG level is enabled
     * @param message a message to log
     * @deprecated You meant to call {@link #infoDebug(Throwable, String)}.
     */
    @Deprecated
    public final void infoDebugf(Throwable cause, String message)
    {
        infoDebug(cause, message);
    }

    /**
     * Logs a formatted message and stack trace if DEBUG logging is enabled
     * or a formatted message and exception description if WARN logging is enabled.
     *
     * @param cause   an exception to print stack trace of if DEBUG logging is enabled
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void warnDebugf(final Throwable cause, final String message, final Object... args)
    {
        logDebugf(Level.WARN, cause, message, args);
    }

    /**
     * Logs a message and stack trace if DEBUG logging is enabled
     * or a formatted message and exception description if WARN logging is enabled.
     *
     * @param cause   an exception to print stack trace of if DEBUG logging is enabled
     * @param message a message
     */
    public final void warnDebug(final Throwable cause, final String message)
    {
        logDebug(Level.WARN, cause, message);
    }

    /**
     * @param cause   an exception to print stack trace of if DEBUG level is enabled
     * @param message a message to log
     * @deprecated You meant to call {@link #warnDebug(Throwable, String)}.
     */
    @Deprecated
    public final void warnDebugf(Throwable cause, String message)
    {
        warnDebug(cause, message);
    }

    /**
     * Logs a formatted message and stack trace if DEBUG logging is enabled
     * or a formatted message and exception description if ERROR logging is enabled.
     *
     * @param cause   an exception to print stack trace of if DEBUG logging is enabled
     * @param message a <a href="http://download.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax">format string</a>
     * @param args    arguments referenced by the format specifiers in the format string.
     */
    public final void errorDebugf(final Throwable cause, final String message, final Object... args)
    {
        logDebugf(Level.ERROR, cause, message, args);
    }

    /**
     * Logs a message and stack trace if DEBUG logging is enabled
     * or a formatted message and exception description if ERROR logging is enabled.
     *
     * @param cause   an exception to print stack trace of if DEBUG logging is enabled
     * @param message a message
     */
    public final void errorDebug(final Throwable cause, final String message)
    {
        logDebug(Level.ERROR, cause, message);
    }

    /**
     * @param cause   an exception to print stack trace of if DEBUG level is enabled
     * @param message a message to log
     * @deprecated You meant to call {@link #errorDebug(Throwable, String)}.
     */
    @Deprecated
    public final void errorDebugf(Throwable cause, String message)
    {
        errorDebug(cause, message);
    }

    private void logf(final Level level, final Throwable cause, final String message, final Object... args)
    {
        if (log4j.isEnabledFor(level)) {
            String renderedMessage;

            try {
                renderedMessage = String.format(message, args);
            }
            catch (RuntimeException e) {
                log4j.log(
                    level.toInt() < Level.WARN_INT ? Level.WARN : level,
                    String.format("Bogus format string: %s %s [%s] (%s)", level, message, safeToString(args), safeToString(e)),
                    cause
                );

                return;
            }

            log4j.log(level, renderedMessage, cause);
        }
    }

    private void logDebug(final Level level, final Throwable cause, final String message)
    {
        if (cause == null || log4j.isDebugEnabled()) {
            log4j.log(level, message, cause);
        }
        else if (log4j.isEnabledFor(level)) {
            String causeMessage = cause.getMessage();

            if (causeMessage == null) {
                causeMessage = cause.getClass().getName();
            }
            else {
                int index = causeMessage.indexOf("\n");

                if (index != -1) {
                    causeMessage = causeMessage.substring(0, index);
                }

                causeMessage = cause.getClass().getName() + ": " + causeMessage;
            }

            log4j.log(level, message + " (Switch to DEBUG for full stack trace): " + causeMessage);
        }
    }

    private void logDebugf(final Level level, final Throwable cause, final String message, final Object... args)
    {
        if (log4j.isEnabledFor(level)) {
            String renderedMessage;

            try {
                renderedMessage = String.format(message, args);
            }
            catch (RuntimeException e) {
                logDebug(
                    level.toInt() < Level.WARN_INT ? Level.WARN : level,
                    cause,
                    String.format("Bogus format string: %s %s [%s] (%s)", level, message, safeToString(args), safeToString(e))
                );

                return;
            }

            logDebug(level, cause, renderedMessage);
        }
    }

    private String safeToString(Object... args)
    {
        if (args == null) {
            return "null";
        }

        StringBuilder result = new StringBuilder(16 * args.length);

        for (Object arg : args) {
            if (result.length() > 0) {
                result.append(", ");
            }

            // guard against some object's toString() throwing an exception
            try {
                result.append(arg);
            }
            catch (RuntimeException e) {
                try {
                    result.append("toString():").append(e.toString());
                }
                catch (RuntimeException e2) {
                    // guard against some exception's toString() throwing an exception
                    result.append("???");
                }
            }
        }

        return result.toString();
    }
}
