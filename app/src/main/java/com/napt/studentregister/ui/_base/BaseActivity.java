package com.napt.studentregister.ui._base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.napt.studentregister.R;
import com.napt.studentregister.cf.helper.connection.FileFoleder;
import com.napt.studentregister.cf.helper.connection.SharedPresenter;
import com.napt.studentregister.cf.model.db.DataBasePresent;
import com.napt.studentregister.di.component.ActivityComponent;
import com.napt.studentregister.di.component.DaggerActivityComponent;
import com.napt.studentregister.di.module.ActivityModule;
import com.napt.studentregister.mApp;
import com.napt.studentregister.ui.register.RegisterView;
import com.napt.studentregister.ui.register.Register_i_Presenter;

import javax.inject.Inject;


/**
 * Created by sreelal on 13/12/17.
 */

public class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent activityComponent;


    @Inject
    protected
    DataBasePresent databse;

    @Inject
    protected
    FileFoleder folder;

    @Inject
    Register_i_Presenter<RegisterView>  registerview;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((mApp) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);

    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void showLoading() {

    }
    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(getResources().getColor(R.color.snackbar));
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }


    public void setwindowFull(AppCompatActivity activity) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void openActivityOnTokenExpire() {

    }

    @Override
    public void onError(int resId) {

    }

    @Override
    public void onError(String message) {

    }



    @Override
    public void showRetry(int type) {
        String message=getString(R.string.resourcenotfound);
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(getResources().getColor(R.color.snackbar));
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
        snackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                registerview.callback(1);
            }
        });
    }

    @Override
    public void showMessage(int resId) {

    }

    @Override
    public boolean isNetworkConnected() {

        return false;
    }

    @Override
    public void hideKeyboard() {

    }
}
