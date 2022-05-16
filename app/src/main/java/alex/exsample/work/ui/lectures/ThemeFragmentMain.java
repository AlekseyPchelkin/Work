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
import alex.exsample.work.db.DbHelper;

public class ThemeFragmentMain extends Fragment implements ThemeAdapter.Lestener {
   // String title;
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
        String t = getTitle();
        Log.d("MyLog1", t );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String getTitle() {
        ThemeAdapter adapter = new ThemeAdapter();
        String title = adapter.Title();
        return title;
    }
}