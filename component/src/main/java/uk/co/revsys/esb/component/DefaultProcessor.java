package uk.co.revsys.esb.component;

import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class DefaultProcessor implements Processor, ParameterMapAwareProcessor{

    private Map<String, Object> parameters;
    
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    protected <P> P getParameter(Exchange exchange, String parameterName, Class<? extends P> type){
        return (P) getParameter(exchange, parameterName);
    }
    
    protected Object getParameter(Exchange exchange, String parameterName){
        Object parameter = parameters.get(parameterName);
        if(parameter == null){
            parameter = exchange.getIn().getHeader(parameterName);
            if(parameter == null){
                parameter = exchange.getProperty(parameterName);
            }
        }
        return parameter;
    }
    
}
