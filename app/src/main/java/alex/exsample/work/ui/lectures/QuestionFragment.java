package alex.exsample.work.ui.lectures;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    int id_test, number = 0, count = 0;
    private DbQuestion question;
    Bundle bundle = new Bundle();
    int [] id_quest;
    boolean wrong_answer = true;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuestionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new QuestionAdapter();
        Button button = binding.bt1;
        EditText editText = binding.etAnswer;
        editText.setVisibility(View.GONE);
        question = new DbQuestion("Question", getContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number++;
                NavController navController = Navigation.findNavController(view);
                if (number!=count){
                    initForm();
                    chekAnswer(editText.getText().toString(), wrong_answer);
                }

                if (number == count-1)
                    button.setText("Завершить");

                if (number == count){
                    chekAnswer(editText.getText().toString(), wrong_answer);
                    bundle.putInt("id_test",id_test);
                    navController.navigate(R.id.nav_test_result, bundle);
                }
            }
        });

        initForm();
        if (Objects.equals(question.getIdFieldid("written_question", id_quest[number], "number_question", 0), "true"))
            editText.setVisibility(View.VISIBLE);

        return root;
    }

    void initForm() {
        adapter.clearQuestion();
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 1));
        binding.rcView.setAdapter(adapter);
        id_test = getArguments().getInt("id_test");
        count = question.getCountFieldID("question_text", "id_test",id_test);

        String[] answers = new String[count];
        id_quest = new int[count];

        for (int i = 0; i < count; ++i)
            id_quest[i] = question.getIdWhereId(id_test, "number_question", "id_test", i);

        for (int i = 1; i < count-1; i++)
            answers[i] = question.getIdFieldid("wrong_answer" + i, id_quest[number], "number_question", 0);

        if (Objects.equals(question.getIdFieldid("written_question", id_quest[number], "number_question", 0), "true"))
            wrong_answer = false;

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Вопрос " + number); // вывод названия темы в toolbar
        initAnswers(answers, wrong_answer);
    }

    void chekAnswer(String text, boolean written_question){
        if (!written_question) {
            if (Objects.equals(question.getIdFieldid("correct_answer", id_quest[number - 1], "number_question", 0), text)) {
                question.setResulTest("true", id_quest[number - 1]);
            } else {
                question.setResulTest("false", id_quest[number - 1]);
            }
        }
    }

    void initAnswers(String[] answers, boolean wrong_answer){
        Question answer;
        String quest = question.getIdFieldid("question_text", id_quest[number], "number_question", 0);
        try {
            answers[count-1] = question.getIdFieldid("correct_answer", id_quest[number], "number_question", 0);
            shuffleArray(answers);

            answer = new Question(quest, number);
            adapter.addQuestion(answer);
            if (wrong_answer) {
                for (int i = 1; i < count; ++i) {
                    quest = answers[i];
                    answer = new Question(quest, number);
                    adapter.addQuestion(answer);
                }
            }
        } catch (Exception ignored){}
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