/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.undertow.servlet;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import io.undertow.servlet.api.DeploymentManager;
import org.jboss.logging.Cause;
import org.jboss.logging.Message;
import org.jboss.logging.MessageBundle;
import org.jboss.logging.Messages;

/**
 * messages start at 10000
 *
 * @author Stuart Douglas
 */
@MessageBundle(projectCode = "UNDERTOW")
public interface UndertowServletMessages {

    UndertowServletMessages MESSAGES = Messages.getBundle(UndertowServletMessages.class);

    @Message(id = 10000, value = "%s cannot be null")
    IllegalArgumentException paramCannotBeNull(String param);

    @Message(id = 10001, value = "%s cannot be null for %s named %s")
    IllegalArgumentException paramCannotBeNull(String param, String componentType, String name);

    @Message(id = 10002, value = "Deployments can only be removed when in undeployed state, but state was %s")
    IllegalStateException canOnlyRemoveDeploymentsWhenUndeployed(DeploymentManager.State state);

    @Message(id = 10003, value = "Cannot call getInputStream(), getReader() already called")
    IllegalStateException getReaderAlreadyCalled();

    @Message(id = 10004, value = "Cannot call getReader(), getInputStream() already called")
    IllegalStateException getInputStreamAlreadyCalled();

    @Message(id = 10005, value = "Cannot call getOutputStream(), getWriter() already called")
    IllegalStateException getWriterAlreadyCalled();

    @Message(id = 10006, value = "Cannot call getWriter(), getOutputStream() already called")
    IllegalStateException getOutputStreamAlreadyCalled();

    @Message(id = 10007, value = "Two servlets specified with same mapping %s")
    IllegalArgumentException twoServletsWithSameMapping(String path);

    @Message(id = 10008, value = "Header %s cannot be converted to a date")
    IllegalArgumentException headerCannotBeConvertedToDate(String header);

    @Message(id = 10009, value = "Servlet %s of type %s does not implement javax.servlet.Servlet")
    IllegalArgumentException servletMustImplementServlet(String name, Class<? extends Servlet> servletClass);

    @Message(id = 10010, value = "%s of type %s must have a default constructor")
    IllegalArgumentException componentMustHaveDefaultConstructor(String componentType, Class<?> componentClass);

    @Message(id = 10011, value = "Filter %s of type %s does not implement javax.servlet.Filter")
    IllegalArgumentException filterMustImplementFilter(String name, Class<? extends Filter> filterClass);

    @Message(id = 10012, value = "Listener class %s must implement at least one listener interface")
    IllegalArgumentException listenerMustImplementListenerClass(Class<?> listenerClass);

    @Message(id = 10013, value = "Could not instantiate %s")
    ServletException couldNotInstantiateComponent(String name, @Cause Exception e);

    @Message(id = 10014, value = "Could not load class %s")
    RuntimeException cannotLoadClass(String className, @Cause Exception e);
}