package exalt.com.models;

public enum SubscriberType {
    SMS(0, "Notification through SMS"),
    EMAIL(1, "Notification through Email"),
    CONSOLE(2, "Notification through Console");

    private final int subscriptionLevel;
    private final String subscriptionDescription;

    SubscriberType(int subscriptionLevel, String subscriptionDescription){
        this.subscriptionLevel = subscriptionLevel;
        this.subscriptionDescription = subscriptionDescription;
    }

    public int getSubscriptionLevel() {
        return subscriptionLevel;
    }

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }
    
}
