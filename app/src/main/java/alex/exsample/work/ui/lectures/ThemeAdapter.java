package alex.exsample.work.ui.lectures;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import alex.exsample.work.R;
import alex.exsample.work.databinding.ThemeItemBinding;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder> {
    ArrayList<Theme> themeList = new ArrayList<Theme>();
    String theme_title;
    String []title_name = new String[] {"sewr"};//!!!

    public class ThemeHolder extends RecyclerView.ViewHolder {
        private ThemeItemBinding binding;
        public ThemeHolder(@NonNull View itemView) {
            super(itemView);
            binding = ThemeItemBinding.bind(itemView);
        }

        void bind(Theme theme){
            binding.iv2.setImageResource(theme.imageId);
            binding.tvTitle.setText(theme.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    theme_title = String.valueOf(theme.title);
                    for (int i = 0; i < title_name.length; ++i) {
                        if(Objects.equals(theme_title, title_name[i])){
                            Log.d("MyLog1", "лох");
                            Navigation.findNavController(view).navigate(R.id.nav_MainTheme);
                            break;
                        }
                    }
                    Log.d("MyLog1", String.valueOf(theme.title));
                }
            });
        }
    }

    @NonNull
    @Override
    public ThemeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Создание view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_item, parent, false);
        return new ThemeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bind(themeList.get(position));
      //  holder.itemView.setOnClickListener (new View.OnClickListener() { // нажатие на rcView рабочий метод, но не совсем корректный
      //      @Override
      //      public void onClick(View v) {
      //          Log.d("MyLog", String.valueOf(position));
      //      }
      //  });
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    String Title() {
        return theme_title;
    }

    void addTheme(Theme theme){
        themeList.add(theme);
        notifyDataSetChanged();
    }

    interface Lestener {
        String getTitle();
    }
}