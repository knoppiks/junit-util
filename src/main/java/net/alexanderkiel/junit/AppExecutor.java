/******************************************************************************
 * Copyright 2010 Alexander Kiel                                              *
 *                                                                            *
 * Licensed under the Apache License, Version 2.0 (the "License");            *
 * you may not use this file except in compliance with the License.           *
 * You may obtain a copy of the License at                                    *
 *                                                                            *
 *     http://www.apache.org/licenses/LICENSE-2.0                             *
 *                                                                            *
 * Unless required by applicable law or agreed to in writing, software        *
 * distributed under the License is distributed on an "AS IS" BASIS,          *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   *
 * See the License for the specific language governing permissions and        *
 * limitations under the License.                                             *
 ******************************************************************************/

package net.alexanderkiel.junit;

import java.io.IOException;

/**
 * @author Alexander Kiel
 */
public interface AppExecutor {

    /**
     * Adds one command-line argument to the application of this app executor.
     *
     * @param arg the command-line argument
     * @see #executeApp()
     */
    void addArg(String arg);

    /**
     * Adds command-line arguments to the application of this app executor.
     *
     * @param args one or more command-line arguments
     * @see #executeApp()
     */
    void addArgs(String... args);

    /**
     * Executes the application of this app executor.
     *
     * @throws IOException           if an I/O error occurs.
     * @throws IllegalStateException if there was no a command specified.
     */
    void executeApp() throws IOException;

    /**
     * Asserts that the application of this app executor outputs the given line on {@link System#out STDOUT}.
     * <p/>
     * Please specify the line without any line terminating characters.
     *
     * @param expectedLine the expected line of output.
     * @throws IOException if an I/O error occurs.
     */
    void assertLineOfOutput(String expectedLine) throws IOException;

    /**
     * Asserts that the application of this app executor outputs a line on {@link System#out STDOUT} which matches the
     * given {@link java.util.regex.Pattern regular expression}.
     * <p/>
     * Please specify the regular expression without any line terminating characters.
     *
     * @param expectedLineRegExp the regular expression used to match the line of output.
     * @throws IOException if an I/O error occurs.
     */
    void assertLineOfOutputMatches(String expectedLineRegExp) throws IOException;

    /**
     * Asserts that the application of this app executor outputs the given expectedLine on {@link System#err STDERR}.
     * <p/>
     * Please specify the expectedLine without any expectedLine terminating characters.
     *
     * @param expectedLine the expected expectedLine of output.
     * @throws IOException if an I/O error occurs.
     */
    void assertLineOfError(String expectedLine) throws IOException;

    /**
     * Asserts that the application of this app executor outputs a line on {@link System#err STDERR} which matches the
     * given {@link java.util.regex.Pattern regular expression}.
     * <p/>
     * Please specify the regular expression without any line terminating characters.
     *
     * @param expectedLineRegExp the regular expression used to match the line of output.
     * @throws IOException if an I/O error occurs.
     */
    void assertLineOfErrorMatches(String expectedLineRegExp) throws IOException;

    /**
     * Asserts that there is no more output on {@link System#out STDOUT} of the application of this app executor.
     *
     * @throws IOException if an I/O error occurs.
     */
    void assertNoMoreOutput() throws IOException;

    /**
     * Asserts that there is no more output on {@link System#err STDERR} of the application of this app executor.
     *
     * @throws IOException if an I/O error occurs.
     */
    void assertNoMoreErrors() throws IOException;

    /**
     * Asserts that the application of this app executor terminates with a status code of zero.
     *
     * @throws InterruptedException if the current thread is {@link Thread#interrupt() interrupted} by another thread
     *                              while it is waiting for the application to terminate.
     */
    void assertNormalExit() throws InterruptedException;

    /**
     * Asserts that the application of this app executor terminates with the given status code.
     *
     * @param expectedStatusCode the expected status code.
     * @throws InterruptedException if the current thread is {@link Thread#interrupt() interrupted} by another thread
     *                              while it is waiting for the application to terminate.
     */
    void assertExit(int expectedStatusCode) throws InterruptedException;

    /**
     * Kills the application.
     * <p/>
     * This may be useful if you test a daemon process which will not exit itself.
     */
    void destroy();
}
