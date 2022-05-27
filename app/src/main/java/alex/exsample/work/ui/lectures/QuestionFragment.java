package alex.exsample.work.ui.lectures;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Objects;
import java.util.Random;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentQuestionsBinding;
import alex.exsample.work.db.DbQuestion;

public class QuestionFragment extends Fragment {
    private FragmentQuestionsBinding binding;
    private QuestionAdapter adapter;
    int number = 0;
    int count = 0;
    int id_test;
    private DbQuestion question;
    Bundle bundle = new Bundle();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new QuestionAdapter();
        Button button = binding.bt1;
        question = new DbQuestion("Question", getContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number++;
                NavController navController = Navigation.findNavController(view);
                if (number!=count)
                    init();

                if (number == count-1)
                    button.setText("Завершить");

                if (number == count){
                    bundle.putInt("id_test",id_test);
                    navController.navigate(R.id.nav_test_result, bundle);
                }
            }
        });

        init();
        return root;
    }

    void init() {
        adapter.clearQuestion();
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 1)); // количество тем в строке
        binding.rcView.setAdapter(adapter);
        String quest = question.getField("question_text", number);
        id_test = getArguments().getInt("id_test");
        count = question.getCountFieldID("question_text", "id_test",id_test);


        String[] answers = new String[count];
        Question answer;

        for (int i = 1; i < count-1; i++)
           answers[i] = question.getField("wrong_answer" + i, number);

        try {
        answers[count-1] = question.getField("correct_answer", number);
        shuffleArray(answers);

        answer = new Question(quest, number);
        adapter.addQuestion(answer);
        for (int i = 1; i < count; ++i){
            quest = answers[i];
            answer = new Question(quest, number);
            adapter.addQuestion(answer);
        }
        } catch (Exception e){}
    }

    public static void shuffleArray(String[] arr) {
        int n = arr.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 1; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(arr, i, change);
        }
    }

    private static void swap(String[] a, int i, int change) {
        String temp = a[i];
        a[i] = a[change];
        a[change] = temp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}