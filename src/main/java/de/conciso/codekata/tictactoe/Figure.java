package de.conciso.codekata.tictactoe;

public class Figure {

    private final char symbol;

    public Figure(char x) {
        symbol = x;
    }

    public char getSymbol() {
        return symbol;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Figure figure = (Figure) o;

        return symbol == figure.symbol;
    }

    @Override
    public int hashCode() {
        return (int) symbol;
    }
}
