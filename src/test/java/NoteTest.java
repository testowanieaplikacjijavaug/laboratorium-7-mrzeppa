import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    @Test
    public void getNameTest() {
        Note note = Note.of("test", 3.0f);

        String result = note.getName();

        assertEquals("test", result);
    }

    @Test
    public void getNoteTest() {
        Note note = Note.of("test", 3.0f);

        Float result = note.getNote();

        assertEquals(3, result);
    }

    @Test
    public void nameCannotBeNullTest() {
        assertThatThrownBy(() -> {
            Note.of(null, 3.0f);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");
    }

    @Test
    public void nameShouldBeTrimmedTest() {
        Note note = Note.of("          \ntest            \t", 3.0f);

        String result = note.getName();

        assertEquals("test", result);
    }

    @Test
    public void whenGivenNameAfterTrimIsEmptyShouldRaiseAnException() {
        assertThatThrownBy(() -> {
            Note.of("             \n \t       \r", 3.0f);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");
    }

    @Test
    public void noteBelow2Test() {
        assertThatThrownBy(() -> {
            Note.of("test", 1.0f);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Niewłaściwa ocena");
    }

    @Test
    public void noteAbove6Test() {
        assertThatThrownBy(() -> {
            Note.of("test", 6.1f);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Niewłaściwa ocena");
    }


}