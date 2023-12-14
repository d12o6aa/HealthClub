package healthclub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MemberRepository {
    private static final Scanner input = new Scanner(System.in);
    private static final FileManager fileManager = new FileManager("member_data.txt");

    public static void addMember() {
        int memberId = new Random().nextInt(100);

        System.out.println("Enter Member Name:");
        String name = input.nextLine();

        System.out.println("Enter Member Username:");
        String username = input.nextLine();

        System.out.println("Enter Member Password:");
        String password = input.nextLine();

        Member member = new Member(memberId, username, password, name, null);
        fileManager.saveToFile(member.toString());
        System.out.println("Member added successfully!");
    }

    public static void deleteMember() {
        System.out.println("Enter Member ID to delete:");
        int memberIdToDelete = input.nextInt();
        input.nextLine();

        List<String> membersData = fileManager.readFromFileLines();
        List<String> newMembersData = new ArrayList<>();

        boolean memberDeleted = false;

        for (String memberInfo : membersData) {
            int currentMemberId = Integer.parseInt(memberInfo.split(",")[0].trim());
            if (currentMemberId == memberIdToDelete) {
                System.out.println("Member deleted successfully!");
                memberDeleted = true;
            } else {
                newMembersData.add(memberInfo);
            }
        }

        if (!memberDeleted) {
            System.out.println("Member not found with ID: " + memberIdToDelete);
        } else {
            fileManager.saveToFileLines(newMembersData);
        }
    }


    public static void updateMember() {
        System.out.println("Enter Member ID to update:");
        int memberIdToUpdate = input.nextInt();
        input.nextLine();

        List<String> membersData = fileManager.readFromFileLines();
        List<String> newMembersData = new ArrayList<>();

        for (String memberInfo : membersData) {
            int currentMemberId = Integer.parseInt(memberInfo.split(",")[0].trim());
            if (currentMemberId == memberIdToUpdate) {
                System.out.println("Enter new Member Name:");
                String newName = input.nextLine();
                System.out.println("Enter new Member Username:");
                String newUsername = input.nextLine();
                System.out.println("Enter new Member Password:");
                String newPassword = input.nextLine();

                // Update the memberInfo
                String updatedMemberInfo = memberIdToUpdate + ", " + newName + ", " + newUsername + ", " + newPassword;
                newMembersData.add(updatedMemberInfo);
            } else {
                newMembersData.add(memberInfo);
            }
        }

        fileManager.saveToFileLines(newMembersData);
        System.out.println("Member updated successfully!");
    }


    public static void getMemberById() {
        System.out.println("Enter Member ID to retrieve:");
        int memberIdToRetrieve = input.nextInt();
        input.nextLine();

        List<String> membersData = fileManager.readFromFileLines();

        for (String memberInfo : membersData) {
            int currentMemberId = Integer.parseInt(memberInfo.split(",")[0].trim());
            if (currentMemberId == memberIdToRetrieve) {
                System.out.println("Member Information:");
                System.out.println(memberInfo);
                return;
            }
        }

        System.out.println("Member not found with ID: " + memberIdToRetrieve);
    }
    public static void getAllMembers() {
        List<String> membersData = fileManager.readFromFileLines();

        System.out.println("All Members Information:");
        for (String memberInfo : membersData) {
            System.out.println(memberInfo);
            System.out.println("--------");
        }
    }
}