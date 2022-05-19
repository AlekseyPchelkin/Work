package alex.exsample.work.ui.lectures;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentThemeBinding;
import alex.exsample.work.db.DbHelper;
import alex.exsample.work.db.DbQuestion;

public class ThemeFragmentMain extends Fragment {
    private FragmentThemeBinding binding;
    private ThemeAdapter adapter = new ThemeAdapter();
    DbQuestion question;
    int [] picture_mass = new int[]{R.drawable.engine};


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentThemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        String titleTheme = getArguments().getString("title");
        question = new DbQuestion("Themes", getContext());
        int id = question.getIdWhereTitle(titleTheme);
        init(id);
        return root;
    }

    void init(int id) {
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 2)); // количество тем в строке
        binding.rcView.setAdapter(adapter);
        Theme theme;
        String item_title;
        question = new DbQuestion("Topic", getContext());
        for (int i = 0; i < question.getCountFieldID("title",id); ++i) {
           item_title = question.getIdField("title", id, i);
           theme = new Theme(picture_mass[0],item_title);
           adapter.addTheme(theme);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}