package com.napt.studentregister.ui.login;

import com.napt.studentregister.R;
import com.napt.studentregister.cf.contant.CENTERLOGIN_OUTPUT;
import com.napt.studentregister.cf.data.AppDataManager;
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
                getView().loading(true, 101, "Loading...");
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
            JSONObject jsonObject = new JSONObject(res);
            int status = jsonObject.getInt(CENTERLOGIN_OUTPUT.STATUS);

            if (status == 1) {

                int centerid = jsonObject.getInt(CENTERLOGIN_OUTPUT.CENTER_ID);
                int userId = jsonObject.getInt(CENTERLOGIN_OUTPUT.USER_ID);

                //sharedHelper.setCentre_UserID(userId+"");

                JSONArray jsonArray = jsonObject.getJSONArray(CENTERLOGIN_OUTPUT.DISTRICT_OBJ);
                List<Name_Id> list_district = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject js = jsonArray.getJSONObject(i);
                    String district = js.getString(CENTERLOGIN_OUTPUT.DISTRICT);
                    String district_id = js.getString(CENTERLOGIN_OUTPUT.DISTRICT_ID);
                    list_district.add(new Name_Id(district,district_id));
                }
                JSONArray jsonArray_type = jsonObject.getJSONArray(CENTERLOGIN_OUTPUT.LOCALBODY_TYPE_OBJ);
                List<Name_Id> list_state = new ArrayList<>();
                for (int i = 0; i < jsonArray_type.length(); i++) {
                    JSONObject js1 = jsonArray_type.getJSONObject(i);
                    String LBTypeid = js1.getString(CENTERLOGIN_OUTPUT.LOCALBODY_TYPE_ID);
                    String LBTypeName_eng = js1.getString(CENTERLOGIN_OUTPUT.LOCALBODY_TYPE);
                    list_state.add(new Name_Id(LBTypeName_eng,LBTypeid));
                }
                JSONArray jsonArray_Name = jsonObject.getJSONArray(CENTERLOGIN_OUTPUT.LOCALBODY_OBJ);
                List<LocalBody> list_name = new ArrayList<>();

                for (int i = 0; i < jsonArray_Name.length(); i++) {
                    JSONObject jsonObject1 = jsonArray_Name.getJSONObject(i);
                    String lb_id = jsonObject1.getString(CENTERLOGIN_OUTPUT.LOCALBODY_ID);
                    String lb_type = jsonObject1.getString(CENTERLOGIN_OUTPUT.LOCAL_TYPE_ID);
                    String lb_name_eng = jsonObject1.getString(CENTERLOGIN_OUTPUT.LOCALBODY);
                    String districtcd = jsonObject1.getString(CENTERLOGIN_OUTPUT.LOCAL_DISTRICT_ID);
                    list_name.add(new LocalBody(lb_id,lb_type,lb_name_eng,districtcd));
                }
                databse.setDistrict(list_district);
                databse.setLocalBodyType(list_state);
                databse.setLocalBodyName(list_name);
                onNext();

            } else {
                getView().showSnackBar("Invalid Center_ID or Password");
            }


        } catch (JSONException e) {
            e.printStackTrace();
            AppDataManager
           getView().showSnackBar(AppDataManager.class.getgetString(R.string.Json_format_Err));
        }
    }


}
