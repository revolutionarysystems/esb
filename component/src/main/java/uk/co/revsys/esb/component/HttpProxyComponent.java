package uk.co.revsys.esb.component;

import java.util.Map;
import org.apache.camel.Processor;

public abstract class HttpProxyComponent extends MappedProcessorComponent {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    protected Processor createProcessor(Class<? extends Processor> type, String uri, String remaining, Map<String, Object> parameters) throws Exception {
        if(baseUrl == null){
            throw new java.lang.IllegalStateException("baseUrl property has not been set for " + getClass().getSimpleName());
        }
        return type.getConstructor(String.class).newInstance(getBaseUrl());
    }
}
