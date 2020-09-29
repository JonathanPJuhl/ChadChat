package chadchat.app;

import chadchat.db.DBConnect;
import chadchat.db.SqlStatements;
import chadchat.domain.User;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TUI {

    private final PrintWriter message;
    private final InputStream userInput;

    public TUI(InputStream userInput, PrintWriter message) {
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
    public void loginPage() throws SQLException, ClassNotFoundException {
        DBConnect db = new DBConnect();
        String userName = "";
        String password = "";
        String password2 = "";
        boolean doPassWordsMatch = false;
        userInput.nextLine();
        while(!doPassWordsMatch) {

            message.println("Please type in username: ");
            message.flush();
            userName = userInput.nextLine();

            //password tastet ind som "*"
            message.println("Please type in password: ");
            message.flush();
            password = userInput.nextLine();

            //Make method that checks username vs pass in DB
            ResultSet rs = db.executeQuery(SqlStatements.checkPassword(new User(userName, password)));
            while (rs.next()) {
                password2 = rs.getString("PassWord");
            }
            if (SHA256.sha256(password).equals(password2)) {
                doPassWordsMatch = true;
                //Login user to a place where user can start chat with a given user
                //User has an option to view unread messages
                //User can join chatrooms
            } else {
                message.println("Wrong username or password, try again. \n");
                message.flush();
            }
        }
    }

    public void signupPage() throws SQLException, ClassNotFoundException {
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
        message.println("Welcome, please sign up!");
        message.flush();
        userInput.nextLine();

    //check if username is already in db
    // if already exists, prompt for name once more

        while(!doesUsernameExist) {
            message.println("Please type in username: ");
            message.flush();
            userName = userInput.nextLine();
            ResultSet rs = db.executeQuery(SqlStatements.doesUsernameAlreadyExist(userName));

            if(!rs.next()){
                doesUsernameExist=true;

            }else {
                message.println("Username taken, please try another one.");
                message.flush();
            }

        }
    //When username is ok, ask for email,
        // Check again and prompt if necessary
        while(!doesEmailExist) {
            message.println("Please type in email: ");
            message.flush();
            eMail = userInput.nextLine();
            ResultSet rs = db.executeQuery(SqlStatements.doesEmailAlreadyExist(eMail));
            if(!rs.next()){
                doesEmailExist=true;

            }else {
                message.println("Email taken, please try another one.");}
                message.flush();
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
            if (password.equals(password1)) {
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

    //Creates a user in the database based on previous input information.
        db.executeUpdate(SqlStatements.insertUserIntoDB(new User(iD, userName, password2, eMail)));
    }
}
