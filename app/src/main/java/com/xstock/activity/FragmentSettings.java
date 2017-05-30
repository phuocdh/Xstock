package com.xstock.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyleduo.switchbutton.SwitchButton;
import com.xstock.R;
import com.xstock.helper.SessionManager;
import com.xstock.service.SrvOnOffPushDevice;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class FragmentSettings extends Fragment {

    public static final String TAG = FragmentSettings.class.getSimpleName();
    FragmentSettingsCommunicator activityCommunicator;
    SwitchButton swReceiveMesage;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        swReceiveMesage = (SwitchButton) v.findViewById(R.id.swReceiveMesage);
        session = new SessionManager(getContext());
        swReceiveMesage.setChecked(session.GetPrefOnOff());

        if (swReceiveMesage.isChecked() == true) {
            new ThreadSetting(1).run();
        } else {
            new ThreadSetting(0).run();
        }

        swReceiveMesage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isPush;
                if (swReceiveMesage.isChecked() == true) {
                    isPush = 1;
                } else {
                    isPush = 0;
                }
                session.SetPrefOnOff(swReceiveMesage.isChecked());
                new ThreadSetting(isPush).run();
            }
        });
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_settings), View.INVISIBLE);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentSettingsCommunicator) getActivity();
    }

    public interface FragmentSettingsCommunicator {
        public void passDataToActivity(String str, int visible);
    }


    public class ThreadSetting extends Thread {
        int isPush = 0;

        public ThreadSetting(int isPush) {
            this.isPush = isPush;
        }

        // overriden from Runnable, which Thread implements
        public void run() {
            String token = session.GetPrefToken();
            String deviceToken = session.GetPrefDeviceToken();
            SrvOnOffPushDevice.OnOffPushDevice(token, deviceToken, isPush);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ActivityMain)getContext()).clearFragmentByTag(TAG);
    }
}
