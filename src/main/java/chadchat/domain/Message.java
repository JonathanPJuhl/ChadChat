package chadchat.domain;

public class Message {
    private final String message;
    private final String username;

    public Message(String username, String message) {
        this.message = message;
        this.username = username;
    }

    @Override
    public String toString() {
        return username+"\nsays: " +
                message;
    }
}

