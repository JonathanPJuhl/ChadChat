package chadchat.app;

import chadchat.domain.User;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

public class StartMenu {


    public StartMenu(TUI tui) {
        this.tui = tui;
    }


    private final TUI tui;
    public User startChadChat(int answer) throws SQLException, ClassNotFoundException {
        String response = Integer.toString(answer);
        switch (response){
            case "1":
                return tui.loginPage();

            case "2": tui.signupPage();
            return null;


        }
        return null;
    }
    public final User messageMenu(int answer) throws SQLException, ClassNotFoundException {

        String response = Integer.toString(answer);
        switch (response){
            case "1":
                return null;

            case "2": //tui.signupPage();
                tui.privateMessagePrompt();
                return null;


        }
        return null;
    }
}
