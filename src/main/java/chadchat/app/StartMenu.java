package chadchat.app;

import java.sql.SQLException;

public class StartMenu {

    public StartMenu(TUI tui) {

        this.tui = tui;
    }


    private final TUI tui;
    public void startChadChat(int answer) throws SQLException, ClassNotFoundException {
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
