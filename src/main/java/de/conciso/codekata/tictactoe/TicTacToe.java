package de.conciso.codekata.tictactoe;

import java.util.Optional;

public class TicTacToe
{
    public static final String COMMAND_ENDE = "ende";
    public static final String COMMAND_NEU = "neu";
    private static final String NO_COMMAND = "nothing";

    private Rule[] rules;
    private GameBoard board;
    private Player[] player;
    private int actualPlayer;

    public TicTacToe(final Player one, final Player two) {
        createNewBoard();
        rules = new Rule[]{new RowRule(), new ColumnRule(), new DiagonalRule()};
        player = new Player[] {one, two};
    }

    private void createNewBoard() {
        board = new GameBoard('A', 'C', (byte)0, (byte)2);
    }

    public Rule[] getRules() {
        return rules;
    }

    public GameBoard getBoard() {
        return board;
    }

    public Optional<Player> getWinner() {
        for (int i = 0; i < rules.length; i++) {
            if (rules[i].gameOver(board)) {
                return rules[i].getWinner(board, player);
            }
        }
        return Optional.empty();
    }

    public Player[] getPlayer() {
        return player;
    }


    public boolean handleNextCommand(String command) {
        if (COMMAND_ENDE.equals(command)) {
            return false;
        }

        if (COMMAND_NEU.equals(command)) {
            createNewBoard();
        } else if (!board.isFull()){
            player[actualPlayer].handleInput(getBoard(), command);
            nextPlayer();
        }
        return true;
    }

    private void nextPlayer() {
        int count = 0;
        do {
            actualPlayer = (actualPlayer + 1) % player.length;
        } while(!player[actualPlayer].isInteractive() && (count++ < player.length));
    }

    public static void main(String[] args )
    {
        GamePresentation presentation = new GamePresentation();
        Player playerOne = new Player("Player 1", 'X');
        Player playerTwo = new Player("Player 2", 'O');
        TicTacToe game = new TicTacToe(playerOne, playerTwo);

        String command;
        do {
            presentation.showBoard(game.getBoard());
            presentation.showPrompt(game.getActualPlayer());
            if (presentation.hasNextCommand()) {
                command = presentation.getNextCommand();
            } else {
                command = NO_COMMAND;
            }
            game.getTimer().waitFor(1000);

        }while( game.handleNextCommand(command));
    }

    public GameTimer getTimer() {
        return new GameTimer();
    }

    public Player getActualPlayer() {
        return player[actualPlayer];
    }
}
