package chadchat.domain;

import java.util.Set;

/*public class Session implements Runnable {
    private final Set<User> activeMessengers;
    //private final Thread thread;

    public Session(Set<User> activeMessengers) {
        //this.thread = new Thread(this);
        this.activeMessengers = activeMessengers;
    }

    @Override
    public void run() {
        try {
            //make this a choose receiver-method
            chooseRoundPlayer();
            Game.ActiveQuestion aq = waitForActiveQuestion();
            Thread.sleep(30000);
            endGame(aq.timeoutQuestion());
        } catch (InterruptedException e) { }

    }
}*/
