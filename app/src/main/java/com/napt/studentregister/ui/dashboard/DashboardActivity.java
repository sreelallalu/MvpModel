package com.napt.studentregister.ui.dashboard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.napt.studentregister.R;
import com.napt.studentregister.databinding.DashBoardBinding;
import com.napt.studentregister.ui._base.BaseActivity;
import com.napt.studentregister.ui.approvedlist.ApprovedListActivity;
import com.napt.studentregister.ui.duepayment.DuePaymentActivity;
import com.napt.studentregister.ui.register.RegisterActivity;
import com.napt.studentregister.ui.secondpayment.SecondPayemntActivity;
import com.napt.studentregister.ui.studentview.StudentViewActivity;

public class DashboardActivity extends BaseActivity implements View.OnClickListener {
    DashBoardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.dash_board);
        getActivityComponent().inject(this);

        binding.duelist.setOnClickListener(this);
        binding.paymentdetails.setOnClickListener(this);
        binding.submitapproval.setOnClickListener(this);
        binding.registration.setOnClickListener(this);
        binding.viewstudents.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == binding.registration.getId()) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        if (id == binding.viewstudents.getId()) {
            startActivity(new Intent(this, StudentViewActivity.class));
        }
        if (id == binding.submitapproval.getId()) {
            startActivity(new Intent(this, ApprovedListActivity.class));
        }
        if (id == binding.duelist.getId()) {
            startActivity(new Intent(this, DuePaymentActivity.class));
        }
        if (id == binding.paymentdetails.getId()) {
            startActivity(new Intent(this, SecondPayemntActivity.class));
        }

    }
}
