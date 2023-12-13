package healthclub;
import java.util.*;

public class Billing {
    private int billingId;
    private FileManager fileManager;

    public Billing(int billingId) {
        this.billingId = billingId;
        this.fileManager = new FileManager("billing_data.txt");
    }

    public void generateBill(int memberId) {
        // Implementation for generating a bill for a member
    }

    public void makePayment(int memberId) {
        // Implementation for processing a payment for a member
    }
}