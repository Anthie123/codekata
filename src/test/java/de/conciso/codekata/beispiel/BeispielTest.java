package de.conciso.codekata.beispiel;

import de.conciso.codekata.Beispiel;
import de.conciso.codekata.Preference;
import de.conciso.codekata.WriteSomewhere;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BeispielTest {
    private Beispiel cut;

    @Test
    public void test_calculate_returns_two() {
        cut = new Beispiel(new DummyPreference());

        assertEquals(2, cut.calculate());
    }

    @Test
    public void test_greeting_returns_hello_with_name() {
        cut = new Beispiel(new StubPreference("world"));

        assertEquals("hello world", cut.greeting());
    }

    @Test
    public void test_greeting_returns_hello_null_with_no_name() {
        cut = new Beispiel(new StubPreference(null));

        assertEquals("hello null", cut.greeting());
    }

    @Test
    public void test_greetAndCalculate_writes_name_adds_2() {
        cut = new Beispiel(new StubPreference("Test"));
        WriteSomewhereSpy spy = new WriteSomewhereSpy();

        cut.greetAndCalculate(spy);
        assertEquals("Test added 2", spy.getOutputString());
    }

    @Test
    public void test_greetAndCalculate_writes_name_adds_2_with_mock() {
        Preference mockPref = mock(Preference.class);
        when(mockPref.name()).thenReturn("Test");
        cut = new Beispiel(mockPref);
        WriteSomewhere mock = mock(WriteSomewhere.class);

        cut.greetAndCalculate(mock);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(mock).write(argument.capture());
        assertEquals("Test added 2", argument.getValue());
    }
    
}
