package kg.geektech.newsapp39.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Date;

import kg.geektech.newsapp39.App;
import kg.geektech.newsapp39.R;
import kg.geektech.newsapp39.databinding.FragmentNewsBinding;
import kg.geektech.newsapp39.models.News;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSetText();
    }

    private void initSetText() {
        binding.btnSave.setOnClickListener(view1 -> save());
    }

    private void save() {
        long millis = System.currentTimeMillis();
        Bundle bundle = new Bundle();
        String title = String.valueOf(binding.editText.getText());

        if (title.isEmpty()) { //Пустая строка
            Toast.makeText(requireContext(), "Заполните поле", Toast.LENGTH_SHORT).show();
            return;
        }
        News news = new News(title, DateFormat.getDateTimeInstance().format(new Date(millis)) + "   ");
        App.appDatabase.newsDao().insert(news); //Записывает данные в бд
        addToFirestore(news);
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_keys", bundle);
    }

    private void addToFirestore(News news) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("news").add(news).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(requireContext(), "Успешно", Toast.LENGTH_SHORT).show();
                close();
            } else Toast.makeText(requireContext(), "Неуспешно", Toast.LENGTH_SHORT).show();
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}