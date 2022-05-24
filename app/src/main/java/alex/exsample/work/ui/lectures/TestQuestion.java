package alex.exsample.work.ui.lectures;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.Objects;
import java.util.Random;

import alex.exsample.work.databinding.FragmentPdfReaderBinding;
import alex.exsample.work.databinding.FragmentTestBinding;
import alex.exsample.work.db.DbQuestion;

public class TestQuestion extends Fragment {
    private FragmentTestBinding binding;
    int number = 0;
    DbQuestion question;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button button = binding.button;
        TextView tv01 = binding.tv01;
        TextView tv02 = binding.tv02;
        TextView tv03 = binding.tv03;
        TextView tv04 = binding.tv04;
        question = new DbQuestion("Question", getContext());
        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setText("");
                number++;
                init();
            }
        });

        tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals((String) tv01.getText(), question.getField("correct_answer", number)))
                    button.setText("Правильно");
            }
        });

        tv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals((String) tv02.getText(), question.getField("correct_answer", number)))
                    button.setText("Правильно");
            }
        });

        tv03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals((String) tv03.getText(), question.getField("correct_answer", number)))
                    button.setText("Правильно");
            }
        });

        tv04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals((String) tv04.getText(), question.getField("correct_answer", number)))
                    button.setText("Правильно");
            }
        });
        return root;
    }

    void init() {
        String quest = question.getField("question_text", number);
        int id = getArguments().getInt("id_test");
        int count = question.getCountFieldID("question_text", "id_test",id);
        String[] answers = new String[count];

        for (int i = 1; i < 4; i++)
            answers[i] = question.getField("wrong_answer" + Integer.toString(i), number);
        answers[4] = question.getField("correct_answer", number);
        shuffleArray(answers);

        binding.tvQuestion.setText(quest);
        binding.tv01.setText(answers[1]);
        binding.tv02.setText(answers[2]);
        binding.tv03.setText(answers[3]);
        binding.tv04.setText(answers[4]);
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
