package de.conciso.codekata.beispiel;

import de.conciso.codekata.Preference;

public class StubPreference implements Preference {
    private String name;

    public StubPreference(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
