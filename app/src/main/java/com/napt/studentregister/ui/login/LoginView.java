package com.napt.studentregister.ui.login;

import com.napt.studentregister.ui._base.MvpView;

/**
 * Created by sreelal on 15/12/17.
 */

public interface LoginView extends MvpView {

    void loading(boolean check,int value,String msg);
    void Login(String name,String pass);
    void onNext();

}
