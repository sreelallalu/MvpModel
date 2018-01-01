package com.napt.studentregister.ui.login;

import com.napt.studentregister.ui._base.MvpPresenter;

/**
 * Created by sreelal on 15/12/17.
 */

public interface Login_i_Presenter<T extends LoginView> extends MvpPresenter<T>{
    void call(String name, String pass);


}
