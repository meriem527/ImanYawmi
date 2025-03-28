package com.example.imanyawmi.database.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.imanyawmi.database.AppDatabase;
import com.example.imanyawmi.database.entities.QuranReading;
import com.example.imanyawmi.database.dao.QuranReadingDao;

import java.util.List;

public class QuranReadingViewModel extends AndroidViewModel {
    private QuranReadingDao quranReadingDao;
    private LiveData<List<QuranReading>> allQuranReadings;

    public QuranReadingViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        quranReadingDao = db.quranReadingDao();
        allQuranReadings = quranReadingDao.getAllReadings();  // Correction ici
    }

    public LiveData<List<QuranReading>> getAllQuranReadings() {
        return allQuranReadings;
    }

    public void insert(QuranReading quranReading) {
        AppDatabase.databaseWriteExecutor.execute(() -> quranReadingDao.insert(quranReading));
    }

    public void update(QuranReading quranReading) {
        AppDatabase.databaseWriteExecutor.execute(() -> quranReadingDao.update(quranReading));
    }
}
