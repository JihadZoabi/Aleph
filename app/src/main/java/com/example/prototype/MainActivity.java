package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.prototype.databinding.ActivityMainBinding;
import com.example.prototype.fragments.CartFragment;
import com.example.prototype.fragments.HomeFragment;
import com.example.prototype.fragments.LeaderBoardFragment;
import com.example.prototype.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Lessons.init(getResources());
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = (User) getIntent().getSerializableExtra("user");
        replaceFragment(new HomeFragment(user));
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.homeItem:
                    replaceFragment(new HomeFragment(user));
                    break;
                case R.id.cartItem:
                    replaceFragment(new CartFragment());
                    break;
                case R.id.leaderBoardItem:
                    replaceFragment(new LeaderBoardFragment());
                    break;
                case R.id.profileItem:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}