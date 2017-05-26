package com.xstock.fcm;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.xstock.helper.SessionManager;

public class FCM_Instance_ID_Service extends FirebaseInstanceIdService {

    private static final String TAG = "AndroidDeviceToken";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        SessionManager session = new SessionManager(getApplicationContext());
        session.SetPrefDeviceToken(refreshedToken);
        Log.d(TAG, "Device token: " + refreshedToken);
    }


}
