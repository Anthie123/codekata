package de.conciso.codekata.tictactoe;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static de.conciso.codekata.tictactoe.TicTacToe.COMMAND_ENDE;
import static de.conciso.codekata.tictactoe.TicTacToe.COMMAND_NEU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TicTacToeTest
{
    public static final String COMMAND_OTHER = "OTHER";
    private TicTacToe cut;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    public void setUp() {
        playerOne = mock(Player.class);
        playerTwo = mock(Player.class);

        cut = new TicTacToe(playerOne, playerTwo);
        GameTimer timer = mock(GameTimer.class);
        TicTacToe cutSpy = spy(cut);
        Mockito.doReturn(timer).when(cutSpy).getTimer();
    }

    @Test
    public void testGetRules_not_empty() {
        assertThat(cut.getRules()).isNotEmpty().allMatch(r -> r != null);
    }

    @Test
    public void testHasBoard_with_correct_size() {
        assertThat(cut.getBoard()).isNotNull();
        assertThat(cut.getBoard().getSize().getXSize()).isEqualTo((byte)3);
        assertThat(cut.getBoard().getSize().getYSize()).isEqualTo((byte)3);
    }

    @Test
    public void testHasPlayer() {
        assertThat(cut.getPlayer()).isNotEmpty().allMatch(p -> p != null);
    }

    @Test
    public void testWinner_if_a_rule_is_fullfilled() {
        GameBoard board = cut.getBoard();
        when(playerOne.getFigure()).thenReturn(new Figure('X'));
        Figure figure = cut.getPlayer()[0].getFigure();
        board.setField('A', (byte)0, figure);
        board.setField('B', (byte)0, figure);
        board.setField('C', (byte)0, figure);

        assertThat(cut.getWinner()).isPresent();
        assertThat(cut.getWinner().get()).isEqualTo(cut.getPlayer()[0]);
    }

    @Test
    public void testNo_winner_if_no_rule_is_fullfilled() {
        GameBoard board = cut.getBoard();
        assertThat(cut.getWinner().isPresent()).isFalse();
    }

    @Test
    public void testHandleNextInput_handles_ende() {

        assertThat(cut.handleNextCommand(COMMAND_ENDE)).isFalse() ;
    }

    @Test
    public void testHandleNextInput_handles_neu() {
        final GameBoard oldBoard = cut.getBoard();

        assertThat(cut.handleNextCommand(COMMAND_NEU)).isTrue();
        assertThat(oldBoard).isNotEqualTo(cut.getBoard());
    }
    @Test
    public void testHandleNextInput_delegates_input_to_playerOne_if_game_is_in_progress() {
        when(playerOne.isInteractive()).thenReturn(true);
        when(playerTwo.isInteractive()).thenReturn(true);

        cut.handleNextCommand(COMMAND_OTHER);
        cut.handleNextCommand(COMMAND_OTHER);
        cut.handleNextCommand(COMMAND_OTHER);
        verify(playerOne, times(2)).handleInput(cut.getBoard(),COMMAND_OTHER);
    }

    @Test
    public void testHandleNextInput_delegates_input_to_playerTwo_if_game_is_in_progress() {
        when(playerOne.isInteractive()).thenReturn(true);
        when(playerTwo.isInteractive()).thenReturn(true);

        cut.handleNextCommand(COMMAND_OTHER);
        cut.handleNextCommand(COMMAND_OTHER);
        cut.handleNextCommand(COMMAND_OTHER);
        cut.handleNextCommand(COMMAND_OTHER);
        verify(playerTwo, times(2)).handleInput(cut.getBoard(), COMMAND_OTHER);
    }

    @Test
    public void testHandleNextInput_ignores_player_if_board_is_full(){
        fillBoard(cut.getBoard());

        cut.handleNextCommand(COMMAND_OTHER);
        verify(playerOne, never()).handleInput(cut.getBoard(), COMMAND_OTHER);
        verify(playerTwo, never()).handleInput(cut.getBoard(), COMMAND_OTHER);
    }

    private void fillBoard(GameBoard board) {
        for (char startX = 'A'; startX <= 'C'; startX++)
            for (byte startY = (byte) 0; startY <= 2; startY++) {
                board.setField(startX, startY, new Figure('X'));
            }
    }

    @Test
    public void testHandleNextInput_delegates_input_from_playerone_to_playerone_if_playertwo_is_not_interactive() {
        when(playerOne.isInteractive()).thenReturn(true);
        when(playerTwo.isInteractive()).thenReturn(false);

        cut.handleNextCommand(COMMAND_OTHER);
        cut.handleNextCommand(COMMAND_OTHER);
        verify(playerOne, times(2)).handleInput(cut.getBoard(), COMMAND_OTHER);
    }

    @Test
    public void testHandleNextInput_delegates_input_from_playerone_to_playertwo_if_no_player_is_not_interactive() {
        when(playerOne.isInteractive()).thenReturn(false);
        when(playerTwo.isInteractive()).thenReturn(false);

        cut.handleNextCommand(COMMAND_OTHER);
        cut.handleNextCommand(COMMAND_OTHER);
        verify(playerOne, times(1)).handleInput(cut.getBoard(), COMMAND_OTHER);
        verify(playerTwo, times(1)).handleInput(cut.getBoard(), COMMAND_OTHER);
    }
    @Test
    public void testGetNextPlayer_delivers_first_player(){
        assertThat(cut.getActualPlayer()).isEqualTo(playerOne);
    }

    @Test
    public void testGetNextPlayer_delivers_second_player(){
        when(playerOne.isInteractive()).thenReturn(true);
        when(playerTwo.isInteractive()).thenReturn(true);

        cut.handleNextCommand(COMMAND_OTHER);

        assertThat(cut.getActualPlayer()).isEqualTo(playerTwo);
    }
}
