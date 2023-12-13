package healthclub;
import java.util.*;

public class Schedule {
    private int scheduleId;
    private Date date;
    private Time time;
    private List<Integer> classIds;

    public Schedule(int scheduleId, Date date, Time time) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.time = time;
        this.classIds = new ArrayList<>();
    }

    public void addClass(int classId) {
        // Implementation for adding a class to the schedule
    }

    public void removeClass(int classId) {
        // Implementation for removing a class from the schedule
    }
}
