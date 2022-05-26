package alex.exsample.work.ui.lectures;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.Objects;

import alex.exsample.work.R;
import alex.exsample.work.databinding.FragmentPdfReaderBinding;
import alex.exsample.work.db.DbQuestion;

public class PdfReader extends Fragment {
    private FragmentPdfReaderBinding binding;
    private PDFView pdfView;
    Toolbar toolbar;
    DbQuestion question;
    Bundle bundle = new Bundle();
    int id=0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu,@NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
      //  super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.action_test:
                NavController navController = Navigation.findNavController(getView());
                bundle.putInt("id_theme",id);
                navController.navigate(R.id.nav_test_menu, bundle);
                return true;
            case R.id.action_favorites:
                question = new DbQuestion("Topic", getContext());
                if (Objects.equals(question.getField("favorite", id), "true"))
                    question.setFavorite("false",id);
                else
                    question.setFavorite("true",id);
                return true;
        }
        return true;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPdfReaderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pdfView = binding.pdfView;
        String titleMainTheme = getArguments().getString("title_main");
        question = new DbQuestion("Topic", getContext());
        id = question.getIdWhereTitle(titleMainTheme, "id_topic", "title");
        init(id);
        return root;
    }

    void init(int id) {
        String id_pdf = question.getPDF(id);
        try {
             pdfView.fromAsset(id_pdf).load();
       //     ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(id_pdf); // пример вывода названия pdf работает
        } catch (Exception e) {}
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
