package uk.co.revsys.esb.utils.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import uk.co.revsys.esb.component.ParameterAwareProcessor;

public class JEXLProcessor implements Processor, ParameterAwareProcessor{

    private JexlEngine jexlEngine;
    private String expression;

    public JEXLProcessor() {
        jexlEngine = new JexlEngine();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
    
    @Override
    public void process(Exchange exchng) throws Exception {
        JexlContext context = new MapContext();
        exchng.getIn().setBody(jexlEngine.createScript(expression).execute(context));
    }
    
}
