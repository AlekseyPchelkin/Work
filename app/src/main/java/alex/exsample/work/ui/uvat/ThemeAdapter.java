package alex.exsample.work.ui.uvat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import alex.exsample.work.R;
import alex.exsample.work.databinding.ThemeItemBinding;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeHolder>  {
    ArrayList<Theme> themeList = new ArrayList<Theme>();
    public class ThemeHolder extends RecyclerView.ViewHolder{
        private ThemeItemBinding binding;
        public ThemeHolder(@NonNull View itemView) {
            super(itemView);
            binding = ThemeItemBinding.bind(itemView);
        }
        void bind(Theme theme){
            binding.iv2.setImageResource(theme.imageId);
            binding.tvTitle.setText(theme.title);
        }
    }

    @NonNull
    @Override
    public ThemeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Создание view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_item, parent, false);
        return new ThemeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeHolder holder, int position) { //
        holder.bind(themeList.get(position));
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    void addTheme(Theme theme){
        themeList.add(theme);
        notifyDataSetChanged();
    }
}
