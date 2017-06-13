package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xstock.R;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.realm.RealmController;
import com.xstock.rippleview.RippleView;

import java.util.List;

import io.realm.Realm;

public class FragmentStockChart extends Fragment {
    public static final String TAG = FragmentStockChart.class.getSimpleName();
    private Context context;
    FragmentStockChartCommunicator activityCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stock_chart, container, false);
        context = this.getContext();
        RippleView btn_x24basic = (RippleView) v.findViewById(R.id.btn_x24basic);
        RippleView btn_x24basic_plus = (RippleView) v.findViewById(R.id.btn_x24basic_plus);
        RippleView btn_x24traders = (RippleView) v.findViewById(R.id.btn_x24traders);
        RippleView btn_x24traders_plus = (RippleView) v.findViewById(R.id.btn_x24traders_plus);
        RippleView btn_x24plus = (RippleView) v.findViewById(R.id.btn_x24plus);
        final SessionManager session = new SessionManager(context);

        btn_x24basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.GetPrefGroupID() == 1 || (session.GetPrefGroupID() == 1 && session.GetPrefX24BasicLicense())
                        || (session.GetPrefGroupID() == 2 && session.GetPrefX24BasicLicense())
                        || (session.GetPrefGroupID() == 3 && session.GetPrefX24BasicLicense())
                        || (session.GetPrefGroupID() == 6 && session.GetPrefX24BasicLicense())) {
                    Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                    activityX24Basic.putExtra("TRADE_TYPE", 1);
                    startActivity(activityX24Basic);
                } else {
                    Common.ShowToast(context, Constant.MSG_LICENSE, Toast.LENGTH_LONG);
                }
            }
        });

        btn_x24basic_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 4);
                startActivity(activityX24Basic);
            }
        });

        btn_x24traders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 2);
                startActivity(activityX24Basic);
            }
        });

        btn_x24traders_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 5);
                startActivity(activityX24Basic);
            }
        });

        btn_x24plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 3);
                startActivity(activityX24Basic);
            }
        });

        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_stock_chart), View.INVISIBLE);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentStockChartCommunicator) getActivity();
    }

    public interface FragmentStockChartCommunicator {
        void passDataToActivity(String str, int visible);
    }
}
