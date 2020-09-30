package chadchat.entries;

import chadchat.api.ChadChat;
import chadchat.app.SqlController;
import chadchat.app.StartMenu;
import chadchat.app.TUI;
import chadchat.ui.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Server {
    private final static int PORT = 2222;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        //Migrate.runMigrations();
       /* SqlController controller = new SqlController();
        controller.controller();
        PrintWriter pw = new PrintWriter(System.out);
        //Scanner scanner = new Scanner(System.in);
        InputStream scanner = null;
        TUI tui = new TUI(scanner, pw);
        StartMenu start = new StartMenu(tui);
        start.startChadChat(tui.welcomeMessage());
*/
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            ChadChat chadChat = new ChadChat();
            System.out.println("Server started at: " + serverSocket);

            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server Client accepted from: " + socket);
                Client client = new Client(socket.getInputStream(), socket.getOutputStream(), chadChat);
                new Thread(client).start();


            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}






