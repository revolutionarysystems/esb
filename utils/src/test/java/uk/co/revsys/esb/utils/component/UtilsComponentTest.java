package uk.co.revsys.esb.utils.component;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class UtilsComponentTest extends CamelTestSupport {

    @Produce(uri = "direct:encode")
    protected ProducerTemplate encodeTemplate;
    
    @Produce(uri = "direct:decode")
    protected ProducerTemplate decodeTemplate;
    
    @Produce(uri = "direct:encodeQueryString")
    protected ProducerTemplate encodeQueryStringTemplate;
    
    @Test
    public void testEncode() throws Exception {
        encodeTemplate.sendBody("This is a test");
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied();
        assertEquals("This+is+a+test", mock.getExchanges().get(0).getIn().getBody());
    }
    
    @Test
    public void testDecode() throws Exception {
        decodeTemplate.sendBody("This+is+a+test");
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied();
        assertEquals("This is a test", mock.getExchanges().get(0).getIn().getBody());
    }
    
    @Test
    public void testEncodeQueryString() throws Exception {
        encodeQueryStringTemplate.sendBody("p1=This is a test");
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(1);
        assertMockEndpointsSatisfied();
        assertEquals("p1=This+is+a+test", mock.getExchanges().get(0).getIn().getBody());
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() {
                from("direct:encode")
                        .to("utils:encode")
                        .to("mock:result");
                from("direct:decode")
                        .to("utils:decode")
                        .to("mock:result");
                from("direct:encodeQueryString")
                        .to("utils:encodeQueryString")
                        .to("mock:result");
            }
        };
    }
}
