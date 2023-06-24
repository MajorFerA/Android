package com.qgj.shoping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.qgj.shoping.databinding.ActivityMainBinding;

import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNav();

    }

    private void initNav() {
        NavHostFragment nav = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.a_main_nav_host);
        navController = nav.getNavController();
        NavigationUI.setupWithNavController(binding.aMainBottomNav,navController);
        NavigationUI.setupActionBarWithNavController(this,navController);
        //监听
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged( NavController controller, NavDestination destination, @Nullable Bundle bundle) {
                //页面底部是否显示导航监听
                if(bundle !=null &&bundle.getBoolean("login")){
                    getSupportActionBar().hide();
                    binding.aMainBottomNav.setVisibility(View.GONE);
                }else if (bundle !=null&&bundle.getBoolean("isd")){
                    getSupportActionBar().show();
                    binding.aMainBottomNav.setVisibility(View.GONE);
                }
                else if (bundle !=null){
                    getSupportActionBar().show();
                    binding.aMainBottomNav.setVisibility(View.GONE);
                }else {
                    getSupportActionBar().hide();
                    binding.aMainBottomNav.setVisibility(View.VISIBLE);
                }

//                if(bundle !=null ){
//                    getSupportActionBar().show();
//                    binding.aMainBottomNav.setVisibility(View.GONE);
//                }else {
//                    getSupportActionBar().hide();
//                    binding.aMainBottomNav.setVisibility(View.VISIBLE);
//                }
            }
        });
    }
}