package chadchat.ui;

import chadchat.app.StartMenu;
import chadchat.app.TUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;

public class Protocol{

    private final Scanner in;
    private final InputStream inputStream;
    private final PrintWriter out;
    private final Protocol superThis;

    public Protocol(InputStream inputStream, PrintWriter out) {
        this.in = new Scanner(inputStream);
        this.inputStream = inputStream;
        this.out = out;
        this.superThis = this;
    }

    public void run() throws SQLException, ClassNotFoundException {
        TUI tui = new TUI(in, out);
        StartMenu start = new StartMenu(tui);
        start.startChadChat(tui.welcomeMessage());

    }
}