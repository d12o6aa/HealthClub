package healthclub;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Coach extends User {

    static void assignMemberToCoach(int coachId, int memberId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int coachId;
    private String name;
    private FileManager fileManager;

    public Coach(int coachId, String username) {
        super(coachId, username, password);
        this.coachId = coachId;
        this.name = name;
        this.fileManager = new FileManager("coach_data.txt");
    }

    public void createPlan(String plan) {
        // Implementation for creating a plan
        try (FileWriter writer = new FileWriter("coach_" + coachId + "_plan.txt")) {
            writer.write(plan);
        } catch (IOException e) {
            // Handle file writing error
            e.printStackTrace();
        }
    }

    public void createSchedule() {
        List<Schedule> scheduleList = createScheduleList();

        String filePath = "schedule_data.dat";

        Schedule.saveSchedulesToFile(scheduleList, filePath);

        List<Schedule> loadedSchedules = Schedule.readSchedulesFromFile(filePath);

        System.out.println("\nLoaded Schedules:");
        for (Schedule schedule : loadedSchedules) {
            System.out.println(schedule);
        }
        

        
        System.out.println("Schedule method implementation for coach: " + name);
    }

    private static List<Schedule> createScheduleList() {
        
        return List.of(new Schedule(1, "Monday", "8:00 AM - 10:00 AM"));
        }
   
    public void sendMessages(List<Member> members, String message) {
        // System.out.println("Message sent to all members: " + message);
        for (Member member : members) {
            try {
                System.out.println("Message sent to member " + member.getName() + ": " + message);
            } catch (NullPointerException e) {
                // Handle null member or any other potential errors
                e.printStackTrace();
            }
        }
    }
    @Override
    public String toString() {
        return  coachId+ ", Name: " + name+", UserName :"+super.getUsername()+", Password :"+super.getPassword();
    }
}