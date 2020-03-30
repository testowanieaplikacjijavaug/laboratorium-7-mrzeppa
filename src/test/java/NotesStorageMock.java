import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;

public class NotesStorageMock implements NotesStorage {

    private Multimap<String, Float> noteList = ArrayListMultimap.create();

    private boolean isEmpty = true;

    @Override
    public void add(Note note) {
        isEmpty = false;
    }

    @Override
    public List<Note> getAllNotesOf(String name) {
        List<Note> result = new ArrayList<>();
        result.add(Note.of(name, 2.0f));
        result.add(Note.of(name, 6.0f));
        return result;
    }

    @Override
    public void clear() {
        isEmpty = true;
    }

    @Override
    public boolean isCleared() {
        return isEmpty;
    }
}