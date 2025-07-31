package exalt.com.subscription;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exalt.com.Subscription.SubscribeEvent;
import exalt.com.builders.EventBuilder;
import exalt.com.builders.SubscriberBuilder;
import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;
import exalt.com.manageEvents.PublishEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;
import exalt.com.models.SubscriberType;

/**
 * Unit test for simple App.
 */
public class SubscribeTest {

    private static final EventManager manager = EventManager.getInstance();
    private static final Event[] event = new Event[2];
    private static final EventSubscriber[] subscriber = new EventSubscriber[2];

    @BeforeAll
    static void setUp(){
        manager.clearSubscribers();
        manager.clearSystemSubscribers();

        event[0] = new EventBuilder("Code Review Session")
        .setDescription("Team-wide session to review and refactor core module pull requests.")
        .setEventTime(LocalDateTime.of(2025, 8, 2, 14, 0))
        .setEventPriority(Priority.LOW)
        .setEventType(EventType.UNSCHEDULED)
        .build();

        event[1] = new EventBuilder("Database Migration")
        .setDescription("Migrate legacy database to cloud-based PostgreSQL system.")
        .setEventTime(LocalDateTime.of(2025, 8, 2, 14, 30))
        .setEventPriority(Priority.HIGH)
        .setEventType(EventType.SCHEDULED)
        .build();

        subscriber[0] = new SubscriberBuilder(1, "Yaman", "Abu Jazar")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.CONSOLE).build();

        subscriber[1] = new SubscriberBuilder(2, "Anwar", "Jaber")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.SMS).build();
    }

    @AfterEach
    void clearList(){
        manager.clearSubscribers();
        manager.clearSystemSubscribers();
    }

    @Test
    @DisplayName("Testing The size of Lists after Subscription Process")
    public void normalSubscribeTest(){

        SubscribeEvent subscribeTool = new SubscribeEvent();
        PublishEvent publisher = new PublishEvent();

        assertEquals(0, manager.getSystemSubscribers().size());

        publisher.publish(event[0]);

        assertEquals(1, manager.getSubscribers().size());
        assertTrue(manager.getSubscribers().containsKey(event[0]));
        assertEquals(0, manager.getSubscribers().get(event[0]).size());
        assertEquals(0, manager.getSystemSubscribers().size());

        subscribeTool.subscribe(event[0], subscriber[0]);

        assertEquals(1, manager.getSubscribers().get(event[0]).size());
        assertTrue(manager.getSubscribers().get(event[0]).contains(subscriber[0]));
        assertEquals(1, manager.getSystemSubscribers().size());
        assertTrue(manager.getSystemSubscribers().contains(subscriber[0]));

        subscribeTool.subscribe(event[0], subscriber[1]);

        assertEquals(2, manager.getSubscribers().get(event[0]).size());
        assertEquals(2, manager.getSystemSubscribers().size());
        assertTrue(manager.getSystemSubscribers().contains(subscriber[0]) && manager.getSystemSubscribers().contains(subscriber[1]));
        assertTrue(manager.getSubscribers().get(event[0]).contains(subscriber[0]) && manager.getSubscribers().get(event[0]).contains(subscriber[1]));
    }

    @Test
    @DisplayName("Testing The size of Lists after Subscription Process")
    public void testingDuplicatedSubscribers(){

        SubscribeEvent subscribeTool = new SubscribeEvent();
        PublishEvent publisher = new PublishEvent();

        publisher.publish(event[0]);
        publisher.publish(event[1]);

        assertEquals(0, manager.getSubscribers().get(event[0]).size());
        assertEquals(0, manager.getSystemSubscribers().size());

        subscribeTool.subscribe(event[0], subscriber[0]);
        subscribeTool.subscribe(event[1], subscriber[0]);

        assertEquals(1, manager.getSubscribers().get(event[0]).size());
        assertEquals(1, manager.getSubscribers().get(event[1]).size());
        assertEquals(1, manager.getSystemSubscribers().size());

        subscribeTool.subscribe(event[0], subscriber[0]);
        subscribeTool.subscribe(event[1], subscriber[0]);

        assertEquals(1, manager.getSubscribers().get(event[0]).size());
        assertEquals(1, manager.getSubscribers().get(event[1]).size());
        assertEquals(1, manager.getSystemSubscribers().size());

        subscribeTool.subscribe(event[0], subscriber[1]);
        subscribeTool.subscribe(event[1], subscriber[1]);

        assertEquals(2, manager.getSubscribers().get(event[0]).size());
        assertEquals(2, manager.getSubscribers().get(event[1]).size());
        assertEquals(2, manager.getSystemSubscribers().size());

    }
    
}
