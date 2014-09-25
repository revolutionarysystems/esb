package uk.co.revsys.esb.component;

import org.apache.camel.Component;

public abstract class AbstractComponentAwareProcessor<C extends Component> implements ComponentAwareProcessor<C>{
    
    private C component;

    public C getComponent() {
        return component;
    }

    public void setComponent(C component) {
        this.component = component;
    }
}
