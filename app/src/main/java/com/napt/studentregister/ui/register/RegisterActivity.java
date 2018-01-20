package com.napt.studentregister.ui.register;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.napt.studentregister.R;
import com.napt.studentregister.cf.model.Name_Id;
import com.napt.studentregister.databinding.StudentRegisterBinding;
import com.napt.studentregister.ui._base.BaseActivity;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterView, BaseActivity.cameraResult {

    StudentRegisterBinding binding;

    @Inject
    Register_i_Presenter<RegisterView> presenter;
    private List<Name_Id> attachment_list;
    private int attach_postion;
    private  final int camera_req=12,attach_req=13;





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
        setCameraSet(this);
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

            takePic(camera_req);

        }
        if (id == binding.attachbuttn.getId()) {
            attachmentShow();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }


    @Override
    public void attachmentShow() {
        presenter.attachmentShow();
      /*  attachment_list = presenter.attachment_list();
        oneFieldAdapter.setType(true,attachment_list);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ListView listView = new ListView(this);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(lparams);
        listView.setPadding(20, 0, 10, 5);
        listView.setAdapter(oneFieldAdapter);
        layout.addView(listView);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(layout);
        dialog.setCancelable(true);
        if (attachment_list.size() != 0) {
            dialog.show();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                attach_postion = position;
                takePic(attach_req);
            }
        });
*/
    }


    @Override
    public void camera_Callback(Uri uri, File file, int requestcode) {
        switch (requestcode) {
            case camera_req:
             binding.profpic.setImageBitmap(setImageSizeReduce(uri));
                break;
            case attach_req:
                if(attachment_list.size()!=0)
                {
                    attachment_list.remove(attach_postion);
                    oneFieldAdapter.notifyDataSetChanged();

                }
                break;
        }
    }
}
