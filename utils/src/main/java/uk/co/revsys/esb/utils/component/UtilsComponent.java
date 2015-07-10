package uk.co.revsys.esb.utils.component;

import java.util.Map;
import org.apache.camel.Processor;
import uk.co.revsys.esb.component.MappedProcessorComponent;

public class UtilsComponent extends MappedProcessorComponent{

    @Override
    protected void populateMappings(Map<String, Class<? extends Processor>> mappings) {
        mappings.put("encode", URLEncoderProcessor.class);
        mappings.put("encodeQueryString", URLQueryStringEncoderProcessor.class);
        mappings.put("decode", URLDecoderProcessor.class);
        mappings.put("jexl", JEXLProcessor.class);
        mappings.put("addAttachment", AddAttachmentProcessor.class);
    }

}
