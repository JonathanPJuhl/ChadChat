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
        /*try {
            quiz.getCurrentGame().addPlayer(player);
            while (true) {
                out.print("> ");
                out.flush();
                try {
                    Command cmd = fetchCommand();
                    in.nextLine();
                    if (cmd == null) {
                        out.println("Thank you, " + player.getId() + ", next!");
                        out.flush();
                        return;
                    } else {
                        cmd.doIt(player);
                    }
                } catch (ParseException e) {
                    out.println("Invalid command: " + e.getMessage());
                }
            }
        } finally {
            quiz.getCurrentGame().removePlayer(player);
        }*/
    }

   /* private Player fetchPlayer() {
        out.print("What is your name? ");
        out.flush();
        String playerId = in.nextLine();
        return new Player(playerId);
    }

    private Command fetchCommand() throws ParseException {
        List<Callable<Command>> parsers =
                List.of(this::parseHelp,
                        this::parseDraw,
                        this::parseReset,
                        this::parseScore,
                        this::parsePlay,
                        this::parseQuit);
        for (Callable<Command> cmd : parsers) {
            try {
                return cmd.call();
            } catch (NoSuchElementException ignored) {
            } catch (ParseException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new ParseException("Could not match: " + in.nextLine(), 0);
    }

    public Command parseHelp() {
        in.next("h|help");
        return new HelpCommand();
    }

    public Command parseDraw() {
        in.next("d|draw");
        return new DrawCommand();
    }

    public Command parseScore() {
        in.next("s|score");
        return new ScoreCommand();
    }

    // public Command parseAnswer() throws ParseException {
    //     in.next("a|answer");
    //     try {
    //         String id = in.next("[abcdefABCDEF][12345]00").toLowerCase();
    //         int category = LETTER_LOOKUP.lastIndexOf(id.substring(0,1));
    //         int question = NUMBER_LOOKUP.lastIndexOf(id.substring(1,2));
    //     } catch (NoSuchElementException e) {
    //         e.printStackTrace();
    //         throw new ParseException("While parsing an answer, got: " + in.nextLine(), 0);
    //     }
    // }

    public Command parseReset() {
        in.next("r|reset");
        return new ResetCommand();
    }

    public Command parseQuit() {
        in.next("q|quit");
        return null;
    }

    public Command parsePlay() {
        in.next("p|play");
        return new PlayCommand();
    }*/
}