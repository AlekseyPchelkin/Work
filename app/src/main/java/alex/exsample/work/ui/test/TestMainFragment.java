package alex.exsample.work.ui.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.tabs.TabItem;


import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentTestMainBinding;
import alex.exsample.work.ui.lectures.Test;
import alex.exsample.work.ui.lectures.TestAdapter;
import alex.exsample.work.ui.lectures.Theme;

public class TestMainFragment extends Fragment {
    private FragmentTestMainBinding binding;
    private TestAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TabItem tabItemAll =  binding.ti1;
        TabItem tabItemDecided =  binding.ti2;
        TabItem tabItemSilence =  binding.ti3;
        adapter = new TestAdapter();
        init();
        tabItemAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tabItemDecided.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new TestAdapter();
            }
        });

        tabItemSilence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

       // adapter = new ThemeAdapter();
        return root;
    }

    void init(){
        binding.rcTest.setLayoutManager(new GridLayoutManager(this.getContext(), 1)); // количество тем в строке
        binding.rcTest.setAdapter(adapter);
        Test test;
        String item_title;
        int id_test;


    }
}
