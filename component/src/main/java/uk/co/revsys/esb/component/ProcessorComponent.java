package uk.co.revsys.esb.component;

import java.util.Map;
import org.apache.camel.Endpoint;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultComponent;
import org.apache.camel.impl.ProcessorEndpoint;

public abstract class ProcessorComponent extends DefaultComponent{

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Processor processor = createProcessor(uri, remaining, parameters);
        if(processor instanceof ComponentAwareProcessor){
            ((ComponentAwareProcessor)processor).setComponent(this);
        }
        ProcessorEndpoint endpoint = new ProcessorEndpoint(uri, this, processor);
        setProperties(endpoint.getProcessor(), parameters);
        return endpoint;
    }
    
    protected abstract Processor createProcessor(String uri, String remaining, Map<String, Object> parameters) throws Exception;

}
