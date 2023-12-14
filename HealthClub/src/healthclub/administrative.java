package healthclub;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class administrative {
    private int adminId;
    private String username;
    private String password;
    private FileManager fileManager;
    private String subscriptionEndDateFilePath;

    public administrative(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.fileManager = new FileManager("admin_data.txt");
    }

    public administrative(String subscriptionEndDateFilePath) {
        this.subscriptionEndDateFilePath = subscriptionEndDateFilePath;
    }

    private static final Scanner input = new Scanner(System.in);
    public void manageAdministrative() {
        int choice;
        do {
            System.out.println("Please choose any service");
            System.out.println("===========================");
            System.out.println("1. Manage Coaches");
            System.out.println("2. Manage Members");
            System.out.println("0. Exit");
            System.out.println("===========================");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                   this.manageCoaches();
                    break;
                case 2:
                    this.manageMembers();
                    break;
                case 0:
                    System.out.println("Exiting Administrative Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    public void manageCoaches() {
        int choice;
        do {
            System.out.println("Please choose any service");
            System.out.println("===========================");
            System.out.println("1. Add new coach");
            System.out.println("2. Delete coach");
            System.out.println("3. Update coach");
            System.out.println("4. Get coach by ID");
            System.out.println("5. Get all coaches");
            System.out.println("0. Exit");
            System.out.println("===========================");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    CoachRepository.addCoach();
                    break;
                case 2:
                    CoachRepository.deleteCoach();
                    break;
                case 3:
                    CoachRepository.updateCoach();
                    break;
                case 4:
                    CoachRepository.getCoachById();
                    break;
                case 5:
                    CoachRepository.getAllCoaches();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }


    public void manageMembers() {
        int choice;
        do {
            System.out.println("Please choose any service");
            System.out.println("===========================");
            System.out.println("1. Add new member");
            System.out.println("2. Delete member");
            System.out.println("3. Update member");
            System.out.println("4. Get member by ID");
            System.out.println("5. Get all members");
            System.out.println("0. Exit");
            System.out.println("===========================");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    MemberRepository.addMember();
                    break;
                case 2:
                    MemberRepository.deleteMember();
                    break;
                case 3:
                    MemberRepository.updateMember();
                    break;
                case 4:
                    MemberRepository.getMemberById();
                    break;
                case 5:
                    MemberRepository.getAllMembers();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    public void manageBilling() {
        // Implementation for managing billing
        // Retrieve a list of member IDs from the file or any other data source
        List<Integer> memberIds = fileManager.readMemberIds();

        // Initialize the Billing module
        Billing billingModule = new Billing(1); // Assuming billing ID is 1

        // Generate bills for each member
        for (int memberId : memberIds) {
            try {
                billingModule.generateBill(memberId);
            } catch (Exception e) {
                // Handle billing generation error
                e.printStackTrace();
            }
        }

        System.out.println("Billing management complete.");
    }

    public void generateMemberReports() {
        // Implementation for generating member reports
        // Retrieve a list of member IDs from the file or any other data source
        List<Integer> memberIds = fileManager.readMemberIds();

        // Initialize the Member module (assuming you have a Member class)
        Member memberModule = new Member();

        // Generate reports for each member
        for (int memberId : memberIds) {
            try {
                // Assuming you have a method to retrieve member information
                String memberName = memberModule.getMemberName(memberId);
                String subscriptionEndDate = memberModule.getSubscriptionEndDate(memberId);

                // Generate and print the member report
                System.out.println("Member Report for Member ID: " + memberId);
                System.out.println("Name: " + memberName);
                System.out.println("Subscription End Date: " + subscriptionEndDate);

                System.out.println();
            } catch (Exception e) {
                // Handle member report generation error
                e.printStackTrace();
            }
        }

        System.out.println("Member reports generation complete.");
    }

    public void assignCoach(int memberId, int coachId) {
        if (coachExists(coachId) && memberExists(memberId)) {
            try {
                Coach.assignMemberToCoach(coachId, memberId);

                saveCoachMemberMapToFile();

                System.out.println("Coach assigned successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid coach or member ID. Assignment failed.");
        }
    }

    private boolean coachExists(int coachId) {
        return true;
    }

    private boolean memberExists(int memberId) {
        return true; 
    }


    public void sendSubscriptionEndNotification(int memberId) {
        String subscriptionEndDate = loadSubscriptionEndDateFromFile(memberId);

        if (subscriptionEndDate != null) {
            if (isNotificationRequired(subscriptionEndDate)) {
                System.out.println("Subscription end notification sent to member " + memberId);
            } else {
                System.out.println("No notification needed for member " + memberId);
            }
        } else {
            System.out.println("Subscription end date not found for member " + memberId);
        }
    }

    private String loadSubscriptionEndDateFromFile(int memberId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(subscriptionEndDateFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int currentMemberId = Integer.parseInt(parts[0]);
                    if (currentMemberId == memberId) {
                        return parts[1];
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isNotificationRequired(String subscriptionEndDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date endDate = dateFormat.parse(subscriptionEndDate);
            Date currentDate = new Date();

            long diffInMillies = endDate.getTime() - currentDate.getTime();
            long daysDifference = diffInMillies / (24 * 60 * 60 * 1000);

            return daysDifference <= 7;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}

