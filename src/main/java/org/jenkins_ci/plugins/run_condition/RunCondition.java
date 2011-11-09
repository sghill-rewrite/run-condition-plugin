/*
 * The MIT License
 *
 * Copyright (C) 2011 by Anthony Robinson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkins_ci.plugins.run_condition;

import hudson.DescriptorExtensionList;
import hudson.ExtensionList;
import hudson.ExtensionPoint;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Describable;
import hudson.model.Descriptor;
import hudson.model.Hudson;

import java.io.IOException;

public abstract class RunCondition implements Describable<RunCondition>, ExtensionPoint {

    public static DescriptorExtensionList<RunCondition, RunConditionDescriptor> all() {
        return Hudson.getInstance().<RunCondition, RunConditionDescriptor>getDescriptorList(RunCondition.class);
    }

    public abstract boolean runPrebuild(final AbstractBuild<?, ?> build, final BuildListener listener) throws Exception;
    public abstract boolean runPerform(final AbstractBuild<?, ?> build, final BuildListener listener) throws Exception;

    public RunConditionDescriptor getDescriptor() {
        return (RunConditionDescriptor)Hudson.getInstance().getDescriptor(getClass());
    }

    public static abstract class RunConditionDescriptor extends Descriptor<RunCondition> {

        protected RunConditionDescriptor() { }

        protected RunConditionDescriptor(Class<? extends RunCondition> clazz) {
            super(clazz);
        }

    }

}