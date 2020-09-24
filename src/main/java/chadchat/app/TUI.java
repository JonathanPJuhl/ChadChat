package chadchat.app;

import chadchat.db.DBConnect;
import chadchat.db.SqlStatements;
import chadchat.domain.User;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TUI {

    private final PrintWriter message;
    private final Scanner userInput;

    public TUI(PrintWriter message, Scanner userInput) {
        this.message = message;
        this.userInput = userInput;
    }

    public int welcomeMessage(){
        message.println("Welcome to ChadChat you have the following options:");
        message.println("1 login");
        message.println("2 Signup");
        message.println("3 exit");
        message.flush();
        int answer = userInput.nextInt();
        return answer;
    }
    //change login to signIn signup with a uppercase U
    public User loginPage(){
        String userName = "";
        String password = "";
        message.print("Please type in username: ");
        message.flush();
        userName = userInput.nextLine();
        //password tastet ind som "*"
        message.print("Please type in password: ");
        message.flush();
        password = userInput.nextLine();
        return new User(userName, password);
    }

    public User signupPage() throws SQLException, ClassNotFoundException {
        DBConnect db = new DBConnect();
        String userName = "";
        String eMail = "";
        String password = "";
        String password1 = "";
        String password2 = "";
        boolean doesUsernameExist = false;
        boolean doesEmailExist = false;
        boolean doPasswordsMatch = false;

        int iD = 0;
        message.println("Welcome, please sign up! ");
        message.flush();

    //check if username is already in db
    // if already exists, prompt for name once more

        while(!doesUsernameExist) {
            message.print("Please type in username: ");
            message.flush();
            userName = userInput.nextLine();
            ResultSet rs = db.executeQuery(SqlStatements.doesUsernameAlreadyExist(userName));
            if(!rs.next()){
                doesUsernameExist=true;

            }else {message.println("Username taken, please try another one.");}
        }
    //When username is ok, ask for email,
        // Check again and prompt if necessary
        while(!doesEmailExist) {
            message.print("Please type in email: ");
            message.flush();
            eMail = userInput.nextLine();
            ResultSet rs = db.executeQuery(SqlStatements.doesEmailAlreadyExist(eMail));
            if(!rs.next()){
                doesEmailExist=true;

            }else {message.println("Email taken, please try another one.");}
        }

    //When username & mail check is okay, prompt for password and pass it to SHA256
    //which will then input hexcode into db.
        while(!doPasswordsMatch) {
            message.println("Please enter a password: ");
            message.flush();
            password = userInput.nextLine();
            message.println("Please reenter password: ");
            message.flush();
            password1 = userInput.nextLine();
            //method for making sure pass is correct
            if (password == password1) {
                password2 = SHA256.sha256(password);
                doPasswordsMatch = true;
            } else{
                message.println("Passwords do not match, please try again!");
                message.flush();
            }
        }
    //Make method to find last id from db
    //make the id for this user be that
        iD = db.getLatestIDFromDB(SqlStatements.getAllIds());

        return new User(iD, userName, eMail, password2);
    }
}
