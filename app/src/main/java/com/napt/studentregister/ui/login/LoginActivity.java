package com.napt.studentregister.ui.login;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.napt.studentregister.R;
import com.napt.studentregister.databinding.LoginPageBinding;
import com.napt.studentregister.lib.flatbutton.bar.ActionProcessButton;
import com.napt.studentregister.ui._base.BaseActivity;
import com.napt.studentregister.ui.dashboard.DashboardActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {

    private LoginPageBinding binding;

    @Inject
    Login_i_Presenter<LoginView> presenter;

    Animation shake ;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.login_page);
        getActivityComponent().inject(this);
        binding.loginsubmit.setOnClickListener(this);
        binding.loginsubmit.setMode(ActionProcessButton.Mode.ENDLESS);
        presenter.onAttach(LoginActivity.this);
        shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == binding.loginsubmit.getId()) {
            Login(binding.loginid.getText().toString(), binding.loginpass.getText().toString());

        }
    }
    @Override
    public void Login(String name, String pass) {

        boolean check = true;
        if (name.isEmpty()) {
            check = false;
            binding.loginid.setError("invalid");
            binding.loginid.startAnimation(shake);
        }
        if (pass.isEmpty()) {
            check = false;
            binding.loginpass.setError("invalid");
            binding.loginpass.startAnimation(shake);
        }

        if (check) {
            presenter.call(name, pass);

        }

    }

    @Override
    public void onNext() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        finish();
    }





    @Override
    public void loading(boolean check, int value, String msg) {

        binding.loginsubmit.setProgress(value);
        binding.loginsubmit.setText(msg);
        binding.loginsubmit.setEnabled(check);
        binding.loginpass.setEnabled(check);
        binding.loginid.setEnabled(check);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
