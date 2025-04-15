package com.fyp.jeremyworldtransfer;

import android.os.Bundle;
import android.view.MenuItem;
import com.fyp.jeremyworldtransfer.ui.history.HistoryFragment;
import com.fyp.jeremyworldtransfer.ui.home.HomeFragment;
import com.fyp.jeremyworldtransfer.ui.support.SupportFragment;
import com.fyp.jeremyworldtransfer.ui.transfer.TransferFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_transfer, R.id.navigation_history, R.id.navigation_support)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        setupBottomNavigation(navView);
        }

        private void setupBottomNavigation(BottomNavigationView bottomNavigationView) {
        BottomNavigationView.OnNavigationItemSelectedListener
             navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectNavigationItem(item);
                        return true;
                //https://guides.codepath.com/android/Fragment-Navigation-Drawer
            }
        };
    }

    public void selectNavigationItem(MenuItem item) {       //https://guides.codepath.com/android/Fragment-Navigation-Drawer
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.navigation_home:
            fragmentClass = HomeFragment.class;
              break;
            case R.id.navigation_transfer:
            fragmentClass = TransferFragment.class;
              break;
            case R.id.navigation_history:
            fragmentClass = HistoryFragment.class;
              break;
            case R.id.navigation_support:
                fragmentClass = SupportFragment.class;
                  break;
            default:
                fragmentClass = HomeFragment.class;
            }
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    }


