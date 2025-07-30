package exalt.com;

import java.time.LocalDateTime;

import exalt.com.builders.NewTaskBuilder;
import exalt.com.core.EventManager;
import exalt.com.core.EventSubscriber;
import exalt.com.events.NewTaskEvent;
import exalt.com.models.EventType;
import exalt.com.models.Priority;

/**
 * Hello world!
 *
 */
public class App 
{

    private static EventManager manager;
    private static Event event;
    private static EventSubscriber subscriber;
    
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

        File file = new File("src/main/java/exalt/com/file.txt");
        byte bWrite[] = {65, 66, 67, 68, 69};
        try {
            FileReader reader = new FileReader(file);
            int c;
            try {
                while((c = reader.read()) != -1){
                    char ch = (char) c;
                    System.out.print(ch);
                }
            } catch (IOException ex) {
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File Does not Exist");
        }
    }
}
