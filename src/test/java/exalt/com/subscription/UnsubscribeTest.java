package exalt.com.subscription;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exalt.com.Subscription.SubscribeEvent;
import exalt.com.Subscription.UnsubscribeEvent;
import exalt.com.builders.EventBuilder;
import exalt.com.builders.SubscriberBuilder;
import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;
import exalt.com.manageEvents.PublishEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;
import exalt.com.models.SubscriberType;

public class UnsubscribeTest {

    private static final EventManager manager = EventManager.getInstance();
    private static final Event[] event = new Event[3];
    private static final EventSubscriber[] subscriber = new EventSubscriber[4];

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

        event[2] = new EventBuilder("System Maintenance")
        .setDescription("Monthly scheduled maintenance to update security patches.")
        .setEventTime(LocalDateTime.of(2025, 8, 4, 10, 0))
        .setEventPriority(Priority.MEDIUM)
        .setEventType(EventType.UNSCHEDULED)
        .build();


        subscriber[0] = new SubscriberBuilder(1, "Yaman", "Abu Jazar")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.CONSOLE).build();

        subscriber[1] = new SubscriberBuilder(2, "Anwar", "Jaber")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.SMS).build();

        subscriber[2] = new SubscriberBuilder(3, "Yazan", "Abu Jazar")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(15, 30))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.SMS).build();

        subscriber[3] = new SubscriberBuilder(4, "Mohammad", "Shalabi")
                        .setDailyWorkHours(LocalTime.of(14, 0), LocalTime.of(22, 0))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.EMAIL).build();
    }

    @AfterEach
    void clearList(){
        manager.clearSubscribers();
        manager.clearSystemSubscribers();
    }

    @Disabled
    @Test
    @DisplayName("Testing The size of Lists after Unsubscriping Process")
    public void normalUnsubscribeTest(){

        SubscribeEvent subscribeTool = new SubscribeEvent();
        PublishEvent publisher = new PublishEvent();
        UnsubscribeEvent unsubscribeTool = new UnsubscribeEvent();

        assertEquals(0, manager.getSystemSubscribers().size());

        publisher.publish(event[0]);
        publisher.publish(event[1]);
        publisher.publish(event[2]);
        subscribeTool.subscribe(event[0], subscriber[0]);
        subscribeTool.subscribe(event[1], subscriber[0]);
        subscribeTool.subscribe(event[0], subscriber[1]);
        subscribeTool.subscribe(event[1], subscriber[1]);
        subscribeTool.subscribe(event[1], subscriber[2]);
        subscribeTool.subscribe(event[2], subscriber[3]);

        assertEquals(3, manager.getSubscribers().size());
        assertEquals(2, manager.getSubscribers().get(event[0]).size());
        assertTrue(manager.getSubscribers().get(event[0]).contains(subscriber[0]));
        assertEquals(3, manager.getSubscribers().get(event[1]).size());
        assertEquals(4, manager.getSystemSubscribers().size());

        unsubscribeTool.unsubscribe(event[0], subscriber[0]);

        assertFalse(manager.getSubscribers().get(event[0]).contains(subscriber[0]));
        assertEquals(1, manager.getSubscribers().get(event[0]).size());
        assertEquals(4, manager.getSystemSubscribers().size());

        unsubscribeTool.unsubscribe(event[1], subscriber[1]);

        assertFalse(manager.getSubscribers().get(event[1]).contains(subscriber[1]));
        assertEquals(2, manager.getSubscribers().get(event[1]).size());
        assertEquals(4, manager.getSystemSubscribers().size());

    }

    @Disabled
    @Test
    @DisplayName("Testing The size of Lists after Unsubscription multiple times")
    public void testingDuplicatedSubscribers(){

        SubscribeEvent subscribeTool = new SubscribeEvent();
        PublishEvent publisher = new PublishEvent();
        UnsubscribeEvent unsubscribeTool = new UnsubscribeEvent();

        assertEquals(0, manager.getSystemSubscribers().size());

        publisher.publish(event[0]);
        publisher.publish(event[1]);
        publisher.publish(event[2]);
        subscribeTool.subscribe(event[0], subscriber[0]);
        subscribeTool.subscribe(event[1], subscriber[0]);
        subscribeTool.subscribe(event[0], subscriber[1]);
        subscribeTool.subscribe(event[1], subscriber[1]);
        subscribeTool.subscribe(event[1], subscriber[2]);
        subscribeTool.subscribe(event[2], subscriber[3]);

        unsubscribeTool.unsubscribe(event[0], subscriber[0]);

        assertFalse(manager.getSubscribers().get(event[0]).contains(subscriber[0]));
        assertEquals(1, manager.getSubscribers().get(event[0]).size());
        assertEquals(4, manager.getSystemSubscribers().size());

        assertEquals(1, manager.getSubscribers().get(event[2]).size());

        unsubscribeTool.unsubscribe(event[2], subscriber[0]);

        assertFalse(manager.getSubscribers().get(event[2]).contains(subscriber[0]));
        assertEquals(1, manager.getSubscribers().get(event[2]).size());

    }
    
}
