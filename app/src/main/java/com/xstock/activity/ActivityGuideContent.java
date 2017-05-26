package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.xstock.R;
import com.xstock.helper.SessionManager;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvGetHelp;


public class ActivityGuideContent extends Activity {

    private Context context;
    AVLoadingIndicatorView avLoading;
    TextView tvGuideContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
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
        context = this.getApplicationContext();
        tvGuideContent = (TextView) findViewById(R.id.tvGuideContent);
        avLoading = (AVLoadingIndicatorView) findViewById(R.id.avGuideContent);
        String id = getIntent().getStringExtra("GUIDE_ID");
        new AsyncGetHelp(id).execute();
    }

    private class AsyncGetHelp extends
            AsyncTask<String, Void, String> {
        String id;

        public AsyncGetHelp(String id) {
            this.id = id;
        }

        @Override
        protected String doInBackground(String... params) {
            String guideContent = "";

            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            guideContent = SrvGetHelp.GetHelp(token, id);
            return guideContent;
        }

        @Override
        protected void onPostExecute(String guideContent) {
            tvGuideContent.setText(guideContent);
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
}
