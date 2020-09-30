package chadchat.app;

import chadchat.domain.User;

import java.sql.SQLException;

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

            case "3":
                return null;

        }
        return null;
    }
}
