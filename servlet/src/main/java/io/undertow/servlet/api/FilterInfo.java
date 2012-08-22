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

package io.undertow.servlet.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import io.undertow.servlet.UndertowServletMessages;

/**
 * @author Stuart Douglas
 */
public class FilterInfo {

    private final Class<? extends Filter> filterClass;
    private final String name;
    private final InstanceFactory instanceFactory;
    private final List<Mapping> mappings;
    private final Map<String, String> initParams;
    private final boolean asyncSupported;

    FilterInfo(final String name, final Class<? extends Filter> filterClass, final InstanceFactory instanceFactory, final List<Mapping> mappings, final Map<String, String> initParams, final boolean asyncSupported) {
        if (name == null) {
            throw UndertowServletMessages.MESSAGES.paramCannotBeNull("name");
        }
        if (filterClass == null) {
            throw UndertowServletMessages.MESSAGES.paramCannotBeNull("filterClass");
        }
        if (mappings == null) {
            throw UndertowServletMessages.MESSAGES.paramCannotBeNull("mappings");
        }

        if (initParams == null) {
            throw UndertowServletMessages.MESSAGES.paramCannotBeNull("initParams");
        }
        this.name = name;
        this.filterClass = filterClass;
        this.instanceFactory = instanceFactory;
        this.mappings = Collections.unmodifiableList(new ArrayList<Mapping>(mappings));
        this.initParams = Collections.unmodifiableMap(new HashMap<String, String>(initParams));
        this.asyncSupported = asyncSupported;
    }

    public FilterInfoBuilder copy() {
        FilterInfoBuilder builder = new FilterInfoBuilder()
                .setName(name)
                .setFilterClass(filterClass)
                .setInstanceFactory(instanceFactory);
        builder.mappings.addAll(mappings);
        builder.initParams.putAll(initParams);
        return builder;
    }

    public String getName() {
        return name;
    }

    public Class<? extends Filter> getFilterClass() {
        return filterClass;
    }

    public InstanceFactory getInstanceFactory() {
        return instanceFactory;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public Map<String, String> getInitParams() {
        return initParams;
    }

    public static FilterInfoBuilder builder() {
        return new FilterInfoBuilder();
    }

    public static class FilterInfoBuilder {
        private Class<? extends Filter> filterClass;
        private String name;
        private InstanceFactory instanceFactory;
        private volatile boolean asyncSupported;
        private final List<Mapping> mappings = new ArrayList<Mapping>();
        private final Map<String, String> initParams = new HashMap<String, String>();

        FilterInfoBuilder() {

        }

        public FilterInfo build() {
            return new FilterInfo(name, filterClass, instanceFactory, mappings, initParams, asyncSupported);
        }

        public String getName() {
            return name;
        }

        public FilterInfoBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public Class<? extends Filter> getFilterClass() {
            return filterClass;
        }

        public FilterInfoBuilder setFilterClass(final Class<? extends Filter> filterClass) {
            this.filterClass = filterClass;
            return this;
        }

        public InstanceFactory getInstanceFactory() {
            return instanceFactory;
        }

        public FilterInfoBuilder setInstanceFactory(final InstanceFactory instanceFactory) {
            this.instanceFactory = instanceFactory;
            return this;
        }

        public List<Mapping> getMappings() {
            return mappings;
        }

        public FilterInfoBuilder addUrlMapping(final String mapping) {
            mappings.add(new Mapping(MappingType.URL, mapping, null));
            return this;
        }

        public FilterInfoBuilder addUrlMapping(final String mapping , DispatcherType dispatcher) {
            mappings.add(new Mapping(MappingType.URL, mapping, dispatcher));
            return this;
        }

        public FilterInfoBuilder addServletNameMapping(final String mapping) {
            mappings.add(new Mapping(MappingType.SERVLET, mapping, null));
            return this;
        }

        public FilterInfoBuilder addServletNameMapping(final String mapping, final DispatcherType dispatcher) {
            mappings.add(new Mapping(MappingType.SERVLET, mapping, dispatcher));
            return this;
        }

        public Map<String, String> getInitParams() {
            return initParams;
        }

        public boolean isAsyncSupported() {
            return asyncSupported;
        }

        public FilterInfoBuilder setAsyncSupported(final boolean asyncSupported) {
            this.asyncSupported = asyncSupported;
            return this;
        }
    }


    public static enum MappingType {
        URL,
        SERVLET;
    }

    public static class Mapping {
        private final MappingType mappingType;
        private final String mapping;
        private final DispatcherType dispatcher;

        public Mapping(final MappingType mappingType, final String mapping, final DispatcherType dispatcher) {
            this.mappingType = mappingType;
            this.mapping = mapping;
            this.dispatcher = dispatcher;
        }

        public MappingType getMappingType() {
            return mappingType;
        }

        public String getMapping() {
            return mapping;
        }

        public DispatcherType getDispatcher() {
            return dispatcher;
        }
    }

}