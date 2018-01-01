package com.napt.studentregister.ui.register;

import com.napt.studentregister.databinding.StudentRegisterBinding;
import com.napt.studentregister.ui._base.MvpPresenter;

/**
 * Created by sreelal on 19/12/17.
 */

public interface Register_i_Presenter<T extends RegisterView> extends MvpPresenter<T> {

    void requestAttachment();
    void callback(int type);

    void setStaticFields(StudentRegisterBinding binding);
}
