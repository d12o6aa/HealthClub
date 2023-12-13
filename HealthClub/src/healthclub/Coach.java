package healthclub;
import java.util.*;

public class Coach extends User {
    private int coachId;
    private String name;
    private FileManager fileManager;

    public Coach(int coachId, String username, String password, String name) {
        super(coachId, username, password);
        this.coachId = coachId;
        this.name = name;
        this.fileManager = new FileManager("coach_data.txt");
    }

    public void createPlan(int memberId) {
        // Implementation for creating a plan for a member
    }

    public void createSchedule(int memberId) {
        // Implementation for creating a schedule for a member
    }

    public void sendMessage(List<Member> members) {
        // Implementation for sending a message to members
    }
}