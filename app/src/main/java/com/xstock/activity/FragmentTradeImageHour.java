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


public class FragmentTradeImageHour extends Fragment implements ActivityX24BasicTradeImage.ListenerTradeImageHour {

    ImageView imvTradeImageHour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trade_image_hour, container, false);
        imvTradeImageHour = (ImageView) v.findViewById(R.id.imvTradeImageHour);
        return v;
    }

    @Override
    public void onArticleTradeImageHour(String path) {
        if (path.equals("") == true) {
            Picasso.with(getContext()).load(R.drawable.none).into(imvTradeImageHour);
        } else {

            Picasso.with(getContext()).load(AppConfig.XSTOCK_URL_STOCK_IMAGE + path).into(imvTradeImageHour);
        }
    }
}
