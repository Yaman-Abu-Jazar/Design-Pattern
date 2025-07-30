package exalt.com.concurrency;

import exalt.com.core.Event;
import exalt.com.core.EventManager;

public class Task implements Runnable {

    private final EventManager manager;
    Event event;

    public Task(Event event){
        this.manager = EventManager.getInstance();
        this.event = event;
    }

    @Override
    public void run() {
        if(!manager.getSubscribers().get(this.event).isEmpty()){
            manager.notifySubscribers(this.event);
        } else {
            System.out.println("This Event : " + event + " has no subscribers.");
        }
    }
    
}
