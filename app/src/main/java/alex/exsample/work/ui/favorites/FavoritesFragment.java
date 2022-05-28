package alex.exsample.work.ui.favorites;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Objects;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentFavoritesBinding;;
import alex.exsample.work.db.DbQuestion;
import alex.exsample.work.ui.lectures.Theme;
import alex.exsample.work.ui.lectures.ThemeAdapter;

public class FavoritesFragment extends Fragment {
    private FragmentFavoritesBinding binding;
    private ThemeAdapter adapter;
    int [] picture_mass = new int[]{R.drawable.general, R.drawable.enginefeatures, R.drawable.exploitation, R.drawable.repair, R.drawable.oil, R.drawable.tires};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new ThemeAdapter();
        init();
        return root;
    }

    void init() {
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        binding.rcView.setAdapter(adapter);
        Theme theme;
        String item_title, check;
        DbQuestion question = new DbQuestion("Topic", getContext());


        for (int i = 0; i < question.getCountFieldPosition("title"); ++i) {
            check = question.getFavorite(i);
            if (Objects.equals(check, "true"))
            {
                item_title = question.getField("title",i);
                theme = new Theme(picture_mass[i],item_title); // возможен баг из-за разницы id в бд и массиве(0 в массиве  и 1 в бд), массивы я бы переделал на бд
                adapter.addTheme(theme);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
