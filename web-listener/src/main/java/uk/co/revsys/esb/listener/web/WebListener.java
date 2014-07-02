package uk.co.revsys.esb.listener.web;

import uk.co.revsys.esb.listener.Listener;

public class WebListener extends Listener{

    private String urlPattern;

    public WebListener() {
    }

    public WebListener(String urlPattern, String eventName) {
        super(eventName);
        this.urlPattern = urlPattern;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
    
}
