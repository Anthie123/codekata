package de.conciso.codekata.tictactoe;

public class GameTimer {
    public void waitFor(final long timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            // ignore 
        }
    }
}
