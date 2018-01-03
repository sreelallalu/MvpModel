package com.napt.studentregister.di.module;

import android.app.Application;
import android.content.Context;

import com.napt.studentregister.cf.data.AppDataManager;
import com.napt.studentregister.cf.data.DataManager;
import com.napt.studentregister.cf.helper.DataBase;
import com.napt.studentregister.cf.helper.SharedHelper;
import com.napt.studentregister.cf.helper.connection.DataBasePresent;
import com.napt.studentregister.cf.helper.connection.SharedPresenter;
import com.napt.studentregister.di.ApplicationContext;
import com.napt.studentregister.mApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final mApp mAp;

    public ApplicationModule(mApp application) {
        this.mAp = application;
    }

    @Provides
    @ApplicationContext
    public Context provideApplicationcontext() {
        return mAp;
    }

    @Provides
    Application provideApplication() {

        return mAp;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {

        return appDataManager;
    }

    @Provides
    @Singleton
     SharedPresenter sharedPresenter(SharedHelper sharedPreferences) {

        return sharedPreferences;
    }
    @Provides
    @Singleton
    DataBasePresent dataBasePresent(DataBase dataBase) {

        return dataBase;
    }
}

