package com.napt.studentregister.di.module;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.napt.studentregister.cf.adapter.OneField_Adapter;
import com.napt.studentregister.di.ActivityContext;
import com.napt.studentregister.di.PerActivity;
import com.napt.studentregister.ui.login.LoginPresenter;
import com.napt.studentregister.ui.login.LoginView;
import com.napt.studentregister.ui.login.Login_i_Presenter;
import com.napt.studentregister.ui.register.RegisterPresenter;
import com.napt.studentregister.ui.register.RegisterView;
import com.napt.studentregister.ui.register.Register_i_Presenter;

import dagger.Module;
import dagger.Provides;


/**
 * Created by sreelal on 13/12/17.
 */
@Module
public class ActivityModule {
    AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {

        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {

        return activity;
    }


    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }


    @Provides
    @PerActivity
    Login_i_Presenter<LoginView> loginpresenter
            (LoginPresenter<LoginView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    Register_i_Presenter<RegisterView> regpresenter
            (RegisterPresenter<RegisterView> presenter) {

        return presenter;
    }

    @Provides
    @PerActivity
    OneField_Adapter oneField_adapter() {

        return new OneField_Adapter(activity);
    }
    @Provides
    @PerActivity
    Dialog dialog() {

        return new Dialog(activity);
    }


}
