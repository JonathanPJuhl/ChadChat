package chadchat.api;

import chadchat.app.SqlController;
import chadchat.app.StartMenu;
import chadchat.app.TUI;
import chadchat.domain.Message;
import chadchat.domain.User;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class ChadChat {
    private final Set<MessageObserver> messageObservers = new HashSet<>();
    private final List<Message> messages = new ArrayList<>();

    public void createMessage(User user, String message) {
        // Create message correctly.
        Message msg = new Message(message);
        messages.add(msg);

        synchronized (this) {
            for (MessageObserver messageObserver : messageObservers) {
                messageObserver.notifyNewMessages(msg);
            }
        }
    }
    public void runStartMenu(InputStream in, PrintWriter out){
        SqlController controller = new SqlController();
        try {
            controller.controller();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(out);
        final Scanner scanner = new Scanner(in);
        TUI tui = new TUI(scanner, pw);
        StartMenu start = new StartMenu(tui);
        try {
            start.startChadChat(tui.welcomeMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Iterable<Message> getNewMessages(LocalDateTime after) {
        // Database get messages
        return List.of(new Message("message"));
    }

    public synchronized void registerMessageObserver(MessageObserver observer) {
        messageObservers.add(observer);
    }

    public interface MessageObserver {
        void notifyNewMessages(Message msg);
    }

}
