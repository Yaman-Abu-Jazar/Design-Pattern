# Design-Pattern

system design

This app responsibles for publishing events (new tasks or jobs) and send notification about them as a reminder for the subscribers
First, I have build an Observer Design Pattern to make subscriber notified about new Events, so i divide the code according to this pattern into (core folder, subscribrs folder,and events folder) in which core folder contains one class (EventManager) which considered as the publisher class in my observer and it also contain two interface Event and EventSubscriber, in subscribers folder, I put kinds of subscribers in which each class implements the EventSubscriber interface to achieve abstraction principle, and in events folder, I put types of events (scheduled and unscheduled) events and both of them implements the Event interface, the difference between two classes is that for scheduled events there are threads that stay making notification about them each X seconds (X value can be read from file.txt) but unscheduled events have not.

additionally, the publisher is responsible for publishing the events, subscribe the users to a specific event, unsubscribe the users from a specific event, and notifying the subscribers about the events when get published or in a scheduled time. The subscribers don't like to be notified for any events, so this app supplies a filters for the subscribers who don't like this, and the admin users can report the history of the events before 1 hour or 1 week, and this app has a GroupEvent class which groups events which has a shared type.

finally, i have built multiple test cases for testing each method used in this app and i have divide the test codebase into well-structure codebase.

How to run the app 

after adding this plugin to the pom.xml file

<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>exec-maven-plugin</artifactId>
    <version>3.5.1</version>
    <configuration>
        <mainClass>exalt.com.App</mainClass>
    </configuration>
</plugin>

App.java will run when i type this command : mvn exec:java

How to run the tests

All tests will run when i type this command : mvn test