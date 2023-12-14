package healthclub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


public class Member extends User {
    private int memberId;
    private String name;
    private Date subscriptionEndDate;
    private Coach coach;
    private Schedule schedule;
    private FileManager fileManager;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.fileManager = new FileManager("member_data.txt");
    }

    public Member() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return memberId;
    }

    public Date viewSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void receiveMessage(String message) {
        System.out.println("Message from Coach: " + message);
    }

    public Coach viewCoach() {
        return coach;
    }

    public String viewSchedule() {
        String fileName = getUsername() + "_schedule.txt";
        StringBuilder schedule = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                schedule.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schedule.toString();
    }

    public void receiveSubscriptionEndNotification(String not) {
        System.out.println("Notification for member " + name + ": " + not);
    }

    public String receiveSubscriptionEndNotification() {
        String fileName = getUsername() + "_not.txt"; // Corrected the file name
        StringBuilder notification = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notification.append(line).append("\n");
            }
        } catch (IOException e) {
            // Handle file reading error
            e.printStackTrace();
        }
        return notification.toString();
    }

    public String getUsername() {
        return getUsernameById(memberId); // Assuming you have a method to get the username by ID
    }

    public String getMemberName(int memberId) {
        // Implementation to get member name by ID
        return ""; // Replace with actual implementation
    }

    public String getSubscriptionEndDate(int memberId) {
        // Implementation to get subscription end date by ID
        return ""; // Replace with actual implementation
    }

    private String getUsernameById(int memberId) {
        // Implementation to get username by member ID
        return ""; // Replace with actual implementation
    }

    String getMemberId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void createMemberDirectory() {
        String directoryPath = "Home/project/HealthClub/HealthClub/member" + memberId; // Adjust the base path as needed

        Path path = Paths.get(directoryPath);

        try {
            Files.createDirectories(path);
            System.out.println("Directory created successfully for Member ID " + memberId + " at: " + path.toAbsolutePath());
        } catch (FileAlreadyExistsException e) {
            System.out.println("Directory already exists for Member ID " + memberId + " at: " + path.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Error creating directory for Member ID " + memberId + ": " + e.getMessage());
        }
    }
    @Override
    public String toString() {
        return memberId + ", Name: " + name + " UserName " + super.getUsername() + " Password :" + super.getPassword()
                + ", Subscription End Date: " + subscriptionEndDate;
    }
}

