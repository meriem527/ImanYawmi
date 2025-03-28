package com.example.imanyawmi.database.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.imanyawmi.database.AppDatabase;
import com.example.imanyawmi.database.entities.Challenge;
import com.example.imanyawmi.database.dao.ChallengeDao;

public class ChallengeViewModel extends AndroidViewModel {
    private final ChallengeDao challengeDao;
    private final MutableLiveData<List<Challenge>> allChallenges = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ChallengeViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        challengeDao = db.challengeDao();

        executorService.execute(() -> {
            List<Challenge> challenges = challengeDao.getAllChallenges(); // Si getAllChallenges() ne renvoie pas LiveData
            allChallenges.postValue(challenges);
        });
    }

    public LiveData<List<Challenge>> getAllChallenges() {
        return allChallenges;
    }

    public void insert(Challenge challenge) {
        executorService.execute(() -> challengeDao.insert(challenge));
    }

    public void update(Challenge challenge) {
        executorService.execute(() -> challengeDao.update(challenge));
    }
}
