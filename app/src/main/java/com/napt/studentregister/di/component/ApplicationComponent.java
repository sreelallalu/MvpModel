package com.napt.studentregister.di.component;

import android.app.Application;
import android.content.Context;

import com.napt.studentregister.cf.data.DataManager;
import com.napt.studentregister.di.ApplicationContext;
import com.napt.studentregister.di.module.ApplicationModule;
import com.napt.studentregister.mApp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sreelal on 13/12/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

   void inject(mApp app);

   @ApplicationContext
   Context context();


   Application application();
   DataManager getDataManager();

}
