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
import com.xstock.adapter.PriceTableAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetDataPrice;
import com.xstock.service.SrvGetPriceTable;
import com.xstock.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentPriceTable extends Fragment {
    public static final String TAG = FragmentPriceTable.class.getSimpleName();
    PriceTableAdapter priceTableAdapter;
    private Context context;
    FragmentPriceTableCommunicator activityCommunicator;
    ListView lvPriceTable;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price_table, container, false);
        lvPriceTable = (ListView) v.findViewById(R.id.lstPriceTable);
        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        this.context = getContext();
        new AsyncGetDataPrice().execute();
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_price_table), View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
                new AsyncGetDataPrice().execute();
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
        activityCommunicator = (FragmentPriceTableCommunicator) getActivity();
    }

    public interface FragmentPriceTableCommunicator {
        public void passDataToActivity(String str, int visible);
    }

    private class AsyncGetDataPrice extends
            AsyncTask<String, Void, ArrayList<GetDataPrice>> {

        @Override
        protected ArrayList<GetDataPrice> doInBackground(String... params) {
            ArrayList<GetDataPrice> lstGetDataPrice = new ArrayList<GetDataPrice>();
            SessionManager session = new SessionManager(getActivity().getApplicationContext());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            lstGetDataPrice = SrvGetPriceTable.GetPriceTable(session.GetPrefToken(), date);
            return lstGetDataPrice;
        }

        @Override
        protected void onPostExecute(ArrayList<GetDataPrice> lstObject) {
            priceTableAdapter = new PriceTableAdapter(context, lstObject);
            lvPriceTable.setAdapter(priceTableAdapter);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ActivityMain)getContext()).clearFragmentByTag(TAG);
    }
}
