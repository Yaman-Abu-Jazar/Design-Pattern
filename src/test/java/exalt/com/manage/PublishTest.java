package exalt.com.manage;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exalt.com.builders.EventBuilder;
import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.manageEvents.PublishEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;

public class PublishTest {

    private static final EventManager manager = EventManager.getInstance();
    private static final Event[] event = new Event[3];

    @BeforeAll
    static void setUp(){
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
    }

    @AfterEach
    void clearList(){
        manager.clearSubscribers();
        manager.clearSystemSubscribers();
    }

    @Test
    @DisplayName("Test the process of publish new events")
    public void normalPublishTest(){
        PublishEvent publisher = new PublishEvent();

        assertEquals(0, manager.getSubscribers().size());

        publisher.publish(event[0]);

        assertEquals(1, manager.getSubscribers().size());
        assertTrue(manager.getSubscribers().containsKey(event[0]));

        publisher.publish(event[1]);
        publisher.publish(event[2]);

        assertEquals(3, manager.getSubscribers().size());
        assertTrue(manager.getSubscribers().containsKey(event[1]));
        assertTrue(manager.getSubscribers().containsKey(event[2]));

    }

    @Test
    @DisplayName("Testing The size of Event HashedMap after publishing the same event multiple times")
    public void testingDuplicatedEvents(){

        PublishEvent publisher = new PublishEvent();

        assertEquals(0, manager.getSystemSubscribers().size());

        publisher.publish(event[0]);
        publisher.publish(event[0]);
        publisher.publish(event[0]);

        assertEquals(1, manager.getSubscribers().size());

        publisher.publish(event[1]);
        publisher.publish(event[1]);
        publisher.publish(event[0]);

        assertEquals(2, manager.getSubscribers().size());

    }
    
}
