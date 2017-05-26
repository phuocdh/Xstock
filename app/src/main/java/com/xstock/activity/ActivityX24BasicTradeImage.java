package com.xstock.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dpizarro.autolabel.library.AutoLabelUI;
import com.wang.avi.AVLoadingIndicatorView;
import com.xstock.R;
import com.xstock.adapter.SwipeImageAdapter;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetImageTrade;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvGetHelpContent;
import com.xstock.service.SrvGetImageTrade;
import com.xstock.tab.SlidingTabLayout;
import com.xstock.tab.TabPagerAdapter;

import java.util.ArrayList;


/**
 * Created by PhuocDH on 7/8/2016.
 */

public class ActivityX24BasicTradeImage extends FragmentActivity {

    RippleView rvX24BasicSearch;
    private AutoLabelUI mAutoLabel;
    AVLoadingIndicatorView avLoading;
    Context context;

    ArrayList<GetImageTrade> lstGetImageTrade = new ArrayList<GetImageTrade>();
    RippleView rvX24BasicTradeImageDay, rvX24BasicTradeImageHour;
    int tradeType;
    String pathImage = "";
    String pathImageDay = "";
    String pathImageHour = "";
    ViewPager viewPager;
    SwipeImageAdapter adapterView;
    boolean isButtonDay = true;
    boolean isButtonHour = true;
    boolean isClickButton = false;
    private WebView wvTradeContent;
    private String[] tabs = {"Games", "Movies"};
    SlidingTabLayout tabLayout;
    TabPagerAdapter tabPagerAdapter;
    ListenerTradeImageDay itfTradeImageDay;
    ListenerTradeImageHour itfTradeImageHour;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x24_basic_trade_image);
        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this.getApplicationContext();
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(1);
        mAutoLabel = (AutoLabelUI) findViewById(R.id.lbvTradeImageSearchData);
        mAutoLabel.setBackgroundResource(R.drawable.round_corner_background);
        RippleView rvX24BasicBack = (RippleView) findViewById(R.id.rvX24BasicTradeImageBack);
//        rvX24BasicTradeImageDay = (RippleView) findViewById(R.id.rvX24BasicTradeImageDay);
//        rvX24BasicTradeImageHour = (RippleView) findViewById(R.id.rvX24BasicTradeImageHour);
        rvX24BasicSearch = (RippleView) findViewById(R.id.rvX24BasicTradeImageSearch);
        ImageButton imbSearch = (ImageButton) findViewById(R.id.imbX24BasicTradeImageSearch);
        avLoading = (AVLoadingIndicatorView) findViewById(R.id.avTradeImageLoading);
        wvTradeContent = (WebView) findViewById(R.id.wvTradeContent);
        String tabTitles[] = new String[]{getResources().getString(R.string.txt_X24_basic_trade_day),
                getResources().getString(R.string.txt_X24_basic_trade_hour)};
        tabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        tabPagerAdapter = new TabPagerAdapter(
                getSupportFragmentManager(), tabTitles);
        viewPager.setAdapter(tabPagerAdapter);
        tabLayout.setViewPager(viewPager, 0);
        tabLayout.setSelectedIndicatorColors(Color.WHITE);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setDistributeEvenly(true);


        tabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                if(position == 0){
                    itfTradeImageDay.onArticleTradeImageDay(pathImageDay);
                }else {
                    itfTradeImageHour.onArticleTradeImageHour(pathImageHour);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tradeType = getIntent().getIntExtra("TRADE_TYPE", 0);

        rvX24BasicBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAutoLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), ActivityItemSearch.class), 2);
            }
        });

        rvX24BasicSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAutoLabel.getLabels().size() > 0) {
                    new AsyncGetTradeImage().execute();
                } else {
                    Common.ShowToast(context, Constant.MSG_MISS_TRADE, Toast.LENGTH_SHORT);
                }
            }
        });

        imbSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), ActivityItemSearch.class), 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 2) {
            if (data != null) {
                String value = data.getStringExtra("SEARCH");
                int id = data.getIntExtra("ID", 0);
                mAutoLabel.clear();
                mAutoLabel.addLabel(value, id);
            }
        }
    }

    private class AsyncGetTradeImage extends
            AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            SessionManager session = new SessionManager(context);
            return session.GetPrefToken();
        }

        @Override
        protected void onPostExecute(String token) {
            String tradeName = mAutoLabel.getLabel(0).getText().toString();
            lstGetImageTrade = SrvGetImageTrade.GetImageTrade(token, tradeName);
            int count = lstGetImageTrade.size();

            for (int i = 0; i < count; i++) {
                pathImage = lstGetImageTrade.get(i).getImage_data();
                if (pathImage.equals("") == false) {
                    if (lstGetImageTrade.get(i).getImage_type().equals(Constant.TRADE_DAY) == true &&
                            Integer.parseInt(pathImage.substring(pathImage.length() - 5, pathImage.length() - 4)) == tradeType) {
                        pathImageDay = pathImage;
                    }
                    if (lstGetImageTrade.get(i).getImage_type().equals(Constant.TRADE_HOUR) == true &&
                            Integer.parseInt(pathImage.substring(pathImage.length() - 5, pathImage.length() - 4)) == tradeType) {
                        pathImageHour = pathImage;
                    }
                }
            }

            tabLayout.setViewPager(viewPager, pos);
            if(pos == 0){
                itfTradeImageDay.onArticleTradeImageDay(pathImageDay);
            }else {
                itfTradeImageHour.onArticleTradeImageHour(pathImageHour);
            }

            wvTradeContent.clearCache(true);
            String mimeType = "text/html; charset=UTF-8";
            String encoding = "utf-8";
            WebSettings webSettings = wvTradeContent.getSettings();
            webSettings.setJavaScriptEnabled(true);
            String strHtml = SrvGetHelpContent.GetHelpContent(token);
            wvTradeContent.loadData(strHtml, mimeType, encoding);
            avLoading.setVisibility(View.GONE);
        }

        @Override
        protected void onPreExecute() {
            avLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public interface ListenerTradeImageDay {
        public void onArticleTradeImageDay(String path);
    }

    public interface ListenerTradeImageHour {
        public void onArticleTradeImageHour(String path);
    }

    @Override
    public void onAttachFragment(Fragment activity) {

        super.onAttachFragment(activity);
        if (activity.toString().contains("FragmentTradeImageDay")) {
            // This makes sure that the container activity has implemented
            // the callback interface. If not, it throws an exception
            try {
                itfTradeImageDay = (ListenerTradeImageDay) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement FragmentTradeImageDay");
            }
        } else if (activity.toString().contains("FragmentTradeImageHour")) {
            try {
                itfTradeImageHour = (ListenerTradeImageHour) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement FragmentTradeImageHour");
            }
        }
    }
}
