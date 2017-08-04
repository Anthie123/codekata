package de.conciso.codekata.tictactoe;


import java.util.Arrays;
import java.util.Optional;

public abstract class Rule {

    public boolean gameOver(final GameBoard board) {
        return getWinner(board).isPresent();
    }

    protected abstract Optional<Figure> getWinner(GameBoard board);

    public Optional<Player> getWinner(final GameBoard board, final Player[] player) {
        Optional<Figure> winnerFigure = getWinner(board);
        return Arrays.stream(player)
                .filter(p -> winnerFigure.map(f -> f.equals(p.getFigure())).orElse(false))
                .findFirst();
    }
}
