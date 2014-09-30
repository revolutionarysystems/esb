package uk.co.revsys.esb.component;

import java.util.Map;
import java.util.Map.Entry;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpComponent;

public abstract class HttpProxyProcessor implements Processor {

    protected static final String GET = "GET";
    protected static final String POST = "POST";
    protected static final String DELETE = "DELETE";
    protected static final String PUT = "PUT";
    
    protected static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
    protected static final String TEXT_PLAIN = "text/plain";
    protected static final String TEXT_XML = "text/xml";
    protected static final String APPLICATION_JSON = "application/json";
    
    private final String baseUrl;
    
    private String authUsername;
    private String authPassword;

    public HttpProxyProcessor(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    @Override
    public void process(Exchange exchange) throws Exception {
        preProcess(exchange);
        HttpComponent http = (HttpComponent) exchange.getContext().getComponent("http");
        
        exchange.getIn().setHeader(Exchange.HTTP_METHOD, getHttpMethod());
        String contentType = getContentType();
        if(contentType!=null){
            exchange.getIn().setHeader(Exchange.CONTENT_TYPE, contentType);
        }
        String queryString = getQueryString();
        if(queryString!=null){
            exchange.getIn().setHeader(Exchange.HTTP_QUERY, queryString);
        }
        Map<String, String> postParameters = getPostParameters();
        if(postParameters!=null){
            StringBuilder body = new StringBuilder();
            for(Entry<String, String> postParameter: postParameters.entrySet()){
                body.append(postParameter.getKey()).append("=").append(postParameter.getValue()).append("&");
            }
            exchange.getIn().setBody(body);
        }
        String uri = baseUrl + getUrlPath();
        if(uri.indexOf("?") > -1){
            uri = uri + "&";
        }else{
            uri = uri + "?";
        }
        uri = uri + "bridgeEndpoint=" + getBridgeEndpoint() + "&throwExceptionOnFailure=" + getThrowExceptionOnFailure();
        if(authUsername!=null){
            uri = uri + "&authMethod=Basic&authUsername=" + getAuthUsername() + "&authPassword=" + getAuthPassword();
        }
        http.createEndpoint(uri).createProducer().process(exchange);
        postProcess(exchange);
    }
    
    public void preProcess(Exchange exchange) throws Exception{
        
    }
    
    public void postProcess(Exchange exchange) throws Exception{
        
    }
    
    public abstract String getHttpMethod();
    
    public String getContentType(){
        return null;
    }
    
    public Map<String, String> getPostParameters(){
        return null;
    }
    
    public String getQueryString(){
        return null;
    }
    
    public abstract String getUrlPath();
    
    public boolean getBridgeEndpoint(){
        return true;
    }
    
    public boolean getThrowExceptionOnFailure(){
        return true;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public void setAuthUsername(String authUsername) {
        this.authUsername = authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }

}
