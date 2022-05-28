package alex.exsample.work.ui.test;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentTestResultsBinding;
import alex.exsample.work.db.DbQuestion;

public class TestResultFragment extends Fragment{
    private FragmentTestResultsBinding binding;
    DbQuestion question;
    Bundle bundle = new Bundle();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestResultsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        TextView textView = binding.tvresult;
        ProgressBar progressBar = binding.progressBar;
        Button bt_overraid = binding.override;
        Button bt_next = binding.next;
        int id_test = getArguments().getInt("id_test");
        init(id_test,textView, progressBar);

        bt_overraid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.nav_test_main);
            }
        });

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                bundle.putInt("id_test", id_test);
                navController.navigate(R.id.nav_test, bundle);
            }
        });

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void init(int id, TextView tv, ProgressBar progressBar){
        question = new DbQuestion("Question", getContext());
        int counter_right_answers=0,iter=0;
        int count_answers = question.getCountFieldID("right", "id_test", id);
        progressBar.setMax(count_answers);
        int arr[] = new int[count_answers];

        for (int i = 0; i < count_answers; ++i)
            arr[i] = question.getIdWhereId(id,"number_question", "id_test", i);

        while (iter != arr.length){
           if (Objects.equals(question.getCheck(arr[iter]), "true"))
                counter_right_answers++;
           iter++;
        }
        question = new DbQuestion("Tests", getContext());
        question.setNumberPoints(count_answers,id);
        question.setRightAnswer(counter_right_answers,id);
        tv.setText("Количество правильных ответов " + counter_right_answers + "/" + count_answers);
        progressBar.setProgress(counter_right_answers,true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
