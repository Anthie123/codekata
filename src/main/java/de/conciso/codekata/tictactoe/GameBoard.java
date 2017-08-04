package de.conciso.codekata.tictactoe;

import java.util.Optional;

public class GameBoard {
    final Figure[][] board;
    private final Size size;

    public GameBoard(final char startChar, final char endChar, final byte startNumber, final byte endNumber) {
        try {
            size = new Size(startChar, startNumber, endChar, endNumber);
            board = new Figure[size.getXSize()][size.getYSize()];
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Size getSize() {
        return size;
    }

    public void setField(final char xPosition, final byte yPosition, final Figure figure) {
        setField(xPosition - getSize().getXStart(), yPosition - getSize().getYStart(), figure);
    }

    public Optional<Figure> getField(int x, int y) {
        try {
            return Optional.ofNullable(board[x][y]);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<Figure> getField(char xPosition, byte yPosition) {
        return getField(xPosition - getSize().getXStart(),yPosition - getSize().getYStart());
    }

    public boolean isFull() {
        for(int i = 0; i < getSize().getXSize(); i++)
            for(int j = 0; j < getSize().getYSize(); j++ ) {
            if (board[i][j] == null) {
                return false;
            }
        }
        return true;
    }

    public void setField(final int x, final int y, final Figure figure) {
        try {
            if (board[x][y] == null) {
                board[x][y] = figure;
            } else {
                throw new OccupiedException("Field is already occupied");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public static class Size {
        private final char xStart;
        private final byte yStart;
        private final char xEnd;
        private final byte yEnd;

        public Size(char firstX, byte firstY, char lastX, byte lastY) {
            xStart = firstX;
            xEnd = lastX;
            yStart = firstY;
            yEnd = lastY;
        }
        public byte getXSize() {
            return (byte)(xEnd - xStart  + 1);
        }

        public byte getYSize() {
            return (byte) (yEnd - yStart + 1);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Size size = (Size) o;

            if (getXSize() != size.getXSize()) return false;
            return getYSize() == size.getYSize();
        }

        @Override
        public int hashCode() {
            int result = (int) getXSize();
            result = 31 * result + (int) getYSize();
            return result;
        }

        public char getXStart() {
            return xStart;
        }

        public byte getYStart() {
            return yStart;
        }
    }
}
