package exalt.com.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    private final Map<Event, List<EventSubscriber>> subscribers;
    private final List<EventSubscriber> allSubscribers;
    private static EventManager manager;

    private EventManager() {
        subscribers = new HashMap<>();
        allSubscribers = new ArrayList<>();
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

    // function to publish new event and add it to the hash map
    public void publish(Event event){
        if(!this.subscribers.containsKey(event)){
            this.subscribers.put(event, new ArrayList<>());
            System.out.println("This Event : " + event + " has been published");
            if(!allSubscribers.isEmpty()){
                for(EventSubscriber subscriber : allSubscribers){
                    subscriber.update();
                }
            } else {
                System.out.println("The system has no subscribers yet.");
            }
            
        } 
        else
            System.out.println("This event has been fired before.");
    }

    // function is used to add new subscriber to the list of subscribers
    public void subscribe(Event event, EventSubscriber subscriber){
        List<EventSubscriber> list;
        if(this.subscribers.containsKey(event)){
            list = this.subscribers.get(event);
            if(!list.contains(subscriber)){
                list.add(subscriber);
                System.out.println("Subscriber : " + subscriber + " has subscribed to event : " + event);
                if(!allSubscribers.contains(subscriber)){
                    allSubscribers.add(subscriber);
                }
            } else {
                System.out.println("This user has subscribed to this event before.");
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
        } else {
            System.out.println("There are no subscribers yet for this Event " + event + ".");
        }
    }

    public void clearSubscribers(){
        this.subscribers.clear();
    }

    public void clearSystemSubscribers(){
        allSubscribers.clear();
    }
}
