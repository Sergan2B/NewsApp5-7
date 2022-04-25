package kg.geektech.newsapp39.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Collections;
import java.util.List;

import kg.geektech.newsapp39.App;
import kg.geektech.newsapp39.R;
import kg.geektech.newsapp39.databinding.FragmentHomeBinding;
import kg.geektech.newsapp39.models.News;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;
    private News news;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NewsAdapter();
        adapter.addItems(App.getAppDatabase().newsDao().getAll());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOpenFragment();
        initResult();
        adapter.setOnLongClickItem(new NewsAdapter.OnLongClickItem() {
            @Override
            public void onClick(int pos) {
            }

            @Override
            public void onLongClick(int pos) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Удалить новость?");
                alert.setMessage("Вы действительно собираетесь удалить новость? После удаления, новость нельзя будет восстановить.");
                alert.setPositiveButton("Удалить", (dialog, whichButton) -> {
                    news = adapter.getItem(pos);
                    if (news != null) {
                        Toast.makeText(requireContext(), "news.getTitle() " + pos, Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Afrika", pos);
                        adapter.remove(pos);
                        binding.newsRv.setAdapter(adapter);
                    }
                });
                alert.setNegativeButton("Отмена", (dialog, whichButton) -> dialog.cancel());
                alert.show();
            }
        });
    }

    private void initResult() {
        getParentFragmentManager().setFragmentResultListener("rk_keys", getViewLifecycleOwner(), (requestKey, result) -> {
            news = (News) result.getSerializable("news");
            Log.e("141414", "initResult: " + news.getTitle());
            adapter.setNewsArrayList(news); //Тут вместо news от News, поставил list от ArrayList<News>.
            binding.newsRv.setAdapter(adapter);
        });
        initRv();
    }

    private void initOpenFragment() {
        binding.fab.setOnClickListener(view1 -> openFragment());
    }

    private void initRv() {
        binding.newsRv.setAdapter(adapter);
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.home, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_exit) {
            requireActivity().finish();
        }
        if (item.getItemId() == R.id.action_clear) {
       //     adapter.removeAll(Collections.singletonList(news));
        }
        if (item.getItemId() == R.id.action_sort) {
            adapter.setNewsArrayList(App.getAppDatabase().newsDao().sort());
            binding.newsRv.setAdapter(adapter);
        }
        return super.onOptionsItemSelected(item);
    }
}