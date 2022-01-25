package kg.geektech.newsapp39;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import kg.geektech.newsapp39.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final int RESULT_GALLERY = 0;
    public static final int REQUEST_IMAGE_SELECTOR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        openBoardFragment(navController);
        logicBoard(navController);
       /* ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(R.id.navigation_home);
        integerArrayList.add(R.id.navigation_dashboard);
        integerArrayList.add(R.id.navigation_notifications);
        integerArrayList.add(R.id.navigation_profile);
        navController.addOnDestinationChangedListener(((controller, destination, arguments) -> {
            if (integerArrayList.contains((destination.getId()))) {
                binding.navView.setVisibility(View.VISIBLE);
            } else {
                binding.navView.setVisibility(View.GONE);
            }
        }));*/

    }

    private void logicBoard(NavController navController) {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(R.id.navigation_board);
        integerArrayList.add(R.id.newsFragment);
        navController.addOnDestinationChangedListener(((controller, destination, arguments) -> {
            if (integerArrayList.contains(destination.getId())) {
                binding.navView.setVisibility(View.GONE);
            } else {
                binding.navView.setVisibility(View.VISIBLE);
            }
        }));
    }

    private void openBoardFragment(NavController navController) {
        navController.navigate(R.id.navigation_board);
    }
}