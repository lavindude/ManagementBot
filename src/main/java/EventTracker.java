import java.util.ArrayList;

public class EventTracker extends Event {
    private ArrayList<Event> events;

    public EventTracker() {
        this.events = new ArrayList<>();
    }

    public void addEvent(String s, String month, int day, int year) {
        Event e = new Event(s, month, day, year);
        events.add(e);
    }
}
