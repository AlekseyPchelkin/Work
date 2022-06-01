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
        question = new DbQuestion("Question", getContext());
        if (Objects.equals(question.getIdFieldid("written_question", id_quest[number], "number_question", 0), "true"))
           editText.setVisibility(View.VISIBLE);

        return root;
    }

    void initForm() {
        adapter.clearQuestion();
        binding.rcView.setLayoutManager(new GridLayoutManager(this.getContext(), 1));
        binding.rcView.setAdapter(adapter);
        id_test = getArguments().getInt("id_test");
        question = new DbQuestion("Question", getContext());
        count = question.getCountFieldID("question_text", "id_test", id_test);

        id_quest = new int[count];
        for (int i = 0; i < count; ++i)
            id_quest[i] = question.getIdWhereId(id_test, "number_question", "id_test", i);

        String quest = question.getIdFieldid("question_text", id_quest[number], "number_question", 0);

        if (Objects.equals(question.getIdFieldid("written_question", id_quest[number], "number_question", 0), "true"))
            wrong_answer = false;

        int answer_count;
        question = new DbQuestion("Answers", getContext());
        answer_count = question.getCountFieldID("answer", "number_question", number);
        String[] answers = new String[answer_count];

        for (int i = 0; i < answer_count; i++)
            answers[i] = question.getIdFieldid("answer", id_quest[number], "number_question", i);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Вопрос " + number); // вывод названия темы в toolbar
        initAnswers(answers, wrong_answer, quest);
    }

    void chekAnswer(String text, boolean written_question){
        if (!written_question) {
            if (Objects.equals(question.getIdFieldid("correct_answer", id_quest[number - 1], "number_question", 0), text)) {
                question.setResulTest(2, id_quest[number - 1]);
            } else {
                question.setResulTest(2, id_quest[number - 1]);
            }
        }
    }

    void initAnswers(String[] answers, boolean wrong_answer, String quest){
        Question answer;
        try {
            shuffleArray(answers);
            answer = new Question(quest, number);
            adapter.addQuestion(answer);
            if (wrong_answer) {
                for (int i = 0; i < answers.length; ++i) {
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