package exalt.com.core;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exalt.com.builders.EventBuilder;
import exalt.com.builders.NewTaskBuilder;
import exalt.com.builders.SubscriberBuilder;
import exalt.com.events.NewTaskEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;
import exalt.com.models.SubscriberType;
import exalt.com.subscribers.SMSSubscriber;

/**
 * Unit test for EventManager Class.
 */
public class EventManagerTest {

    private static EventManager manager;
    private static Event event;
    private static EventSubscriber subscriber;

    @BeforeAll
    public static void initializeVariables(){
        manager = EventManager.getInstance();

        event = new EventBuilder("Christmas")
                        .setDescription("annual festival commemorating the birth of Jesus Christ, observed primarily on December 25")
                        .setEventTime(LocalDateTime.of(2025, 12, 25, 20, 0))
                        .setEventPriority(Priority.HIGH).setEventType(EventType.NEWTASK).build();

        subscriber = new SubscriberBuilder(1, "Yaman", "Abu Jazar")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.CONSOLE).build();
    }

    @AfterEach
    public void clearList(){
        manager.clearSubscribers();
        manager.clearSystemSubscribers();
    }

    @Test
    public void publishTest(){
        manager.publish(event);

        // Check if the event was added
        assertTrue(manager.getSubscribers().containsKey(event));
        // The subscribers list should be empty (size = 0) 
        assertEquals(0, manager.getSubscribers().get(event).size());
    }

    @Test
    public void subscribeTest(){
        manager.publish(event);

        manager.subscribe(event, subscriber);

        assertTrue(manager.getSubscribers().get(event).contains(subscriber));
        assertTrue(manager.getSystemSubscribers().contains(subscriber));
        assertEquals(1, manager.getSubscribers().get(event).size());
        assertEquals(1, manager.getSystemSubscribers().size());
    }

    @Test
    public void unsubscribeTest(){
        manager.publish(event);

        manager.subscribe(event, subscriber);

        assertTrue(manager.getSubscribers().get(event).contains(subscriber));
        assertEquals(1, manager.getSubscribers().get(event).size());
        assertEquals(1, manager.getSystemSubscribers().size());

        manager.unsubscribe(event, subscriber);

        assertFalse(manager.getSubscribers().get(event).contains(subscriber));
        assertEquals(0, manager.getSubscribers().get(event).size());
        assertEquals(1, manager.getSystemSubscribers().size());
    }
}
