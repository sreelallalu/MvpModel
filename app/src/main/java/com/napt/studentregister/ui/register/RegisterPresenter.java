package com.napt.studentregister.ui.register;

import android.app.Dialog;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.napt.studentregister.cf.adapter.OneField_Adapter;
import com.napt.studentregister.cf.contant.Api_Resp;
import com.napt.studentregister.cf.data.DataManager;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.di.service.RestBuilderPro;
import com.napt.studentregister.ui._base.BaseActivity;
import com.napt.studentregister.ui._base.BasePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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

public class RegisterPresenter<T extends RegisterView> extends BasePresenter<T> implements Register_i_Presenter<T> ,BaseActivity.cameraResult{


    @Inject
    OneField_Adapter adapter;

    @Inject
    Dialog dialog;
    private List<Name_Id> attachment_list;
    private int tempposition=-1;


    @Inject
    public RegisterPresenter(DataManager context) {
        super(context);
    }

    @Override
    public void requestAttachment() {

        if (getDataManager().getAttach() == null)

        {
            RestBuilderPro.getService().attachment_call().enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful())

                        try {
                            String resp = response.body().string();
                            getDataManager().setAttach(resp);
                            attachment();
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
        }else{
            attachment();
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
        List<Name_Id> educationlist = new ArrayList<>();
        educationlist.clear();
        educationlist.add(new Name_Id("Select", ""));
        educationlist.add(new Name_Id("10th", "1"));
        educationlist.add(new Name_Id("12th", "2"));
        educationlist.add(new Name_Id("Degree", "3"));
        return educationlist;

    }

    @Override
    public List<Name_Id> attachment_list() {
        List<Name_Id> attachm_list = new ArrayList<>();
        String attachment = getDataManager().getAttach();
        if (attachment == null) {
            requestAttachment();
            return null;
        }
        try {
            JSONObject jsonobj = new JSONObject(attachment);
            JSONArray jsonary = jsonobj.getJSONArray(Api_Resp.ATTCHMENT.ATTACHMENT_OBJ);

            for (int i = 0; i < jsonary.length(); i++) {
                JSONObject json = jsonary.getJSONObject(i);
                int id = json.getInt(Api_Resp.ATTCHMENT.ATTACH_ID);
                if (id != 4) {
                    attachm_list.add(new Name_Id(json.getString(Api_Resp.ATTCHMENT.ATTACH_NAME), "" + json.getInt(Api_Resp.ATTCHMENT.ATTACH_ID)));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return attachm_list;

    }

    @Override
    public void attachment() {
        getView().setCameraSet(this);
        attachment_list = attachment_list();
        if(attachment_list==null||attachment_list.size()==0){return;}
         adapter.setType(true, attachment_list);
        LinearLayout layout = new LinearLayout(getDataManager().getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ListView listView = new ListView(getDataManager().getContext());
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(lparams);
        listView.setPadding(20, 0, 10, 5);
        listView.setAdapter(adapter);
        layout.addView(listView);
        dialog.setContentView(layout);
        dialog.setCancelable(true);


       /* if (attachment_list.size() != 0) {
            dialog.show();
        }
*/
      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();

                getView().takePic(13);
            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                tempposition = position;
                getView().takePic(13);
            }
        });
    }

    @Override
    public void attachmentShow() {
        adapter.notifyDataSetChanged();
        if(attachment_list.size()!=0)
            dialog.show();
    }


    @Override
    public void camera_Callback(Uri uri, File file, int requestcode) {
            if(tempposition!=-1&&attachment_list.size()!=0)
            {
                attachment_list.remove(tempposition);
                adapter.notifyDataSetChanged();
            }
    }
}
