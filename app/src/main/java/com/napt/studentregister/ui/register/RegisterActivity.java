package com.napt.studentregister.ui.register;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.napt.studentregister.BuildConfig;
import com.napt.studentregister.R;
import com.napt.studentregister.cf.adapter.OneField_Adapter;
import com.napt.studentregister.cf.contant.Api_Resp;
import com.napt.studentregister.cf.contant.Constant;
import com.napt.studentregister.cf.helper.PermissionHelper;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.databinding.StudentRegisterBinding;
import com.napt.studentregister.ui._base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterView {

    StudentRegisterBinding binding;

    @Inject
    Register_i_Presenter<RegisterView> presenter;

    private File tempFile;
    private Uri imageUri;
    private final static int camera_request = 12;
    private List<Name_Id> attachm_list;
    private OneField_Adapter attachAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.student_register);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        binding.datelayout.setOnClickListener(this);
        binding.submit.setOnClickListener(this);
        binding.profpic.setOnClickListener(this);
        binding.attachbuttn.setOnClickListener(this);
        setStaticFields(binding);

        //request attachment
        presenter.requestAttachment();


    }

    private void setStaticFields(StudentRegisterBinding binding) {


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == binding.datelayout.getId()) {

        }
        if (id == binding.submit.getId()) {

        }
        if (id == binding.profpic.getId()) {
            permission();

        }
        if (id == binding.attachbuttn.getId()) {
            attachmentShow();
        }


    }

    private void permission() {
        if (Build.VERSION.SDK_INT >= 23) {

            permissionHelper.request(new PermissionHelper.PermissionCallback() {
                @Override
                public void onPermissionGranted() {
                    cameraClick();
                }

                @Override
                public void onPermissionDenied() {
                }

                @Override
                public void onPermissionDeniedBySystem() {

                }
            });
        } else {
            cameraClick();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }


    @Override
    public void cameraClick() {
        tempFile = Constant.tempFilepath();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            imageUri = Uri.fromFile(tempFile);
        } else {
            imageUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", tempFile);
        }
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, camera_request);


    }

    @Override
    public void attachmentShow() {
        attachAdapter = new OneField_Adapter(this, getAttchmentList(), true);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ListView listView = new ListView(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(lparams);
        listView.setPadding(20,0,10,5);
        listView.setAdapter(attachAdapter);
        layout.addView(listView);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(layout);
        dialog.setCancelable(true);
        dialog.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();

            }
        });



/*

        String attachment =

        try {
            JSONObject jsonobj = new JSONObject(attachment);
            JSONArray jsonary = jsonobj.getJSONArray(ATTACH_API.OUTPUT.ATTACHMENT_OBJ);

            List<Attach_Items> ulist = new ArrayList<>();
            for (int i = 0; i < jsonary.length(); i++) {

                JSONObject json = jsonary.getJSONObject(i);
                int id = json.getInt(ATTACH_API.OUTPUT.ATTACH_ID);
                if (id != 4) {
                    Attach_Items item = new Attach_Items();
                    item.setType_id(json.getInt(ATTACH_API.OUTPUT.ATTACH_ID));
                    item.setType_name(json.getString(ATTACH_API.OUTPUT.ATTACH_NAME));

                    att_list.add(item);
                }

            }
            if (sIndex.size() != 0) {
                for (int z = 0; z < sIndex.size(); z++) {

                    int po = getPosition(att_list, sIndex.get(z));

                    att_list.remove(po);
                }
            }
            AttachAdapter dataAdapterx = new AttachAdapter(HomeActivityStudent.this, att_list);
            listview.setAdapter(dataAdapterx);
            listview.setOnItemClickListener(new ItemClick());
            if (
                    sIndex.size() < 3) {
                dialog.show();
            } else {
                showsnackbar("Attachment limit 3");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }

    @Override
    public void datepickShow() {

    }

    public List<Name_Id> getAttchmentList() {
        attachm_list = new ArrayList<>();
        String attachment = dataManager.getAttach();

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
}
