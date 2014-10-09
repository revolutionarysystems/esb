package uk.co.revsys.esb.component;

import java.util.Map;

public interface ParameterMapAwareProcessor {

    public void setParameters(Map<String, Object> parameters);
    
}
