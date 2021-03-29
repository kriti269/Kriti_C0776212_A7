import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Represents a Bank Account.
 * @author Kriti
 */
public class BankAccount {
    private File passbookFile;
    private double balance;

    /**
     * Initializes a bank account by reading balance from
     * given passbook file name.
     * @param passbookFileName
     */
    public BankAccount(String passbookFileName){
        File passbookFile = new File(passbookFileName);
        try{
            Scanner scanFile = new Scanner(passbookFile);
            this.setPassbookFile(passbookFile);
            String lastLine = "";
            while(scanFile.hasNext()){
                lastLine = scanFile.nextLine();
            }
            double balance = Double.parseDouble(lastLine.split("\\$")[1]);
            this.setBalance(balance);
            scanFile.close();

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Unable to access account information from passbook!");
        }
    }

    public File getPassbookFile() {
        return passbookFile;
    }

    public void setPassbookFile(File passbookFile) {
        this.passbookFile = passbookFile;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    /**
     * Calls inputAmount method to prompt the user for
     * an amount to be deposited and updates the balance
     * by calling setBalance method
     */
    public void depositAmount(){
        double amount = inputAmount("Please enter an amount to deposit: ");
        this.setBalance("credit",amount);
    }


    /**
     * Calls inputAmount method to prompt the user for
     * an amount to be withdrawn. Checks if minimum
     * $5.00 balance is maintained and updates the balance
     * by calling setBalance method
     */
    public void withdrawAmount(){
        double amount = inputAmount("Please enter an amount to withdraw: ");
        if(this.balance-amount>=5){
            this.setBalance("debit",amount);
        }
        else{
            JOptionPane.showMessageDialog(null,"Unable to withdraw! " +
                    "You need to maintain a minimum balance of $5");
        }
    }

    /**
     * Overloaded method, to set the balance based on transaction type
     * and deducts or adds the given amount.
     * @param transactionType
     * @param amount
     */
    private void setBalance(String transactionType, double amount){
        try{
            FileWriter fileWriter = new FileWriter(passbookFile, true);
            PrintWriter writer = new PrintWriter(fileWriter);
            if(transactionType.equalsIgnoreCase("debit")){
                writer.println(String.format("Debited: $%.2f",amount));
                this.balance -= amount;
            }
            else if(transactionType.equalsIgnoreCase("credit")){
                writer.println(String.format("Credited: $%.2f",amount));
                this.balance += amount;
            }
            writer.println(String.format("Your balance is: $%.2f",this.balance));
            writer.close();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error occurred while updating balance!");
        }
    }

    /**
     * A static method that takes a string message,
     * prompts for an amount to be withdrawn or deposited and
     * parses and returns it as type double.
     * @param message
     * @return double inputAmount
     */
    private static double inputAmount(String message){
        double inputAmount = 0;
        boolean validAmount = false;
        do{
            try{
                inputAmount = Double.parseDouble(JOptionPane.showInputDialog(message));
                validAmount = true;
            }catch (Exception ex){
                validAmount = false;
            }

        }while(!validAmount);
        return inputAmount;
    }

    /**
     * Reads the remaining balance from the passbook and
     * displays it to the user.
     */
    public void checkBalance(){
        try {
            Scanner scanFile = new Scanner(this.passbookFile);
            String lastLine = "";
            while(scanFile.hasNext()){
                lastLine = scanFile.nextLine();
            }

            this.balance = Double.parseDouble(lastLine.split("\\$")[1]);
            JOptionPane.showMessageDialog(null,"Your balance is: $"+this.balance);
            scanFile.close();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error occurred while reading balance.");
        }
    }
}
