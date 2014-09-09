package uk.co.revsys.esb.component;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Processor;

public abstract class MappedProcessorComponent extends ProcessorComponent{

    private Map<String, Class<? extends Processor>> mappings;
    
    @Override
    protected Processor createProcessor(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        if(mappings == null){
            mappings = new HashMap<String, Class<? extends Processor>>();
            populateMappings(mappings);
        }
        Class processorClass = mappings.get(remaining);
        if(processorClass == null){
            throw new UnsupportedOperationException(uri + ":" + remaining);
        }
        Processor processor = createProcessor(processorClass, uri, remaining, parameters);
        return processor;
    }
    
    protected abstract void populateMappings(Map<String, Class<? extends Processor>> mappings);
    
    protected Processor createProcessor(Class<? extends Processor> type, String uri, String remaining, Map<String, Object> parameters) throws Exception {
        return type.newInstance();
    }

}
