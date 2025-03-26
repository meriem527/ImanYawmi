package com.example.imanyawmi;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

public class QuranActivity extends AppCompatActivity {
    private ListView listSurahs;
    private TextView tabSurahs, tabFavorites;
    private CardView btnKhatemQuran, btnContinueReading;
    private ImageButton btnHome, btnMoreOptions;

    private List<String> surahs;
    private List<String> favorites;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        // Initialize views
        initializeViews();

        // Setup Surahs List
        setupSurahsList();

        // Setup Tab Listeners
        setupTabListeners();

        // Setup Button Listeners
        setupButtonListeners();
    }

    private void initializeViews() {
        listSurahs = findViewById(R.id.listSurahs);
        tabSurahs = findViewById(R.id.tabSurahs);
        tabFavorites = findViewById(R.id.tabFavorites);
        btnKhatemQuran = findViewById(R.id.btnKhatemQuran);
        btnContinueReading = findViewById(R.id.btnContinueReading);
        btnHome = findViewById(R.id.btnHome);
        btnMoreOptions = findViewById(R.id.btnMoreOptions);
    }

    private void setupSurahsList() {
        // Populate Surahs List
        surahs = new ArrayList<>();
        surahs.add("Al-Fatihah");
        surahs.add("Al-Baqarah");
        // Add more Surahs...

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                surahs
        );
        listSurahs.setAdapter(adapter);

        // Surah item click listener
        listSurahs.setOnItemClickListener((parent, view, position, id) -> {
            String selectedSurah = surahs.get(position);
            Toast.makeText(this, "Selected: " + selectedSurah, Toast.LENGTH_SHORT).show();
            // TODO: Implement Surah reading functionality
        });
    }

    private void setupTabListeners() {
        tabSurahs.setOnClickListener(v -> {
            // Switch to Surahs list
            adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    surahs
            );
            listSurahs.setAdapter(adapter);
        });

        tabFavorites.setOnClickListener(v -> {
            // Populate and switch to Favorites list
            favorites = new ArrayList<>();
            favorites.add("Al-Fatihah (Favorite)");

            adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    favorites
            );
            listSurahs.setAdapter(adapter);
        });
    }

    private void setupButtonListeners() {
        btnKhatemQuran.setOnClickListener(v -> {
            Toast.makeText(this, "Starting New Quran Reading", Toast.LENGTH_SHORT).show();
            // TODO: Implement new Quran reading start
        });

        btnContinueReading.setOnClickListener(v -> {
            Toast.makeText(this, "Continuing Last Reading", Toast.LENGTH_SHORT).show();
            // TODO: Implement continue reading
        });

        btnHome.setOnClickListener(v -> finish());

        btnMoreOptions.setOnClickListener(v -> {
            Toast.makeText(this, "More Options", Toast.LENGTH_SHORT).show();
            // TODO: Implement more options menu
        });
    }
}