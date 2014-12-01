package uk.co.revsys.esb.listener.web;

import java.util.List;
import uk.co.revsys.esb.listener.Listener;

public class WebListener extends Listener {

    private String urlPattern;
    private List<String> groups;

    public WebListener() {
    }

    public WebListener(String urlPattern, String eventName) {
        super(eventName);
        this.urlPattern = urlPattern;
    }

    public WebListener(String eventName, String urlPattern, List<String> groups) {
        super(eventName);
        this.urlPattern = urlPattern;
        this.groups = groups;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

}
