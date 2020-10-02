package chadchat.ui;
//scp target/chadchat.jar jonathan@165.22.78.140:/opt/chadchat FROM MASTER BRANCH IN GIT
import chadchat.api.ChadChat;
import chadchat.domain.Message;
import chadchat.domain.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client implements Runnable, ChadChat.MessageObserver {
    private final InputStream in;
    private final OutputStream out;
    private final ChadChat chadChat;
    private static boolean keepTexting;

    public Client(InputStream in, OutputStream out, ChadChat chadChat) {
        this.in = in;
        this.out = out;
        this.chadChat = chadChat;
    }

    @Override
    public void run() {
        keepTexting = true;
        chadChat.registerMessageObserver(this);

        final Scanner scanner = new Scanner(in);
        User user = chadChat.runStartMenu(in, new PrintWriter(out, true));
        while (keepTexting) {
            chadChat.createMessage(user.getName(), scanner.nextLine());

        }
        run();
    }
    public static void setKeeptexting(){

        keepTexting = false;
    }

    @Override
    public void notifyNewMessages(Message msg) {

        new PrintWriter(out, true).println(msg);
    }
}
