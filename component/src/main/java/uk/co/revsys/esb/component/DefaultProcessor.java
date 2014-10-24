package uk.co.revsys.esb.component;

import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public abstract class DefaultProcessor implements Processor, ParameterMapAwareProcessor{

    private Map<String, Object> parameters;
    private String header;
    private String property;
    
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
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
    
    protected void storeResult(Exchange exchange, Object result){
        if(getHeader()!=null){
            exchange.getIn().setHeader(getHeader(), result);
        }else if(getProperty()!=null){
            exchange.setProperty(getProperty(), result);
        }else{
            exchange.getIn().setBody(result);
        }
    }
    
}
