package alex.exsample.work.ui.lectures;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import alex.exsample.work.R;
import alex.exsample.work.databinding.QuestionsItemBinding;
import alex.exsample.work.db.DbQuestion;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {
    ArrayList<Question> questionsList = new ArrayList<Question>();
    DbQuestion question;

    public class QuestionHolder extends RecyclerView.ViewHolder {
        private QuestionsItemBinding binding;
        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            binding = QuestionsItemBinding.bind(itemView);
        }

        void bind(Question quest){
            binding.textView3.setText(quest.answer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    question = new DbQuestion("Answers", itemView.getContext());
                    int point=0;
                    if (Objects.equals((String) question.getIdWhereId(binding.textView3.getText().toString(),"right","answer", 0), "true")) {
                        question = new DbQuestion("Question", itemView.getContext());
                        point = question.getIdWhereId(quest.id,"points","number_question",0);
                        question.setResulTest(point+1, quest.id);
                        binding.textView3.setBackgroundResource(R.color.green);
                    }
                    else {
                        question.setResulTest(0, quest.id);
                        binding.textView3.setBackgroundResource(R.color.rad);
                        //binding.textView3.setText("не правильно " + quest.id);
                   }
                }
            });
        }
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Создание view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_item, parent, false);
        return new QuestionAdapter.QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.QuestionHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(questionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    void addQuestion(Question quest){
        questionsList.add(quest);
        notifyDataSetChanged();
    }

    void clearQuestion() {
        questionsList.clear();
    }
}
