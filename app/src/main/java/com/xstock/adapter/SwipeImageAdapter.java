package com.xstock.adapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.xstock.R;
import com.xstock.app.AppConfig;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class SwipeImageAdapter extends PagerAdapter {

    Context mContext;
    String path;

    public SwipeImageAdapter(Context context, String path) {
        this.mContext = context;
        this.path = path;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (path.equals("") == true) {
            Picasso.with(mContext).load(R.drawable.none).into(mImageView);
        } else {

            Picasso.with(mContext).load(AppConfig.XSTOCK_URL_STOCK_IMAGE + path).into(mImageView);
        }
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}