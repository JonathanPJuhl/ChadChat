package chadchat.entries;

import chadchat.app.SqlController;
import chadchat.app.StartMenu;
import chadchat.app.TUI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Migrate.runMigrations();
        SqlController controller = new SqlController();
        controller.controller();
        PrintWriter pw = new PrintWriter(System.out);
        Scanner scanner = new Scanner(System.in);
        TUI tui = new TUI(pw, scanner);
        StartMenu start = new StartMenu(tui);
        start.startChadChat(tui.welcomeMessage());

    }




}
