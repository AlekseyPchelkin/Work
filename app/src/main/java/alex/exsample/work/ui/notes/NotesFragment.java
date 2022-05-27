package alex.exsample.work.ui.notes;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentNotesMenuBinding;
import alex.exsample.work.db.DbQuestion;

public class NotesFragment extends Fragment {
    private FragmentNotesMenuBinding binding;
    private NotesAdapter adapter;
    DbQuestion question;
    int id = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        menu.findItem(R.id.action_note_save).setVisible(true);
        menu.findItem(R.id.action_note_delit).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_note_save:
                String name_notes = binding.editTextNoteName.getText().toString();
                String text_notes = binding.editTextTextMultiLine2.getText().toString();
                question.setNote(text_notes, name_notes, id);
                initRes();
                return true;

            case R.id.action_note_delit:
                question.deliteField(id, "id_notes");
                initRes();
                return true;
        }
        return true;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotesMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new NotesAdapter();
        question = new DbQuestion("Notes", getContext());
        FloatingActionButton actionButtonadd = binding.floatingActionButtonAddNote;

        actionButtonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = question.getCountTablePosition();
                question.insertNote(count);
                initText(count);
            }
        });

        try {
            id = getArguments().getInt("id");
        } catch (Exception e) {
            id = 0;
        }
        initRes();
        initText(id);
        return root;
    }

    void initRes() {
        adapter.clearNote();
        binding.rcNote.setLayoutManager(new GridLayoutManager(this.getContext(), 1)); // количество тем в строке
        binding.rcNote.setAdapter(adapter);
        String name_note, text;
        Note note;
        int count = question.getCountTablePosition();
        int[] notes_id_mass = new int[count];
        for (int i = 0; i < count; ++i)
            notes_id_mass[i] = question.getIdPosition("id_notes", i);
        for (int i = 0; i < count; ++i) {
            question.setNoteId(notes_id_mass[i], i);
            name_note = question.getField("name_notes", i);
            text = question.getField("text_notes", i);
            note = new Note(i, name_note, text);
            adapter.addNote(note);
        }
    }

    void initText(int id) {
        String text_name = question.getField("name_notes", id);
        String text = question.getField("text_notes", id);
        binding.editTextNoteName.setText(text_name);
        binding.editTextTextMultiLine2.setText(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
