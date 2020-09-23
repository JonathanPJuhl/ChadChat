package chadchat.app;

import java.io.PrintWriter;
import java.util.Scanner;

public class StartMenu {
   // TUI tui = new TUI(new Scanner(System.in), new PrintWriter(System.out));
    private TUI tui;
    public void startChadChat(int answer){
        String response = Integer.toString(answer);
        switch (response){
            case "1": tui.loginPage();
                break;
            case "2": tui.signupPage();
                break;
            case "3":
                break;
        }
    }
}
