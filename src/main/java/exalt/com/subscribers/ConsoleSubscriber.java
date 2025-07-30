package exalt.com.subscribers;

import java.time.LocalTime;

import exalt.com.core.EventSubscriber;
import exalt.com.models.Priority;
import exalt.com.models.SubscriberType;

public class ConsoleSubscriber implements EventSubscriber {
    private int id;
    private String fName;
    private String lName;
    private boolean isAdmin;
    private LocalTime dailyWorkHoursBegin;
    private LocalTime dailyWorkHoursEnd;
    private Priority desiredEventPriority;
    private SubscriberType type;
    private boolean priorityBasedNotify;
    private boolean workHoursBasedNotify;
    private boolean allNotification;
    private boolean noneNotification;

    public ConsoleSubscriber(int id, String fName, String lName, boolean isAdmin, LocalTime dailyWorkHoursBegin, LocalTime dailyWorkHoursEnd, Priority desiredEventPriority, SubscriberType type, boolean priorityBasedNotify, boolean workHoursBasedNotify, boolean allNotification, boolean noneNotification) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.isAdmin = isAdmin;
        this.dailyWorkHoursBegin = dailyWorkHoursBegin;
        this.dailyWorkHoursEnd = dailyWorkHoursEnd;
        this.desiredEventPriority = desiredEventPriority;
        this.type = type;
        this.priorityBasedNotify = priorityBasedNotify;
        this.workHoursBasedNotify = workHoursBasedNotify;
        this.allNotification = allNotification;
        this.noneNotification = noneNotification;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public SubscriberType getType() {
        return type;
    }

    @Override
    public Priority getDesiredEventPriority() {
        return desiredEventPriority;
    }

    @Override
    public LocalTime getDailyWorkHoursEnd() {
        return dailyWorkHoursEnd;
    }

    @Override
    public LocalTime getDailyWorkHoursBegin() {
        return dailyWorkHoursBegin;
    }

    public void setDailyWorkHoursBegin(LocalTime dailyWorkHoursBegin) {
        this.dailyWorkHoursBegin = dailyWorkHoursBegin;
    }

    public void setDailyWorkHoursEnd(LocalTime dailyWorkHoursEnd) {
        this.dailyWorkHoursEnd = dailyWorkHoursEnd;
    }

    public void setDesiredEventPriority(Priority desiredEventPriority) {
        this.desiredEventPriority = desiredEventPriority;
    }

    public void setType(SubscriberType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User Id : " + this.id + " User Name : " + this.fName + " " + this.lName;
    }

    public void setPriorityBasedNotify(boolean priorityBasedNotify) {
        this.priorityBasedNotify = priorityBasedNotify;
    }

    public boolean getPriorityBasedNotify() {
        return this.priorityBasedNotify;
    }

    public void setWorkHoursBasedNotify(boolean workHoursBasedNotify) {
        this.workHoursBasedNotify = workHoursBasedNotify;
    }

    public boolean getWorkHoursBasedNotify() {
        return this.workHoursBasedNotify;
    } 

    public boolean isAllNotification() {
        return allNotification;
    }

    public void setAllNotification(boolean allNotification) {
        this.allNotification = allNotification;
    }

    public boolean isNoneNotification() {
        return noneNotification;
    }

    public void setNoneNotification(boolean noneNotification) {
        this.noneNotification = noneNotification;
    }

    @Override
    public void update(){
        System.out.println("User with " + this.toString() + " has been notified about the event through Console Print Statement.");
    }
}
