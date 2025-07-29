package exalt.com;

import java.time.LocalDateTime;

import exalt.com.core.EventManager;
import exalt.com.events.NewTaskEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;
import exalt.com.subscribers.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EventManager manager = new EventManager();
        NewTaskEvent christmas = new NewTaskEvent("christmass", "Christmas is an annual festival commemorating the birth of Jesus Christ", LocalDateTime.of(2025, 12, 25, 0, 0), Priority.MEDIUM, EventType.NEWTASK);
        
        User yaman = new User(1, "Yaman", "Abu Jazar", true);

        manager.publish(christmas);
        manager.subscribe(christmas, yaman);

        manager.notifySubscribers(christmas);
    }
}
