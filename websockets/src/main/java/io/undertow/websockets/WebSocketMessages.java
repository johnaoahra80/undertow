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

package io.undertow.websockets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jboss.logging.Message;
import org.jboss.logging.MessageBundle;
import org.jboss.logging.Messages;

/**
 * start at 20000
 * @author Stuart Douglas
 */
@MessageBundle(projectCode = "UNDERTOW")
public interface WebSocketMessages {

    WebSocketMessages MESSAGES = Messages.getBundle(WebSocketMessages.class);

    @Message(id = 2001, value = "Not a WebSocket handshake request: missing upgrade in the headers")
    WebSocketHandshakeException missingUpgradeHeaders();

    @Message(id = 2002, value = "Channel is closed")
    IOException channelClosed();

    @Message(id = 2003, value = "Text frame contains non UTF-8 data")
    UnsupportedEncodingException invalidTextFrameEncoding();

}