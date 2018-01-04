package com.napt.studentregister.cf.contant;

public interface Api_Resp {
    interface CENTERLOGIN{
        String STATUS = "status";
        String CENTER_ID = "centerid";
        String USER_ID = "userid";

        //district object

        String DISTRICT_OBJ = "district";
        String DISTRICT = "district";
        String DISTRICT_ID = "district_id";

        //local body TYPE OBJECT

        String LOCALBODY_TYPE_OBJ = "localbodyType";
        String LOCALBODY_TYPE = "LBTypeName_eng";
        String LOCALBODY_TYPE_ID = "LBTypeid";

        //local body object

        String LOCALBODY_OBJ = "localbodyName";
        String LOCALBODY = "lb_name_eng";
        String LOCALBODY_ID = "lb_id";
        String LOCAL_TYPE_ID = "lb_type";
        String LOCAL_DISTRICT_ID = "districtcd";

        //tax list

        String TAXLIST_OBJ = "taxlist";
        String TAX_NAME = "name";
        String TAXT_PER = "percentage";
    }

   interface ATTCHMENT {

        String ATTACHMENT_OBJ="attachment_types";
        String ATTACH_ID="id";
        String ATTACH_NAME="name";
}



}