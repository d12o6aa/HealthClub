package healthclub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CoachRepository {
    private static final Scanner input = new Scanner(System.in);
    private static final FileManager fileManager = new FileManager("coach_data.txt");

    public static void addCoach() {
        int coachId = new Random().nextInt(100);


        System.out.println("Enter Coach Name:");
        String name = input.nextLine();

        System.out.println("Enter Coach Username:");
        String username = input.nextLine();

        System.out.println("Enter Coach Password:");
        String password = input.nextLine();

        Coach coach = new Coach(coachId, username, password, name);
        fileManager.saveToFile(coach.toString());
        System.out.println("Coach added successfully!");
    }

    public static void deleteCoach() {
        System.out.println("Enter Coach ID to delete:");
        int coachIdToDelete = input.nextInt();
        input.nextLine();

        List<String> coachesData = fileManager.readFromFileLines();
        List<String> newCoachesData = new ArrayList<>();

        boolean coachDeleted = false;

        for (String coachInfo : coachesData) {
            int currentCoachId = Integer.parseInt(coachInfo.split(",")[0].trim());
            if (currentCoachId == coachIdToDelete) {
                System.out.println("Coach deleted successfully!");
                coachDeleted = true;
            } else {
                newCoachesData.add(coachInfo);
            }
        }

        if (!coachDeleted) {
            System.out.println("Coach not found with ID: " + coachIdToDelete);
        } else {
            fileManager.saveToFileLines(newCoachesData);
        }
    }

    public static void updateCoach() {
        System.out.println("Enter Coach ID to update:");
        int coachIdToUpdate = input.nextInt();
        input.nextLine();

        List<String> coachesData = fileManager.readFromFileLines();
        List<String> newCoachesData = new ArrayList<>();

        for (String coachInfo : coachesData) {
            int currentCoachId = Integer.parseInt(coachInfo.split(",")[0].trim());
            if (currentCoachId == coachIdToUpdate) {
                System.out.println("Enter new Coach Name:");
                String newName = input.nextLine();
                System.out.println("Enter new Coach Username:");
                String newUsername = input.nextLine();
                System.out.println("Enter new Coach Password:");
                String newPassword = input.nextLine();

                // Update the coachInfo
                String updatedCoachInfo = coachIdToUpdate + ", " + newName + ", " + newUsername + ", " + newPassword;
                newCoachesData.add(updatedCoachInfo);
            } else {
                newCoachesData.add(coachInfo);
            }
        }

        fileManager.saveToFileLines(newCoachesData);
        System.out.println("Coach updated successfully!");
    }

    public static void getCoachById() {
        System.out.println("Enter Coach ID to retrieve:");
        int coachIdToRetrieve = input.nextInt();
        input.nextLine();

        List<String> coachesData = fileManager.readFromFileLines();

        for (String coachInfo : coachesData) {
            int currentCoachId = Integer.parseInt(coachInfo.split(",")[0].trim());
            if (currentCoachId == coachIdToRetrieve) {
                System.out.println("Coach Information:");
                System.out.println(coachInfo);
                return;
            }
        }

        System.out.println("Coach not found with ID: " + coachIdToRetrieve);
    }

    public static void getAllCoaches() {
        List<String> coachesData = fileManager.readFromFileLines();

        System.out.println("All Coaches Information:");
        for (String coachInfo : coachesData) {
            System.out.println(coachInfo);
            System.out.println("--------");
        }
    }
}
