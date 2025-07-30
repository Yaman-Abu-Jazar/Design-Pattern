package exalt.com.core;

import java.time.LocalTime;

import exalt.com.models.Priority;

public interface EventSubscriber {

    public Priority getDesiredEventPriority();

    public void update();

    public LocalTime getDailyWorkHoursEnd();

    public LocalTime getDailyWorkHoursBegin();
    
}
