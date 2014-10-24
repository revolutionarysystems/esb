package uk.co.revsys.esb.utils.component;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import uk.co.revsys.esb.component.ParameterAwareProcessor;
import uk.co.revsys.esb.utils.URLEncoder;

public class URLEncoderProcessor implements Processor, ParameterAwareProcessor{
    
    private URLEncoder encoder = new URLEncoder();
    
    private String encoding = "UTF-8";

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
    
    @Override
    public void process(Exchange exchng) throws Exception {
        String result = encoder.encode(exchng.getIn().getBody(String.class), encoding);
        exchng.getIn().setBody(result);
    }

}
