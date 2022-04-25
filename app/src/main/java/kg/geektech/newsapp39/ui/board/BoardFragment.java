package kg.geektech.newsapp39.ui.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

import kg.geektech.newsapp39.Prefs;
import kg.geektech.newsapp39.R;
import kg.geektech.newsapp39.databinding.FragmentBoardBinding;

public class BoardFragment extends Fragment implements BoardAdapter.OnClickItem {
    private FragmentBoardBinding binding;
    private BoardAdapter adapter;
    private final String[] tabText = {"", "", ""};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniViewPager();
        initClick();
        buttonMet();
        binding.boardIv.setAdapter(adapter);
    }

    private void buttonMet() {
        binding.boardIv.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (position == 2) binding.button4.setVisibility(View.INVISIBLE);
                else binding.button4.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void initClick() {
        binding.button4.setOnClickListener(view1 -> onClick(binding.button4));
    }

    private void iniViewPager() {
        adapter = new BoardAdapter(this);
        binding.boardIv.setAdapter(adapter);
        new TabLayoutMediator(binding.tabLayout, binding.boardIv, (tab, position) -> tab.setText(tabText[position])).attach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(Button button) {
        Toast.makeText(requireContext(), "Работает", Toast.LENGTH_SHORT).show();
        close();
    }

    private void close() {
        Prefs prefs = new Prefs(requireContext());
        prefs.savedBoardState();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}