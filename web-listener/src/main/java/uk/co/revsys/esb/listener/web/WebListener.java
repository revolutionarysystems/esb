package uk.co.revsys.esb.listener.web;

import uk.co.revsys.esb.listener.Listener;

public class WebListener extends Listener{

    private String urlPattern;
    private String group;

    public WebListener() {
    }

    public WebListener(String urlPattern, String eventName) {
        super(eventName);
        this.urlPattern = urlPattern;
    }

    public WebListener(String eventName, String urlPattern, String group) {
        super(eventName);
        this.urlPattern = urlPattern;
        this.group = group;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
}
