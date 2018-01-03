package com.napt.studentregister.cf.helper.connection;

import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.cf.model.db.LocalBody;

import java.util.List;

/**
 * Created by sreelal on 16/12/17.
 */

public interface DataBasePresent {
    void setDistrict(List<Name_Id> list);

    void setLocalBodyType(List<Name_Id> list);

    void setLocalBodyName(List<LocalBody> list);

    List<Name_Id> getDistrict();

    List<Name_Id> getLocalBodyType();

    List<LocalBody> getLocalBodyName(String districId ,String locatType);
}
