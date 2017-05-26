package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.xstock.R;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvCheckRegistry;

public class ActivityRegistry extends Activity {

    private RippleView btnRegister;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtRePassword;
    private EditText edtMobile;
    private AVLoadingIndicatorView avLoading;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.blue));

        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.back_button,
                null);

        RippleView rvBackButton = (RippleView) mCustomView
                .findViewById(R.id.rvBackButton);
        rvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getActionBar().setCustomView(mCustomView);
        context = getApplicationContext();
        edtEmail = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        edtRePassword = (EditText) findViewById(R.id.repassword);
        edtMobile = (EditText) findViewById(R.id.mobile);
        btnRegister = (RippleView) findViewById(R.id.btnRgRegister);
        avLoading = (AVLoadingIndicatorView) findViewById(R.id.registry_loading);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String mobile = edtMobile.getText().toString();
                String pass = edtPassword.getText().toString();
                String repass = edtRePassword.getText().toString();

                if (email.trim().isEmpty() || pass.trim().isEmpty() || repass.trim().isEmpty() || mobile.trim().isEmpty()) {
                    Common.ShowToast(context, Constant.MSG_RG_EMPTY, Toast.LENGTH_SHORT);
                    return;
                }

                if (pass.trim().equals(repass) == false) {
                    Common.ShowToast(context, Constant.MSG_REPASS, Toast.LENGTH_SHORT);
                    return;
                }
                new AsyncRegistry().execute();
            }
        });
    }


    private class AsyncRegistry extends
            AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String email = edtEmail.getText().toString();
            String pass = edtPassword.getText().toString();
            String mobile = edtMobile.getText().toString();
            String str = SrvCheckRegistry.RegisterUser(email, pass, mobile);
            return str;
        }

        @Override
        protected void onPostExecute(String str) {
            if (str.equals("NG")) {
                Common.ShowToast(context, Constant.MSG_REGISTRY_FAIL, Toast.LENGTH_SHORT);
            } else {
                Common.ShowToast(context, Constant.MSG_REGISTRY_SUCCESS, Toast.LENGTH_SHORT);
            }
            avLoading.setVisibility(View.GONE);
        }

        @Override
        protected void onPreExecute() {
            avLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
