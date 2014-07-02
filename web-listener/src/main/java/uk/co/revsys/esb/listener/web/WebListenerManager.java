package uk.co.revsys.esb.listener.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class WebListenerManager{

    private List<WebListener> listeners;

    public WebListenerManager(List<WebListener> listeners) {
        this.listeners = listeners;
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
        message.setHeader("eventName", eventName);
        if(eventName == null){
            message.setHeader(Exchange.HTTP_RESPONSE_CODE, 404);
        }
    }

}
