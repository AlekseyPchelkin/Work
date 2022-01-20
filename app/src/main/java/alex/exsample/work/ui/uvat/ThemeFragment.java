package alex.exsample.work.ui.uvat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentThemeBinding;

public class ThemeFragment extends Fragment {
    private FragmentThemeBinding binding;
    private ThemeAdapter adapter = new ThemeAdapter();
    int []arr = new int[]{R.drawable.engine};
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentThemeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        init();
        return root;
    }

    void init() {
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 2)); // количество тем в строке
        binding.rcView.setAdapter(adapter);
        binding.bt1.setOnClickListener(new View.OnClickListener() { // заменить на базу данных!
            @Override
            public void onClick(View v) {
                Theme theme = new Theme(arr[0],"test");
                adapter.addTheme(theme);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
//                Toast toast = Toast.makeText(getContext(), всплывающая подсказка
//                        "Пора покормить кота!",
//                        Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER, 0, 0);
//                toast.show(); //