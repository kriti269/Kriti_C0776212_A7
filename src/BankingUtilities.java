import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 */
public class BankingUtilities {
    public static void main(String[] args){
        String[] userCredentials = new String[]{"rohan:rohan123", "meena:meena123", "birdie:birdie123", "sky:sky123"};
        User user = new User(new ArrayList(Arrays.asList(userCredentials)));
        if(user.getBankAccount()!=null){
            String option = "";
            do{
                option = JOptionPane.showInputDialog("Choose an option: \n" +
                        "1. Withdraw amount.\n" +
                        "2. Deposit amount.\n" +
                        "3. Check balance.\n" +
                        "4. Exit ");
                switch (option){
                    case "1":
                        user.getBankAccount().withdrawAmount();
                        break;
                    case "2":
                        user.getBankAccount().depositAmount();
                        break;
                    case "3":
                        user.getBankAccount().checkBalance();
                        break;
                    case "4":
                        user.getBankAccount().checkBalance();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Invalid option. Choose again!");
                }
            }while(!option.equals("4"));
        }

    }

}
