package uk.co.revsys.esb.listener;

public class Listener {

    private String eventName;

    public Listener() {
    }

    public Listener(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
}
