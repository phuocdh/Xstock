package com.xstock.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wang.avi.AVLoadingIndicatorView;
import com.xstock.R;
import com.xstock.app.AppConfig;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class FragmentWeb extends Fragment {
    AVLoadingIndicatorView avWebLoading;
    FragmentWebCommunicator activityCommunicator;
    WebView wvWebDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_web), View.INVISIBLE);

        avWebLoading = (AVLoadingIndicatorView) v.findViewById(R.id.avWebLoading);
        wvWebDetail = (WebView) v.findViewById(R.id.wvWebDetail);
        new AsyncWebLoading().execute();
        activityCommunicator.onBackData(wvWebDetail);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentWebCommunicator) getActivity();
    }

    public interface FragmentWebCommunicator {
        public void passDataToActivity(String str, int visible);

        public void onBackData(WebView wv);
    }

    private class AsyncWebLoading extends
            AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void object) {
            wvWebDetail.clearCache(true);
            wvWebDetail.setWebViewClient(new MyWebViewClient());
            WebSettings webSettings = wvWebDetail.getSettings();
            webSettings.setJavaScriptEnabled(true);
            String strHtml = AppConfig.XSTOCK_URL;
            wvWebDetail.loadUrl(strHtml);
        }

        @Override
        protected void onPreExecute() {
            avWebLoading.setVisibility(View.VISIBLE);
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
            avWebLoading.setVisibility(View.GONE);
        }
    }
}
