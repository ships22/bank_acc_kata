import java.util.HashMap;
import java.util.Map;

// Base clase
public abstract class Account {

    // Account number -
    private int accountNumber;

    // Balance -
    protected double balance;

    // Transaction details -
    protected Map<String, String> transaction;

    // Default constructor -
    public Account() { }

    // Constructor with parameter -
    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        balance = 0;
        transaction = new HashMap<String, String>();
    }

    // Getter methods -
    public double getBalance() {
        return this.balance;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public Map getTransactions(){return this.transaction;}

    // Abstract method -
    /**
     * Method to deposit funds into the account as long as
     * the amount is positive
     * 
     * @param amount value to be deposited
     */
    public abstract void deposit(double amount); 

    /**
     * Method to withdraw funds from the Account as long as amount is positive
     * and sufficient fund available in the account
     * @param amount value to be withdrawn
     */    
    public abstract void withdraw(double amount);

    /**
     * Method to update transaction details
     * @param description
     */
    protected abstract void saveTransaction(String description);

    /**
     * Method to print transaction details
     */
    public abstract void printTransaction();

}




