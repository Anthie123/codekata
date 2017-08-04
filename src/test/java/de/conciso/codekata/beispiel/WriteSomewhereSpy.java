package de.conciso.codekata.beispiel;

import de.conciso.codekata.WriteSomewhere;

public class WriteSomewhereSpy implements WriteSomewhere {
    private String outputString;

    public String getOutputString() {
        return outputString;
    }

    public void write(String s) {
        outputString = s;
    }
}
