package alex.exsample.work.ui.lectures;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import alex.exsample.work.R;
//import alex.exsample.work.db.DbAccessHelper;
import alex.exsample.work.databinding.FragmentThemeBinding;
import alex.exsample.work.db.DbHelper;

public class ThemeFragment extends Fragment {
    SQLiteDatabase db;
    Cursor userCursor;
    DbHelper dataHelper;
    private FragmentThemeBinding binding;
    private ThemeAdapter adapter = new ThemeAdapter();
    int [] picture_mass = new int[]{R.drawable.engine};
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentThemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        init();
        return root;
    }

    @SuppressLint("Range")
    void init() {
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 2)); // количество тем в строке
        binding.rcView.setAdapter(adapter);

        Theme theme;
        dataHelper = new DbHelper(getContext());
        dataHelper.create_db();
        db = dataHelper.open();
        userCursor = db.rawQuery("select * from '" + "subject" + "'", null);
        int count = userCursor.getColumnCount();
        userCursor = db.rawQuery("SELECT title from '" +  "subject" + "'",null);
        String item_title;
        for (int i = 0; i < count-1; ++i ) { // вывод из базы данных, не совсем корректно, лучше реализовать отдельным классом для этого
            userCursor.moveToPosition(i);
            item_title = userCursor.getString(userCursor.getColumnIndex("title"));
            theme = new Theme(picture_mass[0],item_title);
            adapter.addTheme(theme);
        }
        userCursor.close();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}