package exalt.com.builders;

import java.time.LocalTime;

import exalt.com.core.EventSubscriber;
import exalt.com.models.Priority;
import exalt.com.models.SubscriberType;
import exalt.com.subscribers.ConsoleSubscriber;
import exalt.com.subscribers.SMSSubscriber;

public class SubscriberBuilder {
    private int id;
    private String fName;
    private String lName;
    private boolean isAdmin = false;
    private LocalTime dailyWorkHoursBegin = LocalTime.of(0, 0, 0);
    private LocalTime dailyWorkHoursEnd = LocalTime.of(0, 0, 0);
    private Priority desiredEventPriority = Priority.LOW;
    private SubscriberType type = SubscriberType.CONSOLE;

    public SubscriberBuilder(int id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public SubscriberBuilder setSubType(SubscriberType type){
        this.type = type;
        return this;
    }

    public SubscriberBuilder setDailyWorkHours(LocalTime begin, LocalTime end){
        this.dailyWorkHoursBegin = begin;
        this.dailyWorkHoursEnd = end;
        return this;
    }

    public SubscriberBuilder setDesiredPriority(Priority priority){
        this.desiredEventPriority = priority;
        return this;
    }

    public EventSubscriber build(){
        switch (this.type) {
            case SubscriberType.CONSOLE -> {
                return new ConsoleSubscriber();
            }
            case SubscriberType.SMS -> {
                return new SMSSubscriber();
            }
            case SubscriberType.EMAIL -> {
                return new ;
            }
            default -> throw new AssertionError();
        }
    }

    
}
