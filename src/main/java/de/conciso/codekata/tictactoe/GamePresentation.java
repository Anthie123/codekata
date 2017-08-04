package de.conciso.codekata.tictactoe;

public class GamePresentation {
    private String command;

    public void showBoard(GameBoard board) {
        StringBuilder out  = new StringBuilder();
        for (int y = -1; y < board.getSize().getYSize(); y++) {
            for (int x = -1; x < board.getSize().getXSize(); x++ ) {
                if (x < 0) {
                    if (y < 0) {
                        out.append(' ');
                    } else {
                        out.append(board.getSize().getYStart() + y);
                    }
                } else {
                    if (y < 0) {
                        out.append((char)(board.getSize().getXStart() + x));
                    } else {
                        out.append(board.getField(x,y).orElse(new Figure((' '))).getSymbol());
                    }
                }
            }
            System.out.println(out.toString());
            out.setLength(0);
        }
    }

    public void showPrompt(Player actualPlayer) {
    }

    public boolean hasNextCommand() {
        return false;
    }

    public String getNextCommand() {
        return command;
    }
}
