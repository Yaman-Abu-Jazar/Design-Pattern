package exalt.com.concurrency;

import exalt.com.core.Event;
import exalt.com.manageEvents.NotifyAboutEvent;

public class Task implements Runnable {

    private final Event event;

    public Task(Event event){
        this.event = event;
    }

    @Override
    public void run() {
        NotifyAboutEvent notifier = new NotifyAboutEvent();
        notifier.notifySubscribers(this.event);
    }
    
}
