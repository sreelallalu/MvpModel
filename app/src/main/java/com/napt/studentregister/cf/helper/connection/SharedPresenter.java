package com.napt.studentregister.cf.helper.connection;

/**
 * Created by sreelal on 15/12/17.
 */

public interface SharedPresenter {

    void setCentreID(String centerid, boolean logincheck);
    void setCentre_UserID(String centerid);
    void setAttach(String attach);
    void setCourse(String course);

    String getCourse();
    String getAttach();
    String getCenterId();
    String getCentre_UserID();
    boolean getLogincheck();
}

