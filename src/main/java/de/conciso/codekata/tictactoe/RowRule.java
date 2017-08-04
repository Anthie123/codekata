package de.conciso.codekata.tictactoe;

import java.util.Optional;

public class RowRule extends Rule {
    protected Optional<Figure> getWinner(final GameBoard board) {
        Optional<Figure> winnerFigure = Optional.empty();
        for (int y = 0; y < board.getSize().getYSize(); y++) {
            for (int x = 0; x < board.getSize().getXSize(); x++) {
                Optional<Figure> actualFigure = board.getField(x, y);
                if (x == 0) {
                    winnerFigure = actualFigure;
                }
                if (!actualFigure.isPresent() || !actualFigure.equals(winnerFigure)) {
                    winnerFigure = Optional.empty();
                    break;
                }
            }
            if (winnerFigure.isPresent()) {
                break;
            }
        }
        return winnerFigure;
    }
}
