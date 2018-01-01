package com.napt.studentregister.cf.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.napt.studentregister.cf.contant.SHAREVALUE;
import com.napt.studentregister.cf.helper.connection.SharedPresenter;
import com.napt.studentregister.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sreelal on 14/12/17.
 */
@Singleton
public class SharedHelper implements SharedPresenter {
    private SharedPreferences mSharedPreferences;

    @Inject
    public SharedHelper(@ApplicationContext Context context) {

        this.mSharedPreferences = context.getSharedPreferences(SHAREVALUE.main, Context.MODE_PRIVATE);
    }


    @Override
    public void setCentreID(String centerid, boolean logincheck) {

        mSharedPreferences.edit().putString(SHAREVALUE.Center_Id, centerid).apply();
        mSharedPreferences.edit().putBoolean(SHAREVALUE.Login_Check, logincheck).apply();

    }

    @Override
    public void setCentre_UserID(String centerid) {
        mSharedPreferences.edit().putString(SHAREVALUE.Center_user_Id, centerid).apply();

    }

    @Override
    public void setAttach(String attach) {
        mSharedPreferences.edit().putString(SHAREVALUE.ATTACHMENT, attach).apply();

    }

    @Override
    public void setCourse(String course) {
        mSharedPreferences.edit().putString(SHAREVALUE.Course, course).apply();

    }

    @Override
    public String getCourse() {
        return mSharedPreferences.getString(SHAREVALUE.Course, null);
    }

    @Override
    public String getAttach() {
        return mSharedPreferences.getString(SHAREVALUE.ATTACHMENT, null);

    }

    @Override
    public String getCenterId() {
        return mSharedPreferences.getString(SHAREVALUE.Center_Id, null);

    }

    @Override
    public String getCentre_UserID() {
        return mSharedPreferences.getString(SHAREVALUE.Center_user_Id, null);

    }

    @Override
    public boolean getLogincheck() {
        return mSharedPreferences.getBoolean(SHAREVALUE.Login_Check, false);

    }

  /*  public void setCentreID(String centerid, boolean logincheck) {

        mSharedPreferences.edit().putString(SHAREVALUE.Center_Id,centerid).apply();
        mSharedPreferences.edit().putBoolean(SHAREVALUE.Login_Check,logincheck).apply();

    }

    public void setCentre_UserID(String centerid) {

        mSharedPreferences.edit().putString(SHAREVALUE.Center_user_Id,centerid).apply();
    }

    public void setAttach(String attachment) {

        mSharedPreferences.edit().putString(SHAREVALUE.ATTACHMENT,attachment).apply();

    }

    public void setCourse(String course) {
        mSharedPreferences.edit().putString(SHAREVALUE.Course,course).apply();

    }

    public String getCourse( ) {
      return   mSharedPreferences.getString(SHAREVALUE.Course,null);

    }

    public String getAttach() {
 return  mSharedPreferences.getString(SHAREVALUE.ATTACHMENT,null);
    }


    public String getCentreId() {

      return   mSharedPreferences.getString(SHAREVALUE.Center_Id,null);
    }

    public  String getCentre_UserId( ) {
     return   mSharedPreferences.getString(SHAREVALUE.Center_user_Id,null);

    }

    public boolean getLogincheck() {

        return   mSharedPreferences.getBoolean(SHAREVALUE.Login_Check,false);

    }
*/
}
