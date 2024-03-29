package alex.exsample.work.ui.lectures;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentThemeBinding;
import alex.exsample.work.db.DbQuestion;

public class ThemeFragment extends Fragment {
    private FragmentThemeBinding binding;
    private ThemeAdapter adapter;
    int [] picture_mass = new int[]{R.drawable.device, R.drawable.exploitation,R.drawable.repair}; // менять на базу данных
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentThemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new ThemeAdapter();
        init();
        return root;
    }

    void init() {
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 1)); // количество тем в строке
        binding.rcView.setAdapter(adapter);
        Theme theme;
        String item_title;
        DbQuestion question = new DbQuestion("Themes", getContext());
        for (int i = 0; i < question.getCountFieldPosition("title"); ++i ) {
            item_title = question.getField("title",i);
            theme = new Theme(picture_mass[i], item_title);
            adapter.addTheme(theme);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}