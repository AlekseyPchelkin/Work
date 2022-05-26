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
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentTestMainBinding;
import alex.exsample.work.db.DbQuestion;
import alex.exsample.work.ui.lectures.Test;
import alex.exsample.work.ui.lectures.TestAdapter;
import alex.exsample.work.ui.lectures.Theme;

public class TestMainFragment extends Fragment {
    private FragmentTestMainBinding binding;
    private TestAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TabLayout tabLayout = binding.tabLayout;
      //  tabLayout.addTab(tabLayout.newTab().setText("Все тесты"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                initAll(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        adapter = new TestAdapter();
        initAll(0);
        return root;
    }

    void initAll(int position){
        binding.rcTest.setLayoutManager(new GridLayoutManager(this.getContext(), 1)); // количество тем в строке
        binding.rcTest.setAdapter(adapter);
        adapter.clearTest();
        Test test;
        String item_title;
        int id_test;
        DbQuestion question = new DbQuestion("Tests", getContext());
        for (int i = 0; i < question.getCountTablePosition(); ++i){
            item_title = question.getField("test_description", i);
            id_test = question.getIdWhereTitle(item_title, "id_test", "test_description");
            int count_points = question.getIdWhereId(id_test,"number_points", "id_test", 0);
            int right_answers = question.getIdWhereId(id_test,"right_answers", "id_test", 0);
            test = new Test(item_title, id_test, count_points,right_answers);
            switch (position){
                case 0:
                    adapter.addTest(test);
                    break;
                case 1:
                    if (right_answers==0) adapter.addTest(test);
                    break;
                case 2:
                    if (right_answers!=0) adapter.addTest(test);
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
