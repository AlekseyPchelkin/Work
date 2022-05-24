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

import alex.exsample.work.R;
import alex.exsample.work.databinding.TestItemBinding;
import alex.exsample.work.db.DbQuestion;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    ArrayList<Test> testList = new ArrayList<Test>();
    Bundle bundle = new Bundle();

    public class TestHolder extends RecyclerView.ViewHolder {
        private TestItemBinding binding;
        public TestHolder(@NonNull View itemView) {
            super(itemView);
            binding = TestItemBinding.bind(itemView);
        }

        void bind(Test test){
            binding.tvTitle.setText(test.test_description);
            binding.textView2.setText("Тест №" + Integer.toString(test.id));
            binding.progressBar2.setProgress(90);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController(view);
                    bundle.putInt("id_test", test.id);
                    navController.navigate(R.id.nav_test, bundle);
                }
            });
        }
    }

    @NonNull
    @Override
    public TestAdapter.TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Создание view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        return new TestAdapter.TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.TestHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(testList.get(position));
    }

    @Override
    public int getItemCount() {
        return testList .size();
    }

    void addTest(Test test){
        testList.add(test);
        notifyDataSetChanged();
    }

    void clearTheme() {
        testList .clear();
    }
}
