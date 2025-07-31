package exalt.com.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager {

    private final Map<Event, List<EventSubscriber>> subscribers;
    private final List<EventSubscriber> allSubscribers;
    private static EventManager manager;
    private int nEvents;

    private EventManager() {
        subscribers = new ConcurrentHashMap<>();
        allSubscribers = new ArrayList<>();
        nEvents = 0;
    }

    public static EventManager getInstance(){
        if(manager == null){
            manager = new EventManager();
        }
        return manager;
    }

    // function is used to retrieve all subscribers for this exent
    public Map<Event, List<EventSubscriber>> getSubscribers() {
        return this.subscribers;
    }

    // function is used to retrieve all subscribers of the system
    public List<EventSubscriber> getSystemSubscribers(){
        return allSubscribers;
    }

    public void clearSubscribers(){
        this.subscribers.clear();
    }

    public void clearSystemSubscribers(){
        allSubscribers.clear();
    }

    public int getnEvents() {
        return nEvents;
    }

    public void setnEvents(int nEvents) {
        this.nEvents = nEvents;
    }
}
