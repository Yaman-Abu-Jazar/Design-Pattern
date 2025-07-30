package exalt.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    // private static EventManager manager;
    // private static Event event;
    // private static EventSubscriber subscriber;
    
    public static void main( String[] args )
    {
        // manager = EventManager.getInstance();

        // event = new EventBuilder("Christmas")
        //                 .setDescription("annual festival commemorating the birth of Jesus Christ, observed primarily on December 25")
        //                 .setEventTime(LocalDateTime.of(2025, 12, 25, 20, 0))
        //                 .setEventPriority(Priority.HIGH).setEventType(EventType.SCHEDULED).build();

        // subscriber = new SubscriberBuilder(1, "Yaman", "Abu Jazar")
        //                 .setDailyWorkHours(LocalTime.of(8, 0), LocalTime.of(17, 0))
        //                 .setDesiredPriority(Priority.LOW).setSubType(SubscriberType.CONSOLE).build();
        
        // manager.publish(event);

        // manager.subscribe(event, subscriber);

        // manager.heartbeat();

        List<String> content = new ArrayList<>();

        try {
            content = Files.readAllLines(Paths.get("src/main/java/exalt/com/file.txt"));
        } catch (IOException exception) {
            System.out.println("File not found");            
        }

        System.out.println(content);
        System.out.println(content.size());
        System.out.println(content.get(0));

        String separator = "=";
        String[] num = content.get(0).split(separator);

        System.out.println(num[1]);


        
    }
}
