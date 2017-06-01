package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xstock.R;
import com.xstock.helper.SessionManager;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvGetHelp;
import com.xstock.utils.Utils;


public class ActivityGuideContent extends Activity {

    private Context context;
    TextView tvGuideContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        this.context = this;
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
        tvGuideContent = (TextView) findViewById(R.id.tvGuideContent);
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
            Utils.hideLoadingDialog();
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(context);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
