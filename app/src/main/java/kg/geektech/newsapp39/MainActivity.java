package kg.geektech.newsapp39;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import kg.geektech.newsapp39.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;
    private final ArrayList<Integer> integerArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavController();
        openBoardFragment(navController);
        logicBoard(navController);
    }

    private void initNavController() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        integerArrayList.add(R.id.navigation_board);
        integerArrayList.add(R.id.newsFragment);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
        navController.addOnDestinationChangedListener(((controller, destination, arguments) -> {
            if (integerArrayList.contains(destination.getId())) {
                binding.navView.setVisibility(View.GONE);
            } else {
                binding.navView.setVisibility(View.VISIBLE);
            }
            if (destination.getId() == R.id.navigation_board) getSupportActionBar().hide();
            else getSupportActionBar().show();
        }));
    }

    private void logicBoard(NavController navController) {
        Prefs prefs = new Prefs(this);
        if (!prefs.isBoardShown()) {
            navController.navigate(R.id.navigation_board);
            loginLoginGoogle(navController);
            prefs.savedBoardState();
        } else {
            navController.navigate(R.id.navigation_home);
            loginLoginGoogle(navController);
        }
    }

    private void loginLoginGoogle(NavController navController) {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            navController.navigate(R.id.navigation_login);
        }
    }

    private void openBoardFragment(NavController navController) {
        navController.navigate(R.id.navigation_board);
    }
}