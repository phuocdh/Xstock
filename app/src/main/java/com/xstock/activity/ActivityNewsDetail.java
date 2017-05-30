package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.xstock.R;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetNewsHeaderDetail;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvGetNewsHeaderDetail;
import com.xstock.utils.Utils;

import java.util.ArrayList;

public class ActivityNewsDetail extends Activity {
    WebView wvNewsDetail;
    TextView txtNewsTitleDetail;
    TextView txtNewsCreateDateDetail;
    TextView txtNewsAuthorDetail;
    TextView txtNewsSummaryDetail;
    TextView txtNewsCreatorDetail;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_detail_item);
        this.mContext = this;
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.blue));

        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.back_button,
                null);

        RippleView rvBackButton = (RippleView) mCustomView
                .findViewById(R.id.rvBackButton);
        rvBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getActionBar().setCustomView(mCustomView);
        txtNewsTitleDetail = (TextView) findViewById(R.id.txtNewsTitleDetail);
        txtNewsCreateDateDetail = (TextView) findViewById(R.id.txtNewsCreateDateDetail);
        txtNewsAuthorDetail = (TextView) findViewById(R.id.txtNewsAuthorDetail);
        txtNewsSummaryDetail = (TextView) findViewById(R.id.txtNewsSummaryDetail);
        txtNewsCreatorDetail = (TextView) findViewById(R.id.txtNewsCreatorDetail);
        wvNewsDetail = (WebView) findViewById(R.id.wvNewsDetail);
        wvNewsDetail.getSettings().setLoadWithOverviewMode(true);
        wvNewsDetail.getSettings().setUseWideViewPort(true);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            wvNewsDetail.getSettings().setTextZoom(200);
        } else {
            wvNewsDetail.getSettings().setTextZoom(300);
        }
        Bundle bundle = getIntent().getExtras();
        String newsID = bundle.getString(Constant.BUNDLE_NEWS_ID);
        new AsyncGetNewsHeaderDetail().execute(newsID);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private class AsyncGetNewsHeaderDetail extends
            AsyncTask<String, Void, ArrayList<GetNewsHeaderDetail>> {

        @Override
        protected ArrayList<GetNewsHeaderDetail> doInBackground(String... params) {
            SessionManager session = new SessionManager(getApplicationContext());
            String token = session.GetPrefToken();
            ArrayList<GetNewsHeaderDetail> lstGetNewsHeaderDetail =
                    SrvGetNewsHeaderDetail.GetNewsHeaderDetail(token, params[0]);
            return lstGetNewsHeaderDetail;
        }

        @Override
        protected void onPostExecute(ArrayList<GetNewsHeaderDetail> lstObject) {
            if (lstObject != null) {
                txtNewsTitleDetail.setText(lstObject.get(0).getTitle());
                txtNewsCreateDateDetail.setText(Constant.NEWS_CREATE_DATE + lstObject.get(0).getCreated());
                txtNewsAuthorDetail.setText(Constant.NEWS_AUTHOR + lstObject.get(0).getAuthor());
                txtNewsSummaryDetail.setText(lstObject.get(0).getSummary());
                txtNewsCreatorDetail.setText(Constant.NEWS_CREATOR + lstObject.get(0).getAuthor());
                wvNewsDetail.clearCache(true);
                String mimeType = "text/html; charset=UTF-8";
                String encoding = "utf-8";
                wvNewsDetail.setWebViewClient(new MyWebViewClient());
                WebSettings webSettings = wvNewsDetail.getSettings();
                webSettings.setJavaScriptEnabled(true);
                String strHtml = lstObject.get(0).getDetail();
                wvNewsDetail.loadData(strHtml, mimeType, encoding);
            }
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(mContext);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    @Override
    public void onBackPressed() {
        if (wvNewsDetail.canGoBack()) {
            wvNewsDetail.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            Utils.hideLoadingDialog();
        }
    }
}
