package kg.geektech.newsapp39.ui.home;

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

import java.util.ArrayList;

import kg.geektech.newsapp39.OnClickItem;
import kg.geektech.newsapp39.R;
import kg.geektech.newsapp39.databinding.FragmentHomeBinding;
import kg.geektech.newsapp39.ui.news.News;

public class HomeFragment extends Fragment implements OnClickItem {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;
    private News news;
    private ArrayList<News> list = new ArrayList<>();
    int pos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOpenFragment();
        initRv();
        initResult();
        //test();
    }

    /*private void test() {
        NewsAdapter.OnStateClickListener stateClickListener = new NewsAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(int position) {
                Toast.makeText(getContext(), "Go", Toast.LENGTH_SHORT).show();
            }
        };
        NewsAdapter adapter = new NewsAdapter(news, stateClickListener);
        binding.newsRv.setAdapter(adapter);
    }*/
/*
    private void deleteNews() {
        binding.newsRv.setOnLongClickListener(view -> {Toast.makeText(requireContext(), "news.getTitle()", Toast.LENGTH_SHORT).show();
            return true;
        });
    }*/

    @Override
    public void onClick(int pos) {
        News news = adapter.getItem(pos);
        if (news != null) {
            Toast.makeText(requireContext(), "news.getTitle() " + pos, Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Afrika", pos);
            list.remove(news);
        }
    }


    private void initResult() {
        getParentFragmentManager().setFragmentResultListener("rk_keys", getViewLifecycleOwner(), (requestKey, result) -> {
            news = (News) result.getSerializable("news");
            list.add(news); //Вот это одна строка решила все.
            adapter.setNewsArrayList(list); //Тут вместо news от News, поставил list от ArrayList<News>.
            binding.newsRv.setAdapter(adapter);
        });
    }

    private void initOpenFragment() {
        binding.fab.setOnClickListener(view1 -> openFragment());
    }

    private void initRv() {
        adapter = new NewsAdapter(list, this);
        binding.newsRv.setAdapter(adapter);
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }


}