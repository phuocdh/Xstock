package com.xstock.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wang.avi.AVLoadingIndicatorView;
import com.xstock.R;
import com.xstock.app.AppConfig;
import com.xstock.rippleview.RippleView;

public class ActivityForgotPass extends Activity {
    AVLoadingIndicatorView avWebForgotPassLoading;
    WebView wvWebForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
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
                finish();
            }
        });
        getActionBar().setCustomView(mCustomView);

        avWebForgotPassLoading = (AVLoadingIndicatorView) findViewById(R.id.avWebForgotLoading);
        wvWebForgotPass = (WebView) findViewById(R.id.wvWebForgotPass);
        new AsyncWebForotPassLoading().execute();
    }

    private class AsyncWebForotPassLoading extends
            AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void object) {
            wvWebForgotPass.clearCache(true);
            String mimeType = "text/html; charset=UTF-8";
            String encoding = "utf-8";
            wvWebForgotPass.setWebViewClient(new MyWebViewClient());
            WebSettings webSettings = wvWebForgotPass.getSettings();
            webSettings.setJavaScriptEnabled(true);
            String strHtml = AppConfig.XSTOCK_FORGOT_PASS_URL;
            wvWebForgotPass.loadUrl(strHtml);
        }

        @Override
        protected void onPreExecute() {
            avWebForgotPassLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            avWebForgotPassLoading.setVisibility(View.GONE);
        }
    }
}
