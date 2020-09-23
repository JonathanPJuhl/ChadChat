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
        return null;
    }


}
