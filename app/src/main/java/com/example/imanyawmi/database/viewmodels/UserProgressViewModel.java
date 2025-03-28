package com.example.imanyawmi.database.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.imanyawmi.database.dao.UserProgressDao;
import com.example.imanyawmi.database.entities.UserProgress;
import com.example.imanyawmi.database.AppDatabase;

import java.util.List;

public class UserProgressViewModel extends AndroidViewModel {
    private final UserProgressDao userProgressDao;
    private final LiveData<List<UserProgress>> allUserProgress;

    public UserProgressViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        userProgressDao = db.userProgressDao();
        allUserProgress = userProgressDao.getAllUserProgress();
    }

    public LiveData<List<UserProgress>> getAllUserProgress() {
        return allUserProgress;
    }

    public void insert(UserProgress userProgress) {
        AppDatabase.databaseWriteExecutor.execute(() -> userProgressDao.insert(userProgress));
    }

    public void update(UserProgress userProgress) {
        AppDatabase.databaseWriteExecutor.execute(() -> userProgressDao.update(userProgress));
    }
}
