package com.example.imanyawmi;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChallengeActivity extends AppCompatActivity {
    private static final String TAG = "ChallengeActivity";

    private TextView txtChallengeDescription, txtDaysCompleted, txtChallengesCompleted;
    private CardView btnMarkComplete, btnSkipChallenge;
    private ImageButton btnHome;

    private List<String> dailyChallenges;
    private int completedDays = 0;
    private int completedChallenges = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: Starting ChallengeActivity");

        try {
            // Verify layout resource
            Log.d(TAG, "Attempting to set content view");
            setContentView(R.layout.activity_challenge);
            Log.d(TAG, "Content view set successfully");

            // Initialize Views
            Log.d(TAG, "Initializing views");
            initializeViews();
            Log.d(TAG, "Views initialized");

            // Setup Challenge Data
            Log.d(TAG, "Setting up challenge data");
            setupChallengeData();

            // Setup Button Listeners
            Log.d(TAG, "Setting up button listeners");
            setupButtonListeners();

            // Load First Challenge
            Log.d(TAG, "Loading first challenge");
            loadRandomChallenge();
            Log.d(TAG, "First challenge loaded");

        } catch (Exception e) {
            // Log the error with full stack trace
            Log.e(TAG, "Error initializing Challenge Activity", e);

            // Show a comprehensive error toast
            Toast.makeText(this,
                    "Error: " + e.getClass().getSimpleName() +
                            "\nMessage: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void initializeViews() {
        try {
            txtChallengeDescription = findViewById(R.id.txtChallengeDescription);
            txtDaysCompleted = findViewById(R.id.txtDaysCompleted);
            txtChallengesCompleted = findViewById(R.id.txtChallengesCompleted);
            btnMarkComplete = findViewById(R.id.btnMarkComplete);
            btnSkipChallenge = findViewById(R.id.btnSkipChallenge);
            btnHome = findViewById(R.id.btnHome);

            // Verify each view is not null
            if (txtChallengeDescription == null)
                Log.e(TAG, "txtChallengeDescription is null");
            if (txtDaysCompleted == null)
                Log.e(TAG, "txtDaysCompleted is null");
            if (txtChallengesCompleted == null)
                Log.e(TAG, "txtChallengesCompleted is null");
            if (btnMarkComplete == null)
                Log.e(TAG, "btnMarkComplete is null");
            if (btnSkipChallenge == null)
                Log.e(TAG, "btnSkipChallenge is null");
            if (btnHome == null)
                Log.e(TAG, "btnHome is null");

        } catch (Exception e) {
            Log.e(TAG, "Error in view initialization", e);
            Toast.makeText(this, "View Initialization Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void setupChallengeData() {
        dailyChallenges = new ArrayList<>();
        dailyChallenges.add("Read one page of Quran with reflection");
        dailyChallenges.add("Perform an act of kindness to a family member");
        dailyChallenges.add("Give charity, even a small amount");
        dailyChallenges.add("Spend 10 minutes in meditation and gratitude");
        dailyChallenges.add("Learn a new surah or ayah");
        dailyChallenges.add("Call a relative you haven't spoken to recently");
        dailyChallenges.add("Practice patience in a challenging situation");

        Log.d(TAG, "Challenge data setup complete. Total challenges: " + dailyChallenges.size());
    }

    private void setupButtonListeners() {
        try {
            btnMarkComplete.setOnClickListener(v -> {
                completedDays++;
                completedChallenges++;
                updateProgressTracking();
                loadRandomChallenge();
                Toast.makeText(this, "Challenge Completed!", Toast.LENGTH_SHORT).show();
            });

            btnSkipChallenge.setOnClickListener(v -> {
                loadRandomChallenge();
                Toast.makeText(this, "Challenge Skipped", Toast.LENGTH_SHORT).show();
            });

            btnHome.setOnClickListener(v -> finish());

            Log.d(TAG, "Button listeners setup successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error setting up button listeners", e);
            Toast.makeText(this, "Button Listener Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void loadRandomChallenge() {
        if (dailyChallenges.isEmpty()) {
            txtChallengeDescription.setText("No more challenges available.");
            Log.d(TAG, "No challenges left in the list");
            return;
        }

        try {
            Random random = new Random();
            int challengeIndex = random.nextInt(dailyChallenges.size());
            String selectedChallenge = dailyChallenges.get(challengeIndex);

            txtChallengeDescription.setText(selectedChallenge);
            dailyChallenges.remove(challengeIndex);

            Log.d(TAG, "Challenge loaded: " + selectedChallenge);
            Log.d(TAG, "Remaining challenges: " + dailyChallenges.size());
        } catch (Exception e) {
            Log.e(TAG, "Error loading random challenge", e);
            Toast.makeText(this, "Challenge Load Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void updateProgressTracking() {
        txtDaysCompleted.setText(String.format("Days Completed: %d/30", completedDays));
        txtChallengesCompleted.setText(String.format("Challenges: %d/15", completedChallenges));

        Log.d(TAG, "Progress updated - Days: " + completedDays + ", Challenges: " + completedChallenges);
    }
}