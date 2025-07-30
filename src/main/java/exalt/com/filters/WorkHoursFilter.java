package exalt.com.filters;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import exalt.com.core.Event;
import exalt.com.core.EventSubscriber;

public class WorkHoursFilter implements MyFilter {

    @Override
    public List<EventSubscriber> filterSubscribers(List<EventSubscriber> subscribers, Event event) {
        Stream<EventSubscriber> stream = subscribers.stream().filter(subscriber -> {
            if (event.getEventTime() == null || subscriber.getDailyWorkHoursBegin() == null || subscriber.getDailyWorkHoursEnd() == null) {
                return false;
            }

            LocalTime eventTime = LocalTime.of(event.getEventTime().getHour(), event.getEventTime().getMinute());
            LocalTime workEnd = subscriber.getDailyWorkHoursEnd();
            LocalTime workBegin = subscriber.getDailyWorkHoursBegin();

            return (eventTime.isAfter(workBegin) && eventTime.isBefore(workEnd));
        });

        return stream.toList();
    }
    
}
