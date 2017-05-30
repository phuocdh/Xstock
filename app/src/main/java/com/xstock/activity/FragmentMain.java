package com.xstock.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xstock.R;
import com.xstock.rippleview.RippleView;

public class FragmentMain extends Fragment {
    public static final String TAG = FragmentMain.class.getSimpleName();
    FragmentMainCommunicator activityCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        RippleView btn_item_stock_chart = (RippleView) v.findViewById(R.id.btn_item_stock_chart);
        RippleView btn_item_index_info = (RippleView) v.findViewById(R.id.btn_item_index_info);
        RippleView btn_item_news = (RippleView) v.findViewById(R.id.btn_item_news);
        RippleView btn_item_price_table = (RippleView) v.findViewById(R.id.btn_item_price_table);
        RippleView btn_item_message = (RippleView) v.findViewById(R.id.btn_item_message);
        RippleView btn_item_favorite = (RippleView) v.findViewById(R.id.btn_item_favorite);
        RippleView btn_item_web = (RippleView) v.findViewById(R.id.btn_item_web);
        RippleView btn_item_guide = (RippleView) v.findViewById(R.id.btn_item_guide);
        RippleView btn_item_settings = (RippleView) v.findViewById(R.id.btn_item_settings);
        RippleView btn_item_contact = (RippleView) v.findViewById(R.id.btn_item_contact);
        RippleView btn_item_logout = (RippleView) v.findViewById(R.id.btn_item_logout);

        btn_item_stock_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentStockChart(), getResources().getString(R.string.item_stock_chart), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_index_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentIndexInfo(), getResources().getString(R.string.item_index_info), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentNews(), getResources().getString(R.string.item_news), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_price_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentPriceTable(), getResources().getString(R.string.item_price_table), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentMessage(), getResources().getString(R.string.item_messages), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentListCode(), getResources().getString(R.string.item_messages), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentWeb(), getResources().getString(R.string.item_web), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentGuide(), getResources().getString(R.string.item_guide), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentSettings(), getResources().getString(R.string.item_settings), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityCommunicator.passDataToActivity(new FragmentContact(), getResources().getString(R.string.item_contact), View.INVISIBLE, FragmentStockChart.TAG);
            }
        });

        btn_item_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        activityCommunicator.passDataToActivity(getResources().getString(R.string.app_name), View.INVISIBLE);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentMainCommunicator) getActivity();
    }

    public interface FragmentMainCommunicator {
        void passDataToActivity(String str, int visible);
        void passDataToActivity(Fragment targetFragment, String str, int visiable, String tag);
    }
}
