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
package io.undertow.websockets.function;

import io.undertow.websockets.ChannelFunction;
import io.undertow.websockets.wrapper.ChannelWrapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
public class ChannelFunctionReadableByteChannel extends ChannelWrapper<ReadableByteChannel> implements ReadableByteChannel {

    private final ChannelFunction[] functions;

    public ChannelFunctionReadableByteChannel(ReadableByteChannel channel, ChannelFunction... functions) {
        super(channel);
        this.functions = functions;
    }

    @Override
    public int read(ByteBuffer dst) throws IOException {
        int r = channel.read(dst);
        for (ChannelFunction func: functions) {
            func.afterRead(dst);
        }
        return r;
    }
}