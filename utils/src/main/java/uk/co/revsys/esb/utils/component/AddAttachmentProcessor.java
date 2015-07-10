package uk.co.revsys.esb.utils.component;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import uk.co.revsys.esb.component.ParameterAwareProcessor;

public class AddAttachmentProcessor implements Processor, ParameterAwareProcessor {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        byte[] file = in.getBody(byte[].class);
        in.addAttachment(name, new DataHandler(new ByteArrayDataSource(file, type)));
    }

}
