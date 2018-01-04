package com.napt.studentregister.ui.login;

import com.napt.studentregister.R;
import com.napt.studentregister.cf.data.DataManager;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.cf.model.db.LocalBody;
import com.napt.studentregister.di.service.RestBuilderPro;
import com.napt.studentregister.ui._base.BasePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.CENTER_ID;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.DISTRICT;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.DISTRICT_ID;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.DISTRICT_OBJ;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCALBODY;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCALBODY_ID;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCALBODY_OBJ;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCALBODY_TYPE;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCALBODY_TYPE_ID;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCALBODY_TYPE_OBJ;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCAL_DISTRICT_ID;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.LOCAL_TYPE_ID;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.STATUS;
import static com.napt.studentregister.cf.contant.Api_Resp.CENTERLOGIN.USER_ID;

/**
 * Created by sreelal on 15/12/17.
 */

public class LoginPresenter<T extends LoginView> extends BasePresenter<T> implements Login_i_Presenter<T> {


    @Inject
    public LoginPresenter(DataManager context) {

        super(context);
    }

    @Override
    public void call(String name, String pass) {
        getView().loading(false, 10, "Loading...");
        RestBuilderPro.getService().login_call(name, pass).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {

                        String respo = response.body().string();
                        response(respo);

                    } catch (Exception e) {
                        e.printStackTrace();
                        getView().showMessage(R.string.Somethingwent);
                    }
                }
                getView().loading(true, 101, "Login");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                getView().loading(true, 101, "Login");
                getView().showSnackBar(t.getMessage());
            }
        });
    }

    private void response(String respo) {
        try {
            JSONObject jsonObject = new JSONObject(respo);
            int status = jsonObject.getInt(STATUS);

            if (status == 1) {

                int centerid = jsonObject.getInt(CENTER_ID);
                int userId = jsonObject.getInt(USER_ID);

                getDataManager().setCentre_UserID(userId + "");
                getDataManager().setCentreID(centerid + "", true);

                JSONArray jsonArray = jsonObject.getJSONArray(DISTRICT_OBJ);
                List<Name_Id> list_district = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject js = jsonArray.getJSONObject(i);
                    String district = js.getString(DISTRICT);
                    String district_id = js.getString(DISTRICT_ID);
                    list_district.add(new Name_Id(district, district_id));
                }
                JSONArray jsonArray_type = jsonObject.getJSONArray(LOCALBODY_TYPE_OBJ);
                List<Name_Id> list_state = new ArrayList<>();
                for (int i = 0; i < jsonArray_type.length(); i++) {
                    JSONObject js1 = jsonArray_type.getJSONObject(i);
                    String LBTypeid = js1.getString(LOCALBODY_TYPE_ID);
                    String LBTypeName_eng = js1.getString(LOCALBODY_TYPE);
                    list_state.add(new Name_Id(LBTypeName_eng, LBTypeid));
                }
                JSONArray jsonArray_Name = jsonObject.getJSONArray(LOCALBODY_OBJ);
                List<LocalBody> list_name = new ArrayList<>();

                for (int i = 0; i < jsonArray_Name.length(); i++) {
                    JSONObject jsonObject1 = jsonArray_Name.getJSONObject(i);
                    String lb_id = jsonObject1.getString(LOCALBODY_ID);
                    String lb_type = jsonObject1.getString(LOCAL_TYPE_ID);
                    String lb_name_eng = jsonObject1.getString(LOCALBODY);
                    String districtcd = jsonObject1.getString(LOCAL_DISTRICT_ID);
                    list_name.add(new LocalBody(lb_id, lb_type, lb_name_eng, districtcd));
                }
                getDataManager().setDistrict(list_district);
                getDataManager().setLocalBodyType(list_state);
                getDataManager().setLocalBodyName(list_name);
                getView().onNext();

            } else {
                getView().snakbarFixed(R.string.invalid_cntr_pass);
            }


        } catch (JSONException e) {
            e.printStackTrace();

            getView().snakbarFixed((R.string.Json_format_Err));
        }
    }


}
