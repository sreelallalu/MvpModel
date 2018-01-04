package com.napt.studentregister;


import android.app.Application;

import com.napt.studentregister.cf.data.DataManager;
import com.napt.studentregister.di.component.ApplicationComponent;
import com.napt.studentregister.di.component.DaggerApplicationComponent;
import com.napt.studentregister.di.module.ApplicationModule;

import javax.inject.Inject;


public class mApp extends Application {

    public ApplicationComponent applicationComponent;

      @Inject
      DataManager dataManager;
    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
       applicationComponent.inject(this);
       // applicationComponent.provideSharedpreference(this);



    }

    public ApplicationComponent getComponent() {

        return applicationComponent;
    }
}
