package com.example.imanyawmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide action bar for a more immersive experience
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Set up click listeners for your menu items
        setupClickListeners();
    }

    private void setupClickListeners() {
        // Planner button (previously Prayer)
        LinearLayout layoutPrayer = findViewById(R.id.layoutPrayer);
        layoutPrayer.setOnClickListener(v -> {
            // Navigate to PlannerActivity
            Intent intent = new Intent(MainActivity.this, PlannerActivity.class);
            startActivity(intent);
        });

        // Quran button - NOW NAVIGATES TO QURAN ACTIVITY
        LinearLayout layoutQuran = findViewById(R.id.layoutQuran);
        layoutQuran.setOnClickListener(v -> {
            // Navigate to QuranActivity
            Intent intent = new Intent(MainActivity.this, QuranActivity.class);
            startActivity(intent);
        });

        // Daily Challenge button - NOW NAVIGATES TO CHALLENGE ACTIVITY
        LinearLayout layoutDailyChallenge = findViewById(R.id.layoutDailyChallenge);
        layoutDailyChallenge.setOnClickListener(v -> {
            // Navigate to ChallengeActivity
            Intent intent = new Intent(MainActivity.this, ChallengeActivity.class);
            startActivity(intent);
        });

        // Notepad button
        LinearLayout layoutNotepad = findViewById(R.id.layoutNotepad);
        layoutNotepad.setOnClickListener(v -> {
            // Handle notepad button click
        });

        // Duas button
        LinearLayout layoutDuas = findViewById(R.id.layoutDuas);
        layoutDuas.setOnClickListener(v -> {
            // Handle duas button click
        });

        // Daily Settings button
        LinearLayout layoutDailySettings = findViewById(R.id.layoutDailySettings);
        layoutDailySettings.setOnClickListener(v -> {
            // Handle daily settings button click
        });
    }
}