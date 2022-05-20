package alex.exsample.work.ui.lectures;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.github.barteksc.pdfviewer.PDFView;
import alex.exsample.work.databinding.FragmentGalleryBinding;
import alex.exsample.work.databinding.FragmentPdfReaderBinding;
import alex.exsample.work.db.DbQuestion;
import alex.exsample.work.ui.gallery.GalleryViewModel;

public class PdfReader extends Fragment {
    private FragmentPdfReaderBinding binding;
    private PDFView pdfView;
    DbQuestion question;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPdfReaderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        pdfView = binding.pdfView;
        String titleMainTheme = getArguments().getString("title_main");
        question = new DbQuestion("Topic", getContext());
        int id = question.getIdWhereTitle(titleMainTheme, "id_topic");
        init(id);
        return root;
    }

    void init(int id) {
        String id_pdf = question.getPDF(id);
        try {
             pdfView.fromAsset(id_pdf).load();
        } catch (Exception e) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
