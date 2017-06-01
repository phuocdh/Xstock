package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.xstock.R;
import com.xstock.adapter.IndexInfoAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetDataNganh;
import com.xstock.service.SrvGetDataNganh;
import com.xstock.utils.Utils;

import java.util.ArrayList;

public class FragmentIndexInfo extends Fragment {
    public static final String TAG = FragmentIndexInfo.class.getSimpleName();
    IndexInfoAdapter indexInfoAdapter;
    private Context context;
    FragmentIndexInfoCommunicator activityCommunicator;
    ListView lvGetDataNganh;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_index_info, container, false);
        lvGetDataNganh = (ListView) v.findViewById(R.id.lstIndexInfo);
        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        this.context = getContext();
        new AsyncGetDataNganh().execute();
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_index_info), View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
                new AsyncGetDataNganh().execute();
            }
        });
        // Configure the refreshing colors
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentIndexInfoCommunicator) getActivity();
    }

    public interface FragmentIndexInfoCommunicator {
        void passDataToActivity(String str, int visible);
    }

    private class AsyncGetDataNganh extends
            AsyncTask<String, Void, ArrayList<GetDataNganh>> {

        @Override
        protected ArrayList<GetDataNganh> doInBackground(String... params) {
            SessionManager session = new SessionManager(context);
            ArrayList<GetDataNganh> lstGetDataNganh = SrvGetDataNganh.GetDataNganh(session.GetPrefToken());
            return lstGetDataNganh;
        }

        @Override
        protected void onPostExecute(ArrayList<GetDataNganh> lstObject) {
            indexInfoAdapter = new IndexInfoAdapter(context, lstObject);
            lvGetDataNganh.setAdapter(indexInfoAdapter);
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
