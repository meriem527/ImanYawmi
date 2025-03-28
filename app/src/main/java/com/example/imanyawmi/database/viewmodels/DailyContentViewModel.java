package com.example.imanyawmi.database.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.imanyawmi.database.AppDatabase;
import com.example.imanyawmi.database.entities.DailyContent;
import com.example.imanyawmi.database.dao.DailyContentDao;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DailyContentViewModel extends AndroidViewModel {
    private DailyContentDao dailyContentDao;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private MutableLiveData<DailyContent> dailyContent = new MutableLiveData<>();

    public DailyContentViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        dailyContentDao = db.dailyContentDao();
        loadDailyContent();
    }

    public LiveData<DailyContent> getDailyContent() {
        return dailyContent;
    }

    private void loadDailyContent() {
        executorService.execute(() -> {
            DailyContent content = dailyContentDao.getRandomDailyContent();
            dailyContent.postValue(content);
        });
    }
}

