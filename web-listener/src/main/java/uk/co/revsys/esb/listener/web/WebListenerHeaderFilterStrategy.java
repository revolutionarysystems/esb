package uk.co.revsys.esb.listener.web;

import org.apache.camel.Exchange;
import org.apache.camel.component.http.HttpHeaderFilterStrategy;
import org.apache.camel.impl.DefaultHeaderFilterStrategy;
import org.apache.camel.spi.HeaderFilterStrategy;

public class WebListenerHeaderFilterStrategy implements HeaderFilterStrategy{

    private final DefaultHeaderFilterStrategy defaultHeaderFilterStrategy;

    public WebListenerHeaderFilterStrategy() {
        defaultHeaderFilterStrategy = new HttpHeaderFilterStrategy();
        defaultHeaderFilterStrategy.getOutFilter().remove("cache-control");
        defaultHeaderFilterStrategy.getOutFilter().remove("pragma");
    }
    
    @Override
    public boolean applyFilterToCamelHeaders(String headerName, Object headerValue, Exchange exchange) {
        return defaultHeaderFilterStrategy.applyFilterToCamelHeaders(headerName, headerValue, exchange);
    }

    @Override
    public boolean applyFilterToExternalHeaders(String headerName, Object headerValue, Exchange exchange) {
        return defaultHeaderFilterStrategy.applyFilterToExternalHeaders(headerName, headerValue, exchange);
    }

}
