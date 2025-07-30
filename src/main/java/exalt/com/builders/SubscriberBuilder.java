package exalt.com.builders;

import java.time.LocalTime;

import exalt.com.core.EventSubscriber;
import exalt.com.models.Priority;
import exalt.com.models.SubscriberType;
import exalt.com.subscribers.ConsoleSubscriber;
import exalt.com.subscribers.EmailSubscriber;
import exalt.com.subscribers.SMSSubscriber;

public class SubscriberBuilder {
    private int id;
    private String fName;
    private String lName;
    private boolean isAdmin = false;
    private LocalTime dailyWorkHoursBegin = null;
    private LocalTime dailyWorkHoursEnd = null;
    private Priority desiredEventPriority = Priority.LOW;
    private SubscriberType type = SubscriberType.CONSOLE;
    private boolean priorityBasedNotify = false;
    private boolean workHoursBasedNotify = false;
    private boolean allNotification = false;
    private boolean noneNotification = false;

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

    public SubscriberBuilder setPriorityBasedNotify(boolean priorityBasedNotify){
        this.priorityBasedNotify = priorityBasedNotify;
        return this;
    }

    public SubscriberBuilder setWorkHoursBasedNotify(boolean workHoursBasedNotify){
        this.workHoursBasedNotify = workHoursBasedNotify;
        return this;
    }

    public EventSubscriber build(){
        switch (this.type) {
            case SubscriberType.CONSOLE -> {
                return new ConsoleSubscriber(this.id, this.fName, this.lName, this.isAdmin, this.dailyWorkHoursBegin,
                this.dailyWorkHoursEnd, this.desiredEventPriority, this.type, this.priorityBasedNotify, this.workHoursBasedNotify,
                this.allNotification, this.noneNotification);
            }
            case SubscriberType.SMS -> {
                return new SMSSubscriber(this.id, this.fName, this.lName, this.isAdmin, this.dailyWorkHoursBegin,
                this.dailyWorkHoursEnd, this.desiredEventPriority, this.type, this.priorityBasedNotify, this.workHoursBasedNotify,
                this.allNotification, this.noneNotification);
            }
            case SubscriberType.EMAIL -> {
                return new EmailSubscriber(this.id, this.fName, this.lName, this.isAdmin, this.dailyWorkHoursBegin,
                this.dailyWorkHoursEnd, this.desiredEventPriority, this.type, this.priorityBasedNotify, this.workHoursBasedNotify,
                this.allNotification, this.noneNotification);
            }
            default -> throw new AssertionError();
        }
    }

    
}
