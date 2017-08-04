package de.conciso.codekata.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GamePresentationTest {
    private GamePresentation cut;
    private ByteArrayOutputStream outStream;

    @BeforeEach
    public void setUp() {
        cut = new GamePresentation();
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    @Test
    public void testShowBoard_shows_empty_Gameboard() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        cut.showBoard(board);
        assertThat(outStream.toString()).isEqualTo(" ABC" + System.lineSeparator()
            + "0   " + System.lineSeparator()
            + "1   " + System.lineSeparator()
            + "2   " + System.lineSeparator());

    }

    @Test
    public void testShowBoard_shows_full_Gameboard() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        Figure figure = new Figure('X');
        for (int x = 0; x < 'C' - 'A' + 1; x++)
            for (int y = 0; y < 2 - 0 + 1; y++) {
                board.setField(x,y, figure);
            }

        cut.showBoard(board);
        assertThat(outStream.toString())
                .isEqualTo(" ABC" + System.lineSeparator()
                + "0XXX" + System.lineSeparator()
                + "1XXX" + System.lineSeparator()
                + "2XXX" + System.lineSeparator());

    }

    @Test
    public void testShowBoard_shows_one_field_occupied_Gameboard() {
        GameBoard board = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        Figure figure = new Figure('X');
        board.setField('C',  (byte) 1, figure);

        cut.showBoard(board);
        assertThat(outStream.toString())
                .isEqualTo(" ABC" + System.lineSeparator()
                        + "0   " + System.lineSeparator()
                        + "1  X" + System.lineSeparator()
                        + "2   " + System.lineSeparator());

    }

}