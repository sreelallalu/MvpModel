package com.napt.studentregister.di.component;

import com.napt.studentregister.di.PerActivity;
import com.napt.studentregister.di.module.ActivityModule;
import com.napt.studentregister.ui.dashboard.DashboardActivity;
import com.napt.studentregister.ui.login.LoginActivity;
import com.napt.studentregister.ui._base.BaseActivity;
import com.napt.studentregister.ui.register.RegisterActivity;

import dagger.Component;

/**
 * Created by sreelal on 13/12/17.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(BaseActivity activity);
    void inject(LoginActivity activity);
    void inject(DashboardActivity activity);
    void inject(RegisterActivity activity);

}
