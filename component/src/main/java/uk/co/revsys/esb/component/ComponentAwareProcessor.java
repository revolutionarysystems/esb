package uk.co.revsys.esb.component;

import org.apache.camel.Component;
import org.apache.camel.Processor;

public interface ComponentAwareProcessor<C extends Component> extends Processor{

    public void setComponent(C component);
    
}
