/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package healthclub;
import java.util.*;

/**
 *
 * @author AFAQ_Lite
 */
public class HealthClub {

    
    public static void main(String[] args) {
        
        Scanner n = new Scanner(System.in);
        System.out.println("are you member or coach ?");
        String ss = n.nextLine();
        System.out.println("do you have an account?y/n");
        String c = n.nextLine();

        if ("y".equals(c)){
            User user = new User();
            if (user.login())
            {
                if ("member".equals(ss)){
                    Member member1 = new Member(1,"John Doe"));

                    int choice;

                    do {
                        System.out.println("Member Information:");
                        System.out.println("Member ID: " + member1.getMemberId());
                        System.out.println("Name: " + member1.getName());
                        System.out.println();

                        System.out.println("Menu:");
                        System.out.println("1. View Subscription End Date");
                        System.out.println("2. View Coach");
                        System.out.println("3. View Schedule");
                        System.out.println("4. Receive Message");
                        System.out.println("0. Exit");
                        System.out.print("Enter your choice: ");
                        choice = n.nextInt();

                        switch (choice) {
                            case 1:
                                member1.viewSubscriptionEndDate();
                                break;
                            case 2:
                                member1.viewCoach();
                                break;
                            case 3:
                                member1.viewSchedule();
                                break;
                            case 4:
                                member1.receiveSubscriptionEndNotification();
                                break;
                            case 0:
                                System.out.println("Exiting program. Goodbye!");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (choice != 0);
                    

                } 
                else if ("coach".equals(ss))
                {
                    Coach coach1 = new Coach(1, "Coach A");

            int choice;

            do {
                

                System.out.println("Menu:");
                System.out.println("1. Create Plan");
                System.out.println("2. Create Schedule");
                System.out.println("3. Send Message to Members");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = n.nextInt();

                switch (choice) {
                    case 1:
                        coach1.createPlan("1"); // Pass memberId as an example
                        break;
                    case 2:
                        coach1.createSchedule(); // Pass memberId as an example
                        break;
                    case 3:
                        coach1.sendMessages(members,"sdasa "); // You may want to pass a list of members here
                        break;
                    case 0:
                        System.out.println("Exiting program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                                }
            } while (choice != 0);
                }
            else if ("admin".equals(ss))
            {
                System.out.print("Enter your username: ");
                String enteredUsername = n.nextLine();

                System.out.print("Enter your password: ");
                String enteredPassword = n.nextLine(); 
                if (enteredUsername == "doaa" && enteredPassword == "111")
                {
                    administrative admin = new administrative(1, "admin", "adminpass");

                    int choice;
                    System.out.println("Admin Menu:");
                    System.out.println("1. Alter Admin Credentials");
                    System.out.println("2. Manage Coaches");
                    System.out.println("3. Manage Members");
                    System.out.println("4. Manage Billing");
                    System.out.println("5. Generate Member Reports");
                    System.out.println("6. Assign Coach to Member");
                    System.out.println("7. Send Subscription End Notification");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");
                    choice = n.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter new username: ");
                            String newUsername = n.next();
                            System.out.print("Enter new password: ");
                            String newPassword = n.next();
                            admin.alterCredentials(newUsername, newPassword);
                            break;
                        case 2:
                            admin.manageCoaches();
                            break;
                        case 3:
                            admin.manageMembers();
                            break;
                        case 4:
                            admin.manageBilling();
                            break;
                        case 5:
                            admin.generateMemberReports();
                            break;
                        case 6:
                            System.out.print("Enter member ID: ");
                            int memberId = scanner.nextInt();
                            System.out.print("Enter coach ID: ");
                            int coachId = scanner.nextInt();
                            admin.assignCoach(memberId, coachId);
                            break;
                        case 7:
                            System.out.print("Enter member ID: ");
                            int memberIdForNotification = scanner.nextInt();
                            admin.sendSubscriptionEndNotification(memberIdForNotification);
                            break;
                        case 0:
                            System.out.println("Exiting admin menu. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } while (choice != 0);
                        }
            }
            else
                System.out.println("invaild username or password");
        }
            
        }else if ("n".equals(c)){
            
            User user = new User();
            user.signup(ss);
        }
            
    }

    
}
