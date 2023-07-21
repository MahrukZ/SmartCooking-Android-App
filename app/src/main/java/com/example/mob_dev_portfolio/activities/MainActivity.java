package com.example.mob_dev_portfolio.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.UI.FavouriteFragment;
import com.example.mob_dev_portfolio.UI.MenuFragment;
import com.example.mob_dev_portfolio.UI.PantryFragment;
import com.example.mob_dev_portfolio.UI.ShoppingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final Integer AudioRequestCode = 1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, new PantryFragment()).commit();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }
    }


    // Code adapted from https://developer.android.com/reference/android/speech/SpeechRecognizer &
    // https://github.com/abhinav0612/SpeechToText [Accessed: 20th May 2022]
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, AudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AudioRequestCode && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.pantry:
                            selectedFragment = new PantryFragment();
                            break;

                        case R.id.menu:
                            selectedFragment = new MenuFragment();
                            break;

                        case R.id.favorites:
                            selectedFragment = new FavouriteFragment();
                            break;

                        case R.id.shoppingCart:
                            selectedFragment = new ShoppingFragment();
                            break;
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragment, selectedFragment)
                            .commit();
                    return true;
                }
            };
}