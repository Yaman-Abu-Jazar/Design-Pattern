package exalt.com.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private final Map<Event, List<EventSubscriber>> subscribers;

    public EventManager() {
        subscribers = new HashMap<>();
    }

    public EventManager(Map<Event, List<EventSubscriber>> subscribers) {
        this.subscribers = subscribers;
    }

    // function is used to retrieve all subscribers for this exent
    public Map<Event, List<EventSubscriber>> getSubscribers() {
        return this.subscribers;
    }

    // function to publish new event and add it to the hash map
    public void publish(Event event){
        if(!this.subscribers.containsKey(event)){
            this.subscribers.put(event, new ArrayList<>());
            System.out.println("This Event : " + event + " has been published");
        }
            
        else
            System.out.println("This event has been fired before.");
    }

    // function is used to add new subscriber to the list of subscribers
    public void subscribe(Event event, EventSubscriber subscriber){
        List<EventSubscriber> list;
        if(this.subscribers.containsKey(event)){
            list = this.subscribers.get(event);
            if(list != null){
                if(!list.contains(subscriber)){
                    list.add(subscriber);
                    System.out.println("Subscriber : " + subscriber + " has subscribed to event : " + event);
                } else {
                    System.out.println("This user has subscribed to this event before.");
                }
            } else {
                System.out.println("This Event has no subscribers yet.");
            }
        } else {
            System.out.println("There is no such event");
        }
    }

    // function is used to remove (delete) a subscriber from the list of subscribers
    public void unsubscribe(Event event, EventSubscriber subscriber){
        List<EventSubscriber> list;
        if(this.subscribers.containsKey(event)){
            list = this.subscribers.get(event);
            if(list != null){
                if(list.contains(subscriber)){
                    list.remove(subscriber);
                    System.out.println("Subscriber : " + subscriber + " has unsubscribed this event : " + event);
                } else {
                    System.out.println("This user hasn't subscribed to this event before.");
                }
            } else {
                System.out.println("This Event has no subscribers yet.");
            }
        } else {
            System.out.println("There is no such event");
        }
    }

    public void notifySubscribers(Event event){
        List<EventSubscriber> list = this.subscribers.get(event);
        if(!list.isEmpty()){
            for(EventSubscriber subscriber : list){
                subscriber.update();
            }
        }
    }
}
