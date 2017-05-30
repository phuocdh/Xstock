package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xstock.R;
import com.xstock.rippleview.RippleView;

public class FragmentStockChart extends Fragment {
    public static final String TAG = FragmentStockChart.class.getSimpleName();
    private Context context;
    FragmentStockChartCommunicator activityCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stock_chart, container, false);
        context = this.getContext();
        RippleView btn_x24basic = (RippleView)v.findViewById(R.id.btn_x24basic);
        RippleView btn_x24basic_plus = (RippleView)v.findViewById(R.id.btn_x24basic_plus);
        RippleView btn_x24traders = (RippleView)v.findViewById(R.id.btn_x24traders);
        RippleView btn_x24traders_plus = (RippleView)v.findViewById(R.id.btn_x24traders_plus);
        RippleView btn_x24plus = (RippleView)v.findViewById(R.id.btn_x24plus);

        btn_x24basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent activityX24Basic = new Intent(getContext(), ActivityX24BasicMain.class);
            activityX24Basic.putExtra("TRADE_TYPE", 1);
            startActivity(activityX24Basic);
            }
        });

        btn_x24basic_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(getContext(), ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 4);
                startActivity(activityX24Basic);
            }
        });

        btn_x24traders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(getContext(), ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 2);
                startActivity(activityX24Basic);
            }
        });

        btn_x24traders_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(getContext(), ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 5);
                startActivity(activityX24Basic);
            }
        });

        btn_x24plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(getContext(), ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 3);
                startActivity(activityX24Basic);
            }
        });

        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_stock_chart),View.INVISIBLE);
        return v;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        activityCommunicator =(FragmentStockChartCommunicator)getActivity();
    }

    public interface FragmentStockChartCommunicator{
        void passDataToActivity(String str, int visible);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ActivityMain)getContext()).clearFragmentByTag(TAG);
    }
}
