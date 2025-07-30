package exalt.com;

import java.time.LocalDateTime;

import exalt.com.builders.NewTaskBuilder;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;
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
        NewTaskEvent task1 = new NewTaskBuilder("Arranging Clothes").setDescription("Arrange all clothes in the wardrobe and in the room")
                                .setEventTime(LocalDateTime.of(2025, 8, 2, 10, 0))
                                .setEventPriority(Priority.HIGH).setEventType(EventType.NEWTASK).build();
        
        NewTaskEvent task2 = new NewTaskBuilder("Christmas").setDescription("annual festival commemorating the birth of Jesus Christ, observed primarily on December 25")
                                .setEventPriority(Priority.HIGH).build();
        
        EventSubscriber yaman = new User(1, "Yaman", "Abu Jazar", true);
        EventSubscriber anwar = new User(2, "Anwar", "Jaber", false);

        manager.publish(task1);
        manager.publish(task2);
        manager.publish(task2);

        manager.subscribe(task2, yaman);
        manager.subscribe(task2, anwar);

        manager.subscribe(task1, anwar);

        manager.notifySubscribers(task2);
        manager.notifySubscribers(task1);
    }
}
