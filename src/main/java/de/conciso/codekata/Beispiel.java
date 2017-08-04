package de.conciso.codekata;

public class Beispiel
{
    private final Preference preference;

    public Beispiel(Preference p) {
        this.preference = p;
    }

    public int calculate() {
        return 1 + 1;
    }

    public String greeting() {
        return "hello " + preference.name();
    }

    public void greetAndCalculate(WriteSomewhere out) {
        out.write(preference.name() + " added " + calculate());
    }
}
