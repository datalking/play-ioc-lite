package com.github.datalking.context.annotation;

import com.github.datalking.annotation.meta.MethodMetadata;

/**
 * @author yaoo on 4/13/18
 */
public class BeanMethod {

    protected final MethodMetadata metadata;
    protected final ConfigurationClass configurationClass;


    public BeanMethod(MethodMetadata metadata, ConfigurationClass configurationClass) {
        this.metadata = metadata;
        this.configurationClass = configurationClass;
    }

    public MethodMetadata getMetadata() {
        return metadata;
    }

    public ConfigurationClass getConfigurationClass() {
        return configurationClass;
    }


}
