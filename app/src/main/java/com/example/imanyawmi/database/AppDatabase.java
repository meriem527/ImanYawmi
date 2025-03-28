package com.example.imanyawmi.database;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.imanyawmi.database.dao.UserProgressDao;
import com.example.imanyawmi.database.dao.ChallengeDao;
import com.example.imanyawmi.database.dao.QuranReadingDao;
import com.example.imanyawmi.database.dao.DailyContentDao;
import com.example.imanyawmi.database.entities.UserProgress;
import com.example.imanyawmi.database.entities.Challenge;
import com.example.imanyawmi.database.entities.QuranReading;
import com.example.imanyawmi.database.entities.DailyContent;

@Database(entities = {UserProgress.class, Challenge.class, QuranReading.class, DailyContent.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // Executor pour les opérations en arrière-plan
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public abstract UserProgressDao userProgressDao();
    public abstract ChallengeDao challengeDao();
    public abstract QuranReadingDao quranReadingDao();
    public abstract DailyContentDao dailyContentDao();

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "imanyawmi_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback) // Ajout du callback pour pré-remplir la DB
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Callback pour insérer des données par défaut à la création de la base de données
    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                AppDatabase database = INSTANCE;
                DailyContentDao dao = database.dailyContentDao();

                // Ajout de contenu initial : Hadiths, Duas et Versets du Coran
                dao.insert(new DailyContent("hadith", "The best among you are those who learn the Quran and teach it.", "Sahih Al-Bukhari"));
                dao.insert(new DailyContent("quran", "Indeed, with hardship comes ease.", "Surah Al-Inshirah, 94:6"));
                dao.insert(new DailyContent("dua", "O Allah, guide me among those whom You have guided.", "Dua for Guidance"));
            });
        }
    };
}
