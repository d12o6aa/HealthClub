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
                    
                }
            }
            else
                System.out.println("invaild username or password");
            
        }else if ("n".equals(c)){
            
            User user = new User();
            user.signup(ss);
        }
            
    }

    
}
