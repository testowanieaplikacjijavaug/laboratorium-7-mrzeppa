import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NotesServiceImplTest {

    private NotesService notesService;

    private NotesStorage notesStorage;

    @BeforeEach
    public void setUp() {
        notesStorage = new NotesStorageMock();
        notesService = NotesServiceImpl.createWith(notesStorage);
    }

    @AfterEach
    public void tearDown() {
        notesService = null;
        notesStorage = null;
    }

    @Test
    public void givenValidRecordShouldBeAddedToList() {
        Note note = Note.of("a", 3.5f);

        notesService.add(note);

        assertThat(notesStorage.isCleared()).isFalse();
    }

    @Test
    public void shouldCalculateAverageOfNotes() {
        notesService.add(Note.of("test", 2.0f));
        notesService.add(Note.of("test", 6.0f));

        Float result = notesService.averageOf("name");

        assertThat(result).isEqualTo(4.0f);
    }

    @Test
    public void callingClearFunctionShouldClearMap() {
        notesService.clear();
        assertThat(notesStorage.isCleared()).isTrue();
    }

    @Test
    public void whenCalculatingAverageWithNullNameShouldRaiseAnException() {
        assertThatThrownBy(() -> {
            notesService.averageOf(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"\n\n\n   \n", "        ", "                      \r"} )
    public void whenCalculatingAverageWithEmptyNameShouldRaiseAnException(String name) {
        assertThatThrownBy(() -> {
            notesService.averageOf(name);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Imię ucznia nie może być puste");
    }



}