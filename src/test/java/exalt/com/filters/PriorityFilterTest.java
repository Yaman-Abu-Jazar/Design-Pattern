package exalt.com.filters;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
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

public class PriorityFilterTest {

    private static final EventManager manager = EventManager.getInstance();
    private static final EventSubscriber[] subscriber = new EventSubscriber[5];
    private static final Event[] event = new Event[5];

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

        event[3] = new EventBuilder("Release Planning")
        .setDescription("Sprint planning for upcoming software version 2.4 release.")
        .setEventTime(LocalDateTime.of(2025, 8, 5, 12, 0))
        .setEventPriority(Priority.HIGH)
        .setEventType(EventType.SCHEDULED)
        .build();


        subscriber[0] = new SubscriberBuilder(1, "Yaman", "Abu Jazar")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
                        .setDesiredPriority(Priority.MEDIUM).setSubType(SubscriberType.CONSOLE)
                        .setPriorityBasedNotify(true).build();

        subscriber[1] = new SubscriberBuilder(2, "Anwar", "Jaber")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
                        .setDesiredPriority(Priority.HIGH).setSubType(SubscriberType.SMS)
                        .setPriorityBasedNotify(true).build();

        subscriber[2] = new SubscriberBuilder(3, "Yazan", "Abu Jazar")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(15, 30))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.SMS).build();

        subscriber[3] = new SubscriberBuilder(4, "Mohammad", "Shalabi")
                        .setDailyWorkHours(LocalTime.of(14, 0), LocalTime.of(22, 0))
                        .setDesiredPriority(Priority.HIGH).setSubType(SubscriberType.EMAIL)
                        .setPriorityBasedNotify(true).build();

        subscriber[4] = new SubscriberBuilder(5, "Adham", "Faqi")
                        .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(23, 0))
                        .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.EMAIL).build();
    }

    @AfterEach
    void clearList(){
        manager.clearSubscribers();
        manager.clearSystemSubscribers();
    }

    @Test
    public void filterTest(){
        PriorityFilter filter = new PriorityFilter();

        SubscribeEvent subscribeTool = new SubscribeEvent();
        PublishEvent publisher = new PublishEvent();

        publisher.publish(event[0]);
        publisher.publish(event[1]);
        publisher.publish(event[3]); 

        subscribeTool.subscribe(event[0], subscriber[0]);
        subscribeTool.subscribe(event[0], subscriber[1]);
        subscribeTool.subscribe(event[0], subscriber[2]);
        subscribeTool.subscribe(event[0], subscriber[3]);
        subscribeTool.subscribe(event[0], subscriber[4]);
        subscribeTool.subscribe(event[1], subscriber[0]);
        subscribeTool.subscribe(event[1], subscriber[1]);
        subscribeTool.subscribe(event[1], subscriber[2]);
        subscribeTool.subscribe(event[1], subscriber[3]);
        subscribeTool.subscribe(event[1], subscriber[4]);
        subscribeTool.subscribe(event[3], subscriber[0]);
        subscribeTool.subscribe(event[3], subscriber[1]);
        subscribeTool.subscribe(event[3], subscriber[2]);
        subscribeTool.subscribe(event[3], subscriber[3]);
        subscribeTool.subscribe(event[3], subscriber[4]);

        assertTrue(manager.getSystemSubscribers().contains(subscriber[1]));

        List<EventSubscriber> filteredList1 = filter.filterSubscribers(manager.getSystemSubscribers(), event[0]);

        System.out.println(filteredList1);

        List<EventSubscriber> filteredList2 = filter.filterSubscribers(manager.getSystemSubscribers(), event[1]);

        System.out.println(filteredList2);

        List<EventSubscriber> filteredList3 = filter.filterSubscribers(manager.getSystemSubscribers(), event[3]);

        System.out.println(filteredList3);

        assertFalse(filteredList1.contains(subscriber[1]));

    }
    
}
