package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.xstock.R;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.service.SrvGetPushDeviceStatus;
import com.xstock.service.SrvOnOffPushDevice;
import com.xstock.utils.Utils;

public class FragmentSettings extends Fragment {

    public static final String TAG = FragmentSettings.class.getSimpleName();
    FragmentSettingsCommunicator activityCommunicator;
    SwitchButton swReceiveMessage;
    SessionManager session;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        this.context = getContext();
        swReceiveMessage = (SwitchButton) v.findViewById(R.id.swReceiveMesage);
        session = new SessionManager(getContext());
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_settings), View.INVISIBLE);
        new AsyncGetSetting().execute();
        swReceiveMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isPush;
                if (swReceiveMessage.isChecked() == true) {
                    isPush = 1;
                } else {
                    isPush = 0;
                }
                new AsyncSetting().execute(isPush);
            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentSettingsCommunicator) getActivity();
    }

    public interface FragmentSettingsCommunicator {
        void passDataToActivity(String str, int visible);
    }

    private class AsyncGetSetting extends
            AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            return SrvGetPushDeviceStatus.GetPushDeviceStatus(session.GetPrefToken(), session.GetPrefDeviceToken());
        }

        @Override
        protected void onPostExecute(Integer isPush) {
            swReceiveMessage.setChecked(isPush == 1 ? true : false);
            Utils.hideLoadingDialog();
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(context);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private class AsyncSetting extends
            AsyncTask<Integer, Void, String> {

        @Override
        protected String doInBackground(Integer... params) {
            String token = session.GetPrefToken();
            String deviceToken = session.GetPrefDeviceToken();
            return SrvOnOffPushDevice.OnOffPushDevice(token, deviceToken, params[0]);
        }

        @Override
        protected void onPostExecute(String sPush) {
            Utils.hideLoadingDialog();
            if(!sPush.equals("OK")){
                Common.ShowToast(context, Constant.MSG_ERROR, Toast.LENGTH_LONG);
            }
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(context);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
