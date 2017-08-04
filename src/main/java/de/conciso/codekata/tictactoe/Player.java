package de.conciso.codekata.tictactoe;


public class Player {
    private final Figure figure;
    private final String name;

    public Player(String name, char symbol) {
        this.name = name;
        figure = new Figure(symbol);
    }

    public Figure getFigure() {
        return figure;
    }

    public void handleInput(GameBoard board, String commandOther) {
    }

    public boolean isInteractive() {
        return true;
    }
}
