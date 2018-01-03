package com.napt.studentregister.ui.register;

import com.napt.studentregister.cf.data.DataManager;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.di.service.RestBuilderPro;
import com.napt.studentregister.ui._base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sreelal on 19/12/17.
 */

public class RegisterPresenter<T extends RegisterView> extends BasePresenter<T> implements Register_i_Presenter<T> {

    @Inject
    public RegisterPresenter(DataManager context) {
        super(context);
    }

    @Override
    public void requestAttachment() {

        if(getDataManager().getAttach()==null)

        {
            RestBuilderPro.getService().attachment_call().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful())

                        try {
                            String resp = response.body().string();
                             getDataManager().setAttach(resp);
                        } catch (Exception e) {
                            e.printStackTrace();
                            getView().showRetry(1);
                        }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    getView().showRetry(1);
                }
            });
        }
    }

    @Override
    public void callback(int type) {
        requestAttachment();
    }




    private List<Name_Id> Shirt_SizeListItems()

    {

      List<Name_Id> _shirtsize = new ArrayList<>();
        _shirtsize.clear();

        _shirtsize.add(new Name_Id("Select Size", ""));
        _shirtsize.add(new Name_Id("S", "1"));
        _shirtsize.add(new Name_Id("M", "2"));
        _shirtsize.add(new Name_Id("L", "3"));
        _shirtsize.add(new Name_Id("XL", "4"));
        _shirtsize.add(new Name_Id("XXL", "5"));
       return _shirtsize;

    }
    private List<Name_Id> Education_ListItems() {
      List<Name_Id>  educationlist = new ArrayList<>();
        educationlist.clear();
        educationlist.add(new Name_Id("Select", ""));
        educationlist.add(new Name_Id("10th", "1"));
        educationlist.add(new Name_Id("12th", "2"));
        educationlist.add(new Name_Id("Degree", "3"));
     return educationlist ;

    }


}
