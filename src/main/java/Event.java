public class Event {
    private String s;
    private String month;
    private int day;
    private int year;
    private String subject;
    private String time;

    public Event(String s, String month, int day, int year, String subject, String time) {
        this.s = s;
        this.month = month;
        this.day = day;
        this.year = year;
        this.subject = subject;
        this.time = time;

        //throw exception for minutes?
    }

    public Event(String s, String month, int day, int year) {
        this.s = s;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Event() {}

    public String toString() {
        return (this.s + " is on: " + this.month + " " + this.day + "th, " + this.year + " for " + this.subject + " (" + this.time + ")");
    }

    public void setS(String s) {
        this.s = s;
    }

    public void setMonth(String m) {
        this.month = m;
    }

    public void setDay(int d) {
        this.day = d;
    }

    public void setYear(int y) {
        this.year = y;
    }
}
