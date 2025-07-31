package exalt.com.manageEvents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import exalt.com.concurrency.Task;
import exalt.com.core.Event;
import exalt.com.core.EventManager;
import exalt.com.models.EventType;

public class HeartBeatNotify {

    List<String> content = new ArrayList<>();

    public void heartbeat(){
        EventManager manager = EventManager.getInstance();
        int nHeartbeat;
        try {
            content = Files.readAllLines(Paths.get("src/main/java/exalt/com/file.txt"));
            String separator = "=";
            String[] splittedString = content.get(0).split(separator);
            nHeartbeat = Integer.parseInt(splittedString[1]);
        } catch (IOException exception) {
            System.out.println("File not found");
            nHeartbeat = 10;
        }
        ScheduledExecutorService schedulor = Executors.newScheduledThreadPool(manager.getnEvents());
        for(Event event : manager.getSubscribers().keySet()){
            if(event.getEventType().equals(EventType.SCHEDULED)){
                Task task = new Task(event);
                schedulor.scheduleWithFixedDelay(task, nHeartbeat, nHeartbeat, TimeUnit.SECONDS);
            }
        }
    }
    
}
