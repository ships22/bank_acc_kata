import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

// Account with defined standard in the base class
public class SavingsAccount extends Account {

    // Default constructor -
    public SavingsAccount() {
        super();
    }

    /**
     * Constructor with parameter to intialize Savings account with an account number
     */
    public SavingsAccount(int accountNumber) {
        super(accountNumber);
    }

    // Getter function -
    /**
     * Method to deposit funds into the account
     * @param amount
     */
    public void deposit(double amount) {
        // Verification of positive amount -
        if (amount > 0) {
            balance += amount;
            saveTransaction("Deposit " + amount);
            System.out.printf("Your current balance is: %.2f%n", balance);
        } else {
            System.out.println("Invalid amount");
        }
    }

    /**
     * Method to withdraw funds
     * @param amount
     */
    public void withdraw(double amount) {
        // Verification of positive amount -
        if (amount > 0) {
            // Check for sufficient funds -
            if ((amount) <= balance) {
                System.out.printf("Amount of %.2f withdrawn from Account%n", amount);
                balance -= amount;
                saveTransaction("Withdraw " + amount);
                System.out.printf("Your current balance is: %.2f%n", balance);
            }else {
                System.out.println("You do not have sufficient funds!");
            }
        } else {
            System.out.println("Invalid amount!");
        }
    }

    /**
     * Method to save transactions
     * @param description
     */
    protected void saveTransaction(String description) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(new Date());
        transaction.put(date, description);
    }

    /**
     * Method to print transactions
     */
    public void printTransaction(){
        Map<String, String> transactionList = getTransactions();
        if (transactionList.isEmpty()){
            System.out.printf("No transaction yet.");
        }else {
            System.out.printf("          Transations          " + "\n");
            System.out.println("------------------------------");
            transactionList.forEach((key, value) -> System.out.printf(key + " " + value +" " + "\n") );
            System.out.println("Balance : " + getBalance());
        }
    }



}
