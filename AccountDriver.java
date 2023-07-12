import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

// App
public class AccountDriver {

    public static void main(String [] args) {
        // To take user input -
        Scanner userInput = new Scanner(System.in);
        // List of accounts -
        List<Account> accounts= new ArrayList<>();
        int accNumber = 0;
        int selectedOption;
        do {
            selectedOption = menu(userInput);
            System.out.println("----------Bank account service---------");
            if(selectedOption == 1) {
                try{
                accounts.add(createAccount(userInput, accounts));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            } else if(selectedOption == 2) {
                doDeposit(accounts, userInput);
            } else if(selectedOption == 3) {
                try{
                    doWithdraw(accounts, userInput);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            } else if(selectedOption == 4) {
                showStatement(userInput, accounts);
            }
            else {
                System.out.println("GoodBye!");
            }
            System.out.println();
        } while(selectedOption != 5);
    }

    /**
     * Method to search account index in the list
     */
    public static int searchAccount(List<Account> accounts, int accountNumber) {
        return IntStream.range(0, accounts.size())
                .filter(i -> accounts.get(i).getAccountNumber() == accountNumber).findFirst().orElse(-1);
    }

    /**
     * Method to deposit in a selected account
     */
    public static void doDeposit(List<Account> accounts , Scanner userInput) {
        // Get the account number -
        System.out.print("\nPlease enter account number: ");
        int accountNumber = userInput.nextInt();

        // Search for account -
        int found = searchAccount(accounts,  accountNumber);

        if(found == -1) {
            System.out.println("No account exist with account number: " + accountNumber);
        } else {
            // Amount -
            System.out.print("Please enter the deposit amount : ");
            double amount = userInput.nextDouble();
            accounts.get(found).deposit(amount);

        }
    }

    public static void doWithdraw(List<Account>accounts, Scanner userInput) throws Exception {
        // Get the account number -
        int accountNumber;
        System.out.print("\nPlease enter account number: ");
        if (userInput.hasNext()){
            accountNumber = userInput.nextInt();
        }else {
            throw new ArithmeticException("Incorrect account number");
        }

        // search for account
        int found = searchAccount(accounts, accountNumber);

        if(found == -1) {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        } else {
            // Amount -
            System.out.print("Please enter Withdraw Amount: ");
            double amount = userInput.nextDouble();
            System.out.println("test here : " + accounts.get(found).getBalance() +  " idx " + found);
            accounts.get(found).withdraw(amount);
        }
    }


    /**
     * Method to create an account
     */
    public static Account createAccount(Scanner userInput, List<Account>accounts) throws InstanceAlreadyExistsException {

        Account account = null;
        int accountNumber;
        System.out.print("Enter your account number please : ");
        accountNumber = userInput.nextInt();
        int found = searchAccount(accounts, accountNumber);
        if (found == -1 ){
            account = new SavingsAccount(accountNumber);
        }else {
            throw new InstanceAlreadyExistsException("Account already exists!");
        }
        return account;
    }

    /**
     *  Method to show statement
     */
    public static void showStatement(Scanner userInput, List<Account>accounts){
        int accountNumber;
        System.out.print("Enter your account number please : ");
        accountNumber = userInput.nextInt();
        int found = searchAccount(accounts, accountNumber);
        if (found == -1 ){
            System.out.println("Account does not exist !");
        }else {
            accounts.get(found).printTransaction();
        }
    }

    /**
     * Menu to display options and get the user's selection
     * 
     * @param userInput
     * @return selected option
     */
    public static int menu(Scanner userInput) {
        System.out.println("Main Menu :");
        System.out.println("1. Create an account");
        System.out.println("2. Deposit funds");
        System.out.println("3. Withdraw funds");
        System.out.println("4. Print statement");
        System.out.println("5. Quit");

        int selectedOption;

        do {
            System.out.print("Enter an option number from the list : ");
            selectedOption = userInput.nextInt();
        } while(selectedOption < 1 || selectedOption > 5);

        return selectedOption;
    }    
}
