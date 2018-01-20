package com.napt.studentregister.cf.data;

import android.content.Context;

import com.napt.studentregister.cf.helper.connection.SharedPresenter;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.cf.helper.connection.DataBasePresent;
import com.napt.studentregister.cf.model.db.LocalBody;
import com.napt.studentregister.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sreelal on 28/12/17.
 */
@Singleton
public class AppDataManager implements DataManager {
    private Context mContext;
    private SharedPresenter mPreferencesHelper;
    private DataBasePresent dataBasePresent;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          SharedPresenter preferencesHelper,DataBasePresent dataBasePresent) {
        mContext = context;

        mPreferencesHelper = preferencesHelper;
        this.dataBasePresent = dataBasePresent;
    }

    @Override
    public void setCentreID(String centerid, boolean logincheck) {
        mPreferencesHelper.setCentreID(centerid, logincheck);

    }


    public Context getAppContext()
    {
        return mContext;
    }
    @Override
    public void setCentre_UserID(String centerid) {
        mPreferencesHelper.setCentre_UserID(centerid);
    }

    @Override
    public void setAttach(String attach) {
        mPreferencesHelper.setAttach(attach);
    }

    @Override
    public void setCourse(String course) {
        mPreferencesHelper.setCourse(course);
    }

    @Override
    public String getCourse() {
        return mPreferencesHelper.getCourse();
    }

    @Override
    public String getAttach() {
        return mPreferencesHelper.getAttach();
    }

    @Override
    public String getCenterId() {
        return mPreferencesHelper.getCenterId();
    }

    @Override
    public String getCentre_UserID() {

        return mPreferencesHelper.getCentre_UserID();
    }

    @Override
    public boolean getLogincheck() {
        return mPreferencesHelper.getLogincheck();

    }

    @Override
    public void setDistrict(List<Name_Id> list) {
        dataBasePresent.setDistrict(list);
    }

    @Override
    public void setLocalBodyType(List<Name_Id> list) {
      dataBasePresent.setLocalBodyType(list);
    }

    @Override
    public void setLocalBodyName(List<LocalBody> list) {
   dataBasePresent.setLocalBodyName(list);
    }

    @Override
    public List<Name_Id> getDistrict() {
        return dataBasePresent.getDistrict();
    }

    @Override
    public List<Name_Id> getLocalBodyType() {
        return dataBasePresent.getLocalBodyType();
    }

    @Override
    public List<LocalBody> getLocalBodyName(String districId, String locatType) {
        return dataBasePresent.getLocalBodyName(districId,locatType);
    }

    @Override
    public Context getContext() {
        return mContext;
    }
}
