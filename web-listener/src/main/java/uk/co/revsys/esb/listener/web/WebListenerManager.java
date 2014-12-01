package uk.co.revsys.esb.listener.web;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class WebListenerManager {

    private List<WebListener> listeners = new LinkedList<WebListener>();

    public WebListenerManager(List<WebListener> listeners) {
        this(listeners, null);
    }
    
    public WebListenerManager(List<WebListener> listeners, String group) {
        this(listeners, group, false);
    }

    public WebListenerManager(List<WebListener> listeners, String group, boolean includeUngrouped) {
        if (group == null) {
            this.listeners = listeners;
        } else {
            for (WebListener listener : listeners) {
                if(((listener.getGroups() == null || listener.getGroups().isEmpty()) && includeUngrouped) || (listener.getGroups()!=null && listener.getGroups().contains(group))){
                    this.listeners.add(listener);
                }
            }
        }
    }

    public void routeMessage(Message message) {
        String incomingUrl = (String) message.getHeader(Exchange.HTTP_URI);
        String eventName = null;
        for (WebListener listener : listeners) {
            String patternString = listener.getUrlPattern();
            Pattern pattern = Pattern.compile(patternString + ".*");
            Matcher matcher = pattern.matcher(incomingUrl);
            if (matcher.matches()) {
                message.setHeader(Exchange.HTTP_PATH, incomingUrl.replaceAll(patternString, ""));
                eventName = listener.getEventName();
                break;
            }
        }
        if(incomingUrl.equals("/") && eventName == null){
            eventName = "status";
        }
        message.setHeader("eventName", eventName);
        if (eventName == null) {
            message.setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
        }
    }

}
