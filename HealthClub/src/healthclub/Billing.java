package healthclub;

import java.io.*;
import java.util.Scanner;

public class Billing {
    private int billingId;
    private FileManager fileManager;

    public Billing(int billingId) {
        this.billingId = billingId;
        this.fileManager = new FileManager("billing_data.txt");
    }

    public void generateBill(int memberId) {
        // Retrieve member information
        Member member = getMemberById(memberId);

        // Check if the member exists
        if (member != null) {
            try {
                // Perform billing logic (e.g., calculate charges based on membership type)
                double charges = calculateCharges(member);

                // Display the generated bill
                System.out.println("Generated Bill for Member " + member.getMemberId() + ":");
                System.out.println("Member Name: " + member.getName());
                System.out.println("Charges: $" + charges);

                // Save the billing information to a file
                fileManager.writeToFile(memberId + "," + charges);
            } catch (Exception e) {
                // Handle billing generation error
                e.printStackTrace();
                System.out.println("Error generating bill for Member " + memberId);
            }
        } else {
            System.out.println("Member with ID " + memberId + " not found.");
        }
    }

    public void makePayment(int memberId) {
        // Retrieve member information
        Member member = getMemberById(memberId);

        // Check if the member exists
        if (member != null) {
            try {
                // Retrieve billing information from the file
                String billingData = fileManager.readFromFile(memberId);

                // Check if billing information exists
                if (billingData != null) {
                    // Parse the billing data and process the payment
                    double charges = Double.parseDouble(billingData.split(",")[1]);
                    System.out.println("Payment of $" + charges + " received from Member " + member.getMemberId());
                } else {
                    System.out.println("No billing information found for Member " + member.getMemberId());
                }
            } catch (Exception e) {
                // Handle payment processing error
                e.printStackTrace();
                System.out.println("Error processing payment for Member " + memberId);
            }
        } else {
            System.out.println("Member with ID " + memberId + " not found.");
        }
    }

    private Member getMemberById(int memberId) {
        // Retrieve member information from the file
        try (Scanner scanner = new Scanner(new File("members.txt"))) {
            while (scanner.hasNextLine()) {
                String[] memberData = scanner.nextLine().split(",");
                int currentMemberId = Integer.parseInt(memberData[0]);
                String name = memberData[1];
                // Assuming "subscriptionEndDate" is the third element in the array
                String subscriptionEndDate = memberData[2];

                if (currentMemberId == memberId) {
                    return new Member(currentMemberId, name, subscriptionEndDate);
                }
            }
        } catch (FileNotFoundException e) {
            // Handle file not found error
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other errors
            e.printStackTrace();
        }

        return null;
    }

    private double calculateCharges(Member member) {
        // Simplified billing logic (you can implement your own logic here)
        // For example, you can calculate charges based on membership type, usage, etc.
        return 50.0; // Fixed charges for demonstration purposes
    }

    public static void main(String[] args) {
        // Example usage
        Billing billing = new Billing(1);

        // Assume memberId is obtained from user input
        int memberId = 123;

        billing.generateBill(memberId);
        billing.makePayment(memberId);
    }
}