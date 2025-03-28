package com.example.imanyawmi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.imanyawmi.database.viewmodels.DailyContentViewModel;
import com.example.imanyawmi.database.entities.DailyContent;

public class MainActivity extends AppCompatActivity {
    private TextView tvHadithContent;
    private TextView tvHadithType;
    private TextView tvHadithReference;
    private DailyContentViewModel dailyContentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize daily content TextViews
        tvHadithContent = findViewById(R.id.tvHadithContent);
        tvHadithType = findViewById(R.id.tvHadithType);
        tvHadithReference = findViewById(R.id.tvHadithReference);

        // Hide action bar for a more immersive experience
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Set up ViewModel for daily content
        dailyContentViewModel = new ViewModelProvider(this).get(DailyContentViewModel.class);

        // Observe and update daily content
        dailyContentViewModel.getDailyContent().observe(this, this::updateDailyContentUI);

        // Set up click listeners for menu items
        setupClickListeners();
    }

    private void updateDailyContentUI(DailyContent dailyContent) {
        if (dailyContent != null) {
            // Update TextViews with daily content
            tvHadithType.setText(dailyContent.getType().toUpperCase());
            tvHadithContent.setText(dailyContent.getContent());
            tvHadithReference.setText(dailyContent.getReference());
        }
    }

    private void setupClickListeners() {
        // Planner button (previously Prayer)
        LinearLayout layoutPrayer = findViewById(R.id.layoutPrayer);
        layoutPrayer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PlannerActivity.class);
            startActivity(intent);
        });

        // Quran button
        LinearLayout layoutQuran = findViewById(R.id.layoutQuran);
        layoutQuran.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuranActivity.class);
            startActivity(intent);
        });

        // Daily Challenge button
        LinearLayout layoutDailyChallenge = findViewById(R.id.layoutDailyChallenge);
        layoutDailyChallenge.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChallengeActivity.class);
            startActivity(intent);
        });

        // Notepad button
        LinearLayout layoutNotepad = findViewById(R.id.layoutNotepad);
        layoutNotepad.setOnClickListener(v -> {
            // TODO: Implement Notepad Activity
        });

        // Duas button
        LinearLayout layoutDuas = findViewById(R.id.layoutDuas);
        layoutDuas.setOnClickListener(v -> {
            // TODO: Implement Duas Activity
        });

        // Daily Settings button
        LinearLayout layoutDailySettings = findViewById(R.id.layoutDailySettings);
        layoutDailySettings.setOnClickListener(v -> {
            // TODO: Implement Daily Settings Activity
        });
    }
}