package exalt.com.manage;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exalt.com.Subscription.SubscribeEvent;
import exalt.com.builders.EventBuilder;
import exalt.com.builders.SubscriberBuilder;
import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;
import exalt.com.manageEvents.HeartBeatNotify;
import exalt.com.manageEvents.PublishEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;
import exalt.com.models.SubscriberType;

public class HeartBeatTest {

    private static final EventManager manager = EventManager.getInstance();
    private static final Event[] event = new Event[5];
    private static final EventSubscriber[] subscriber = new EventSubscriber[5];

    @BeforeAll
    static void setUp(){
        event[0] = new EventBuilder("Code Review Session")
        .setDescription("Team-wide session to review and refactor core module pull requests.")
        .setEventTime(LocalDateTime.of(2025, 8, 2, 14, 0))
        .setEventPriority(Priority.HIGH)
        .setEventType(EventType.SCHEDULED)
        .build();

        event[1] = new EventBuilder("Database Migration")
        .setDescription("Migrate legacy database to cloud-based PostgreSQL system.")
        .setEventTime(LocalDateTime.of(2025, 8, 2, 14, 30))
        .setEventPriority(Priority.LOW)
        .setEventType(EventType.UNSCHEDULED)
        .build();

        event[2] = new EventBuilder("System Maintenance")
        .setDescription("Monthly scheduled maintenance to update security patches.")
        .setEventTime(LocalDateTime.of(2025, 8, 4, 10, 0))
        .setEventPriority(Priority.MEDIUM)
        .setEventType(EventType.SCHEDULED)
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
    public void heartbeatTest(){
        SubscribeEvent subscribeTool = new SubscribeEvent();
        PublishEvent publisher = new PublishEvent();
        HeartBeatNotify heartbeat = new HeartBeatNotify();

        publisher.publish(event[0]);
        publisher.publish(event[1]);
        publisher.publish(event[2]);

        subscribeTool.subscribe(event[0], subscriber[0]);
        subscribeTool.subscribe(event[0], subscriber[1]);

        heartbeat.heartbeat();

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupt");
        }
    }
    
}
