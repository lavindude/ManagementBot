public class Event {
    private String s;
    private String month;
    private int day;
    private int year;
    private int hour;
    private int minute;

    public Event(String s, String month, int day, int year, int hour, int minute) {
        this.s = s;
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
        this.minute = minute;

        //throw exception for minutes?
    }

    public Event() {}

    public String toString() {
        return (this.s + " is due: " + this.month + " " + this.day + "th, " + this.year + " at " + this.hour + this.minute);
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

    public void setHour(int h) {
        this.hour = h;
    }

    public void setMinute(int m) {
        this.minute = m;
    }
}
