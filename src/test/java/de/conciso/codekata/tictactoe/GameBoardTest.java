package de.conciso.codekata.tictactoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private GameBoard cut;


    @BeforeEach
    public void setUp() {
        cut = new GameBoard('A', 'C', (byte) 0, (byte) 2);
    }

    @Test
    public void testConstructor_withValid_Parameter() {
        cut = new GameBoard('A', 'C', (byte) 0, (byte) 2);
        assertThat(cut.getSize()).isEqualTo(new GameBoard.Size('A', (byte) 0, 'C',  (byte) 2));
    }

    @Test()
    public void testConstructor_withInvalid_X_Parameter() {
        assertThrows(IllegalArgumentException.class, () -> new GameBoard('C', 'A', (byte) 0, (byte) 2));
    }

    @Test()
    public void testConstructor_withInvalid_Y_Parameter() {
        assertThrows(IllegalArgumentException.class, () -> new GameBoard('A', 'C', (byte) 2, (byte) 0));
    }

    @Test
    public void testCan_set_figure_to_inbound_field() {
        Figure figure = new Figure('X');
        cut.setField('A', (byte) 1, figure);
        assertThat(cut.getField('A', (byte) 1)).isPresent();
        assertThat(cut.getField('A', (byte) 1).get()).isEqualTo(figure);
    }

    @Test
    public void testCan_set_figure_to_occupied_field_throws_exception() {
        Figure figure = new Figure('X');
        cut.setField('A', (byte) 1, figure);
        assertThrows(OccupiedException.class, () -> cut.setField('A', (byte) 1, figure));
    }

    @Test
    public void testCan_set_figure_to_outbound_x_field_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> cut.setField('D', (byte) 1, new Figure('X')));
    }

    @Test
    public void testCan_set_figure_to_outbound_y_field_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> cut.setField('A', (byte) 3, new Figure('X')));
    }

    @Test
    public void testCan_get_figure_from_outbound_x_field_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> cut.getField('D', (byte) 1));
    }

    @Test
    public void testCan_get_figure_from_outbound_y_field_throws_exception() {
        assertThrows(IllegalArgumentException.class, () -> cut.getField('A', (byte) 3));
    }

    @Test
    public void testBoard_is_Full_when_free_fields() {
        for (char startX = 'A'; startX <= 'C'; startX++)
            for (byte startY = (byte) 0; startY <= 2; startY++) {
                cut.setField(startX, startY, new Figure('X'));
            }
        assertThat(cut.isFull()).isTrue();
    }

    @Test
    public void testBoard_is_Full_when_all_fields_full() {
        for (char startX = 'A'; startX <= 'C'; startX++)
            for (byte startY = (byte) 0; startY <= 1; startY++) {
                cut.setField(startX, startY, new Figure('X'));
            }
        assertThat(cut.isFull()).isFalse();
    }

}