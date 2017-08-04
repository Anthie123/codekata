package de.conciso.codekata.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ColumnRuleTest {
    private ColumnRule cut;

    @BeforeEach
    void setUp() {
        cut = new ColumnRule();
    }

    @Test
    void testGameOver_finds_first_column() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        Figure figure = new Figure('X');
        board.setField('A', (byte)0, figure);
        board.setField('A', (byte)1, figure);
        board.setField('A', (byte)2, figure);

        assertThat(cut.gameOver(board)).isTrue();
    }

    @Test
    void testGameOver_finds_last_column() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        Figure figure = new Figure('X');
        board.setField('C', (byte)0, figure);
        board.setField('C', (byte)1, figure);
        board.setField('C', (byte)2, figure);

        assertThat(cut.gameOver(board)).isTrue();
    }

    @Test
    void testGameOver_on_no_row() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        Figure figure = new Figure('X');
        board.setField('A', (byte)0, figure);
        board.setField('A', (byte)2, figure);

        assertThat(cut.gameOver(board)).isFalse();
    }

    @Test
    void testGetWinner_on_gameOver() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        Player[] player = new Player[]{new Player("One", 'X'), new Player("Two", 'O')};

        board.setField('A', (byte)0, player[0].getFigure());
        board.setField('A', (byte)1, player[0].getFigure());
        board.setField('A', (byte)2, player[0].getFigure());

        assertThat(cut.getWinner(board, player)).isPresent();
        assertThat(cut.getWinner(board, player).get()).isEqualTo(player[0]);
    }

    @Test
    void testGetWinner_on_game_not_over() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        Player[] player = new Player[]{new Player("One", 'X'), new Player("Two", 'O')};

        board.setField('C', (byte)0, player[0].getFigure());
        board.setField('C', (byte)2, player[0].getFigure());

        assertThat(cut.getWinner(board, player).isPresent()).isFalse();
    }

}