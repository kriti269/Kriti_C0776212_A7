import javax.swing.*;
import java.util.ArrayList;

/**
 * Represents a User.
 * @author Kriti
 */
public class User {
    private String username;
    private String password;
    private BankAccount bankAccount;

    /**
     * Initializes a user object after prompting and
     * verifying the user credentials from ArrayList users.
     * Ends after a 3 wrong retries.
     * @param users
     */
    public User(ArrayList users){
        String username="",password="";
        int numberOfTries = 0;
        boolean isAuthenticated = false;
        do{
            if(numberOfTries>=3) {
                JOptionPane.showMessageDialog(null, "Too many attempts. Try again later!");
                break;
            }
            username = JOptionPane.showInputDialog("Enter username: ");
            password = JOptionPane.showInputDialog("Enter password: ");
            numberOfTries++;
            isAuthenticated = isAuthorized(username,password,users);
        }while(!isAuthenticated && numberOfTries<=3);
        if(isAuthenticated) {
            this.setUsername(username);
            this.setPassword(password);
            this.setBankAccount(new BankAccount("src/BankFiles/"+username+".txt"));
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * Returns true if the username and password matches an entry
     * from users ArrayList otherwise returns false.
     * @param username
     * @param password
     * @param users
     * @return
     */
    private boolean isAuthorized(String username, String password, ArrayList users){
        if(username==null || password==null)
            return false;
        for(Object credentials: users){
            String[] credentialPair = ((String) credentials).split(":");
            if(username.equals(credentialPair[0]) && password.equals(credentialPair[1])){
                return true;
            }
        }
        return false;
    }
}
