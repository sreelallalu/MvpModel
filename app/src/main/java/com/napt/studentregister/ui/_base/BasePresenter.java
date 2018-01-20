package com.napt.studentregister.ui._base;

import android.content.Context;

import com.napt.studentregister.cf.data.DataManager;

/**
 * Created by sreelal on 15/12/17.
 */

public class  BasePresenter<V extends MvpView> implements MvpPresenter<V> {

      Context context;
     DataManager dataManager;

    public BasePresenter(DataManager dataManager)
     {
   this.dataManager=dataManager;
    }
    private V mMvpView;
    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {

        mMvpView = null;
    }
     public DataManager getDataManager() {
        return dataManager;
    }

    public V getView(){
       return mMvpView;
   }


}
