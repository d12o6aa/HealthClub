package healthclub;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;

    private int scheduleId;
    private String day;
    private String time;

    public Schedule(int scheduleId, String day, String time) {
        this.scheduleId = scheduleId;
        this.day = day;
        this.time = time;
    }

    // Getter methods...

    @Override
    public String toString() {
        return day + " at " + time;
    }

    // Method to save schedules to a file
    public static void saveSchedulesToFile(List<Schedule> schedules, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(schedules);
            System.out.println("Schedules saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read schedules from a file
    public static List<Schedule> readSchedulesFromFile(String filePath) {
        List<Schedule> schedules = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                schedules = (List<Schedule>) obj;
                System.out.println("Schedules loaded from file successfully.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return schedules;
    }
}


