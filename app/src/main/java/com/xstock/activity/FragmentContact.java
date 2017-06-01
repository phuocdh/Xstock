package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
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

import com.xstock.R;
import com.xstock.app.AppConfig;
import com.xstock.utils.Utils;

public class FragmentContact extends Fragment {
    public static final String TAG = FragmentContact.class.getSimpleName();
    FragmentContactCommunicator activityCommunicator;
    private WebView wvContact;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        this.mContext = getContext();
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_contact), View.INVISIBLE);
        wvContact = (WebView) v.findViewById(R.id.wvContact);
        new AsyncContact().execute();
        activityCommunicator.onBackData(wvContact);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentContactCommunicator) getActivity();
    }

    public interface FragmentContactCommunicator {
        void passDataToActivity(String str, int visible);
        void onBackData(WebView wv);
    }

    private class AsyncContact extends
            AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return "";
        }

        @Override
        protected void onPostExecute(String str) {
            wvContact.clearCache(true);
            wvContact.setWebViewClient(new MyWebViewClient());
            WebSettings webSettings = wvContact.getSettings();
            webSettings.setJavaScriptEnabled(true);
            String strHtml = AppConfig.XSTOCK_CONTACT;
            wvContact.loadUrl(strHtml);
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(mContext);
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
            Utils.hideLoadingDialog();
        }
    }
}
