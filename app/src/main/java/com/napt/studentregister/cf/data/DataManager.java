package com.napt.studentregister.cf.data;

import android.content.Context;

import com.napt.studentregister.cf.helper.connection.SharedPresenter;
import com.napt.studentregister.cf.helper.connection.DataBasePresent;

/**
 * Created by sreelal on 28/12/17.
 */

public interface DataManager extends SharedPresenter ,DataBasePresent {

    Context getContext();

}
