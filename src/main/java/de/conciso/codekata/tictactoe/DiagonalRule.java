package de.conciso.codekata.tictactoe;

import java.util.Optional;

public class DiagonalRule extends Rule{
    @Override
    protected Optional<Figure> getWinner(GameBoard board) {
        Optional<Figure> winnerFigure = Optional.empty();
        for(int i = 0; i < board.getSize().getXSize(); i++) {
            Optional<Figure> actualFigure = board.getField(i, i);
            if (i == 0) {
                winnerFigure = actualFigure;
            }
            if (!actualFigure.isPresent() || !actualFigure.equals(winnerFigure)) {
                winnerFigure = Optional.empty();
                break;
            }
        }
        if (!winnerFigure.isPresent()) {
            for (int i = 0; i < board.getSize().getXSize(); i++) {
                Optional<Figure> actualFigure = board.getField(board.getSize().getXSize() - i - 1, i);
                if (i == 0) {
                    winnerFigure = actualFigure;
                }
                if (!actualFigure.isPresent() || !actualFigure.equals(winnerFigure)) {
                    winnerFigure = Optional.empty();
                    break;
                }
            }
        }

        return winnerFigure;
    }
}
