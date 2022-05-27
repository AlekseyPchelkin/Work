package alex.exsample.work.ui.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import alex.exsample.work.R;
import alex.exsample.work.databinding.NoteItemBinding;
import alex.exsample.work.ui.lectures.Test;
import alex.exsample.work.ui.lectures.TestAdapter;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {
    ArrayList<Note> noteList = new ArrayList<Note>();
    Bundle bundle = new Bundle();

    public class NoteHolder extends RecyclerView.ViewHolder {
        private NoteItemBinding binding;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            binding = NoteItemBinding.bind(itemView);
        }

        void bind(Note note) {
            binding.textName.setText(note.name_note);
            binding.textText.setText(note.text_note);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController(view);
                    bundle.putInt("id", note.id);
                    navController.navigate(R.id.nav_notes, bundle);
                }
            });
        }
    }
    @NonNull
    @Override
    public NotesAdapter.NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Создание view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NotesAdapter.NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void addNote(Note note){
        noteList.add(note);
        notifyDataSetChanged();
    }

    public void clearNote() {
        noteList.clear();
    }
}
