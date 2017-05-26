package com.xstock.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xstock.R;
import com.xstock.app.AppConfig;


public class FragmentTradeImageDay extends Fragment implements ActivityX24BasicTradeImage.ListenerTradeImageDay {

    ImageView imvTradeImageDay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trade_image_day, container, false);
        imvTradeImageDay = (ImageView) v.findViewById(R.id.imvTradeImageDay);

        return v;
    }

    @Override
    public void onArticleTradeImageDay(String path) {
        if (path.equals("") == true) {
            Picasso.with(getContext()).load(R.drawable.none).into(imvTradeImageDay);
        } else {

            Picasso.with(getContext()).load(AppConfig.XSTOCK_URL_STOCK_IMAGE + path).into(imvTradeImageDay);
        }
    }
}
