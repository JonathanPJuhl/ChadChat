package chadchat.app;

import chadchat.api.ChadChat;
import chadchat.db.DBConnect;
import chadchat.db.SqlStatements;
import chadchat.domain.User;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class TUI {

    private PrintWriter message;
    private Scanner userInput;
    private User user;
    ArrayList<User> users = new ArrayList<>();


    public TUI(Scanner userInput, PrintWriter message, User user) {
        this.message = message;
        this.userInput = userInput;
        this.user = user;
    }
    public TUI(Scanner userInput, PrintWriter message) {
        this.message = message;
        this.userInput = userInput;

    }

    public int welcomeMessage(){
        message.println("!clear");
        message.println("Welcome to ChadChat you have the following options:");
        message.println("1 login");
        message.println("2 Signup");
        message.flush();
        int answer = userInput.nextInt();
        message.println(answer);
        message.flush();
        return answer;
    }
    public int loginMessage(){
        message.println("Welcome to ChadChat, you have the following options:");
        message.println("1 Enter openchat");
        message.println("2 Send private message");
        message.println("3 Read messages");
        message.flush();
        int answer = userInput.nextInt();
        if(answer == 1){
            message.println("!clear");
            message.println("Welcome to the open chat,try sending a message! (type: !.return to go back to main menu)");
            message.flush();
        }
        return answer;
    }
    public void privateMessagePrompt(String userName) throws SQLException, ClassNotFoundException {
        DBConnect db = new DBConnect();
        boolean doesUserExist = false;
        int userId = 0;
        String recipient ="";
        //Ask for a username
        while(!doesUserExist) {
            userInput.nextLine();
            message.println("Please choose recipient!: ");
            message.flush();
            recipient = userInput.nextLine();
            //doesRecipientExist
            ResultSet rs = db.executeQuery(SqlStatements.doesUsernameAlreadyExist(recipient));
            if (!rs.next()) {
                message.println("User cannot be found, please try again!");
                message.flush();
            } else {
                //PreparedStatement ps = db.prepareStatement(SqlStatements.findUserIdFromUserName());
                //ps.setString(1, "'"+recipient+"'");
                ResultSet rs2 =  db.executeQuery(SqlStatements.findUserIdFromUserName(userName));;

                while(rs2.next()){
                    userId = rs.getInt("ID");
                }
                doesUserExist = true;
            }
        }
        message.println("Recipient: " + recipient);
        message.flush();
        ResultSet rs3 = db.executeQuery(SqlStatements.doesUsernameAlreadyExist(userName));
        while (rs3.next()){
            User u = new User(rs3.getInt("ID"), userName, rs3.getString("PassWord"), rs3.getString("Email"));
            users.add(u);
        }

        //Perhaps make it possible to search for users
        message.println("Please type your message: ");
        message.flush();
        String message2 = userInput.nextLine();

        db.executeUpdate(SqlStatements.sendUserAMessage(message2, users.get(0), userId));
        //When a user is selected input a message for general db, and connect it via user id's


        return;
    }
    //change login to signIn signup with a uppercase U
    public User loginPage() throws SQLException, ClassNotFoundException {
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
            message.println(userName);
            message.flush();
            //password tastet ind som "*"
            message.println("Please type in password: ");
            message.flush();
            password = userInput.nextLine();
            for(int i = 0; i<=password.length(); i++){
                message.print("*");
                message.flush();
            }
            message.println("");
            message.flush();
            //Make method that checks username vs pass in DB
            ResultSet rs = db.executeQuery(SqlStatements.checkPassword(new User(userName, password)));
            while (rs.next()) {
                password2 = rs.getString("PassWord");
            }
            if (SHA256.sha256(password).equals(password2)) {
                doPassWordsMatch = true;
                message.println("Login successful!");
                message.println("!clear");
                message.flush();
                StartMenu startMenu = new StartMenu(new TUI(userInput, message));
                startMenu.messageMenu(loginMessage(), userName);
                return new User(userName, password);
                //Login user to a place where user can start chat with a given user
                //User has an option to view unread messages
                //User can join chatrooms
            } else {
                 message.println("Wrong username or password, try again. \n");
                message.flush();
            }
        }
        return null;
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
            message.println(userName);
            message.flush();
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
            message.println(eMail);
            message.flush();

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
            for(int i = 0; i<=password.length(); i++){
                message.print("*");
                message.flush();
            }
            message.println("");
            message.flush();

            message.println("Please reenter password: ");
            message.flush();
            password1 = userInput.nextLine();
            for(int i = 0; i<=password1.length(); i++){
                message.print("*");
                message.flush();
            }
            message.println("");
            message.flush();

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
        userInput.nextLine();
        message.println("User created succesfully!");
        message.flush();
        userInput.nextLine();
        StartMenu startMenu = new StartMenu(new TUI(userInput, message, new User(iD, userName, password2, eMail)));
        startMenu.startChadChat(welcomeMessage());

    }

    public void readMyMessage(String userName) throws SQLException, ClassNotFoundException {
        DBConnect db = new DBConnect();
        int id = 0;
        String user = "";
        String messages = "";
        String time = "";
        //PreparedStatement ps = db.prepareStatement(SqlStatements.findUserIdFromUserName());
        //ps.setString(1, userName);
        ResultSet rs = db.executeQuery(SqlStatements.findUserIdFromUserName(userName));
    while (rs.next()){
            id = rs.getInt("ID");
        }
        message.println("ID: " + id);
        message.flush();
        message.println("user: " + userName);
        message.flush();
        ResultSet rs2 = db.executeQuery(SqlStatements.readMyMessages(id));

        while(rs2.next()){
            user = rs2.getString("senderName");
            messages = rs2.getString("message");
            time = rs2.getString("sendTime");
            userInput.nextLine();
        }
        message.println(user + " " + messages + " " + time);
        message.flush();
    }
}
