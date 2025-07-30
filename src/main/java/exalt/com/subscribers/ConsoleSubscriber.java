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

    public Priority getDesiredEventPriority() {
        return desiredEventPriority;
    }

    public LocalTime getDailyWorkHoursEnd() {
        return dailyWorkHoursEnd;
    }

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

    @Override
    public void update(){
        System.out.println("User with " + this.toString() + " has been notified about the event through Console Print Statement.");
    }
    
}
