package chadchat.ui;

import chadchat.api.ChadChat;
import chadchat.app.SqlController;
import chadchat.app.StartMenu;
import chadchat.app.TUI;
import chadchat.domain.Message;
import chadchat.domain.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

public class Client implements Runnable, ChadChat.MessageObserver {
    private final InputStream in;
    private final OutputStream out;
    private final ChadChat chadChat;

    public Client(InputStream in, OutputStream out, ChadChat chadChat) {
        this.in = in;
        this.out = out;
        this.chadChat = chadChat;
    }

    @Override
    public void run() {
        chadChat.registerMessageObserver(this);

        final Scanner scanner = new Scanner(in);
        chadChat.runStartMenu(in, new PrintWriter(out, true));
        while (true) {

            chadChat.createMessage(null, scanner.nextLine());
        }
    }

    @Override
    public void notifyNewMessages(Message msg) {
        new PrintWriter(out, true).println(msg);
    }
}
