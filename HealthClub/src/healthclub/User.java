package healthclub;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Scanner;

public class User {
    private static int coachId;
    private static int memberId;
    private int userId;
    private String username;
    private String password;
    private FileManager fileManager;

    public User(){
        
    }
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public void signup(String user) {
        String ss = user;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating a new account...");

        System.out.print("Enter a username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter a password: ");
        String newPassword = scanner.nextLine();

        int newUserId = generateUserId();

        User newUser = new User(newUserId, newUsername, newPassword);
        newUser.fileManager = new FileManager("user_data.txt");
        newUser.saveToFile();

        System.out.println("Account created successfully.");
        System.out.println("your id is : "+ newUser.getUserId());
    }
    public boolean login() {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int i;
                for (i=0;i<3;i++) {
                    if (i == 1 && parts[i].equals(enteredUsername) && parts[i+1].equals(enteredPassword)){
                        return true;
                    }
                    
                    
                }
            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Login failed. Invalid username or password.");
        return false;
    }

    public int logout() {
        System.out.println("User " + username + " logged out.");
        return (0);
    }

    public void updateInformation(String newPassword) {
        this.password = newPassword;
        System.out.println("User " + username + " updated password.");
    }

    private int generateUserId() {
        return (int) (Math.random() * 1000);
        
//        if ("member".equals(s))
//            return (this.memberId++);
//        else if ("coach".equals(s))
//            return coachId++;
//        else
//            return 0;
    }

    private void saveToFile() {
        String data = userId + "," + username + "," + password;
        fileManager.saveToFile(data);
    }

    // Getters and setters for user fields

    public int getUserId() {
        return userId;
    }

//    public int getUserId() {
//        return userId;
//    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    private String split(String s,int i){
        String ss = s;
        int x =0;
        String c3= "";
        String c2= "";
        String c1 = "";
        char[] chars = ss.toCharArray();
        for (char c:chars)
        {
            if (x ==0 ){
                if (c !=',')
                    c1 +=c;
                else
                    x++;
            } else if (x == 1){
                if (c !=',')
                    c2 +=c;
                else
                    x++;
            } else if (x == 1 && c !='\n'){
                c3 +=c;
            }
        }
        
        if (i == 1)
            return c1;
        else if (i == 2)
            return c2;
        else if (i == 3)
            return c3;
        return null;
        
    }
}