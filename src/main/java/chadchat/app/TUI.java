package chadchat.app;

import chadchat.domain.User;

import java.io.PrintWriter;
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

    public User signupPage(){
        String userName, eMail, password;
        int iD = 0;

    //check if username is already in db
    // if already exists, prompt for name once more
        userName = "";
    //When username is ok, ask for email,
        // Check again and prompt if necessary
        eMail = "";
    //When username & mail check is okay, prompt for password and pass it to SHA256
    //which will then input hexcode into db.
        password = SHA256.sha256("");
    //Make method to find last id from db
    //make the id for this user be that+1

        return new User(iD, userName, eMail, password);
    }
}
