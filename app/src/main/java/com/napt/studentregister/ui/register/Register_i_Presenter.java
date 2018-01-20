package com.napt.studentregister.ui.register;

import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.ui._base.MvpPresenter;

import java.util.List;

/**
 * Created by sreelal on 19/12/17.
 */

public interface Register_i_Presenter<T extends RegisterView> extends MvpPresenter<T> {

    void requestAttachment();
    void callback(int type);
    List<Name_Id> attachment_list();
    void attachment();
    void attachmentShow();


}
