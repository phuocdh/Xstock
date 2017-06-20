package com.xstock.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.xstock.R;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.UserDetail;
import com.xstock.realm.RealmController;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvAddDevice;
import com.xstock.service.SrvGetUserDetail;
import com.xstock.service.SrvGetUserProduct;
import com.xstock.utils.Utils;

import java.util.ArrayList;

import io.realm.Realm;

import static com.xstock.service.SrvCheckLogin.CheckServerLogin;

public class ActivityLogin extends Activity {

    private RippleView btnLogin;
    private RippleView btnRegister;
    private RippleView btnForgotPass;
    private EditText edtEmail;
    private EditText edtPassword;
    private Context mContext;
    private SessionManager session;
    private SwitchButton swSavePass;
    private Realm realm;
    private RealmController realmController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent intent = new Intent();
        intent.setAction("com.google.firebase.MESSAGING_EVENT");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        this.mContext = this;
        edtEmail = (EditText) findViewById(R.id.email);
        edtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (RippleView) findViewById(R.id.btnLogin);
        btnRegister = (RippleView) findViewById(R.id.btnRegister);
        btnForgotPass = (RippleView) findViewById(R.id.btnForgotPass);
        swSavePass = (SwitchButton) findViewById(R.id.swSavePass);
        session = new SessionManager(mContext);
        realm = Realm.getDefaultInstance();
        realmController = new RealmController();

        if (session.GetPrefSavePass() == true) {
            if (realmController.getUserDetail(realm).size() > 0) {
                String email = realmController.getUserDetail(realm).get(0).getEmail();
                String pass = realmController.getUserDetail(realm).get(0).getPassword();
                edtEmail.setText(email);
                edtPassword.setText(pass);
            }
            swSavePass.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (email.isEmpty()) {
                    edtEmail.setHintTextColor(getResources().getColor(R.color.red));
                    Common.ShowToast(mContext, getString(R.string.err_msg_email), Toast.LENGTH_LONG);
                    return;
                }

                if (password.isEmpty()) {
                    edtPassword.setHintTextColor(getResources().getColor(R.color.red));
                    Common.ShowToast(mContext, getString(R.string.err_msg_password), Toast.LENGTH_LONG);
                    return;
                }
                if (!email.isEmpty() && !password.isEmpty()) {
                    new AsyncLogin().execute(email, password);
                    edtPassword.setHintTextColor(getResources().getColor(R.color.input_login_hint));
                    edtEmail.setHintTextColor(getResources().getColor(R.color.input_login_hint));
                } else {
                    Common.ShowToast(mContext, Constant.MSG_TEXT_EMPTY, Toast.LENGTH_LONG);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent activityRegistry = new Intent(ActivityLogin.this, ActivityRegistry.class);
                startActivity(activityRegistry);
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent activityForgotPass = new Intent(ActivityLogin.this, ActivityForgotPass.class);
                startActivity(activityForgotPass);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit1:
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class AsyncLogin extends
            AsyncTask<String, Void, String> {
        ArrayList<UserDetail> lstGetUserDetail;

        @Override
        protected String doInBackground(String... params) {
            String token = CheckServerLogin(params[0], params[1]);
            return token;
        }

        @Override
        protected void onPostExecute(final String token) {

            if (token.trim().equals(Constant.TIMEOUT) == true) {
                Common.ShowToast(mContext, Constant.MSG_LOGIN_TIMEOUT, Toast.LENGTH_LONG);
            } else {
                if (token.trim().equals(Constant.RETURN_NG) == true || token.isEmpty() == true) {
                    Common.ShowToast(mContext, Constant.MSG_LOGIN_FAIL, Toast.LENGTH_LONG);
                } else {
                    lstGetUserDetail = SrvGetUserDetail.GetUserDetail(token);
                    if (lstGetUserDetail.get(0).getIsActive() == 0) {
                        Common.ShowToast(mContext, Constant.MSG_INACTIVE, Toast.LENGTH_LONG);
                    } else {
                        session.SetPrefGroupID(lstGetUserDetail.get(0).getGroupID());
                        session.SetPrefToken(token);
                        session.SetPrefSavePass(swSavePass.isChecked());
                        realmController.clearUserDetail(realm);

                        // Save UserDetail in db
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                // Add a UserDetail
                                UserDetail userDetail = realm.createObject(UserDetail.class);
                                userDetail.setEmail(edtEmail.getText().toString().trim());
                                userDetail.setPassword(edtPassword.getText().toString().trim());
                                userDetail.setUsername(lstGetUserDetail.get(0).getUsername());
                                userDetail.setFirstname(lstGetUserDetail.get(0).getFirstname());
                                userDetail.setLastname(lstGetUserDetail.get(0).getLastname());
                                userDetail.setSexid(lstGetUserDetail.get(0).getSexid());
                                userDetail.setIsActive(lstGetUserDetail.get(0).getIsActive());
                                userDetail.setGroupID(lstGetUserDetail.get(0).getGroupID());
                            }
                        });

                        SrvGetUserProduct.SetUserProductLicense(session, token);
                        SrvAddDevice.AddDevice(token, session.GetPrefDeviceToken());
                        Intent activityMain = new Intent(ActivityLogin.this, ActivityMain.class);
                        startActivity(activityMain);
                    }
                }
            }

            Utils.hideLoadingDialog();
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(mContext);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
