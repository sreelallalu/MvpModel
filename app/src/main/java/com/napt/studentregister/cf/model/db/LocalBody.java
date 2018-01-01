package com.napt.studentregister.cf.model.db;

/**
 * Created by sreelal on 16/12/17.
 */

public class LocalBody {
    String lb_id;
    String lb_type;
    String lb_name_eng;
    String districtcd;

    public LocalBody() {
    }

    public LocalBody(String lb_id, String lb_name_eng) {
        this.lb_id = lb_id;
        this.lb_name_eng = lb_name_eng;
    }

    public LocalBody(String lb_id, String lb_type, String lb_name_eng, String districtcd) {
        this.lb_id = lb_id;
        this.lb_type = lb_type;
        this.lb_name_eng = lb_name_eng;
        this.districtcd = districtcd;
    }

    public String getLb_id() {
        return lb_id;
    }

    public void setLb_id(String lb_id) {
        this.lb_id = lb_id;
    }

    public String getLb_type() {
        return lb_type;
    }

    public void setLb_type(String lb_type) {
        this.lb_type = lb_type;
    }

    public String getLb_name_eng() {
        return lb_name_eng;
    }

    public void setLb_name_eng(String lb_name_eng) {
        this.lb_name_eng = lb_name_eng;
    }

    public String getDistrictcd() {
        return districtcd;
    }

    public void setDistrictcd(String districtcd) {
        this.districtcd = districtcd;
    }

}
