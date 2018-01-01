package com.napt.studentregister.ui.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.napt.studentregister.R;
import com.napt.studentregister.databinding.StudentRegisterBinding;
import com.napt.studentregister.ui._base.BaseActivity;

import javax.inject.Inject;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,RegisterView {

    StudentRegisterBinding binding;

    @Inject
    Register_i_Presenter<RegisterView> presenter;


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
        presenter.setStaticFields(binding);

        //request attachment
        presenter.requestAttachment();



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

      }
      if(id==binding.attachbuttn.getId())
      {

      }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
