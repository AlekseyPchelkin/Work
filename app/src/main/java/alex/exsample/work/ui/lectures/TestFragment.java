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
import alex.exsample.work.databinding.FragmentTestMenuBinding;
import alex.exsample.work.db.DbQuestion;

public class TestFragment extends Fragment {
    private FragmentTestMenuBinding binding;
    private TestAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new TestAdapter();
        int id_theme = getArguments().getInt("id_theme");
        init(id_theme);
        return root;
    }

    void init(int id_theme) {
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 1)); // количество тем в строке
        binding.rcView.setAdapter(adapter);
        Test test;
        String item_title;
        int id_test;
        DbQuestion question = new DbQuestion("Tests", getContext());
        for (int i = 0; i < question.getCountFieldID("test_description","id", id_theme); ++i){
            item_title = question.getIdField("test_description", id_theme ,i);
            id_test = question.getIdWhereTitle(item_title, "id_test", "test_description");
            question = new DbQuestion("Tests",getContext());

            int count_points = question.getIdWhereId(id_test,"number_points", "id_test", 0);
            int right_answers = question.getIdWhereId(id_test,"right_answers", "id_test", 0);
            test = new Test(item_title, id_test, count_points,right_answers);
            adapter.addTest(test);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
