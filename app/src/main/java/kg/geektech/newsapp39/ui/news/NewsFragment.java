package kg.geektech.newsapp39.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.text.DateFormat;
import java.util.Date;

import kg.geektech.newsapp39.R;
import kg.geektech.newsapp39.databinding.FragmentNewsBinding;
import kg.geektech.newsapp39.models.News;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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
       /* Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        long mYear, mMonth, mDay, mHour, mMinute, timeDate;
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);
        timeDate = calendar.getTimeInMillis();
        DateFormat.getDateTimeInstance().format(new Date(millis));*/

        String title = binding.editText.getText().toString();
        News news = new News(title, DateFormat.getDateTimeInstance().format(new Date(millis)) + "   ");
        Bundle bundle = new Bundle();
        bundle.putSerializable("news", news);
        getParentFragmentManager().setFragmentResult("rk_keys", bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}