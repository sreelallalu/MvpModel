package com.napt.studentregister.ui.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;

import com.napt.studentregister.BuildConfig;
import com.napt.studentregister.R;
import com.napt.studentregister.cf.contant.Constant;
import com.napt.studentregister.cf.helper.PermissionHelper;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.databinding.StudentRegisterBinding;
import com.napt.studentregister.ui._base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,RegisterView {

    StudentRegisterBinding binding;

    @Inject
    Register_i_Presenter<RegisterView> presenter;

    private File tempFile;
    private Uri imageUri;
    private final static int camera_request=12;
    private List<Name_Id> attachm_list;
    /*@Singleton
    private SharedPresenter share;
*/

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

        //static values setting
        setStaticFields(binding);

        //request attachment
        presenter.requestAttachment();




    }

    private void setStaticFields(StudentRegisterBinding binding) {




    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
      if(id==binding.datelayout.getId())
      {

      }
      if(id==binding.submit.getId())
      {

      }
      if(id==binding.profpic.getId())
      {
          permission();

      }
      if(id==binding.attachbuttn.getId())
      {

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


        attachm_list = new ArrayList<>();
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
}
