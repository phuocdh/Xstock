package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wang.avi.AVLoadingIndicatorView;
import com.xstock.R;
import com.xstock.adapter.PriceTableAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetDataPrice;
import com.xstock.service.SrvGetPriceTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FragmentPriceTable extends Fragment {

    PriceTableAdapter priceTableAdapter;
    private Context context;
    FragmentPriceTableCommunicator activityCommunicator;
    ListView lvPriceTable;
    AVLoadingIndicatorView avLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price_table, container, false);
        avLoading = (AVLoadingIndicatorView) v.findViewById(R.id.avPriceTableLoading);
        lvPriceTable = (ListView) v.findViewById(R.id.lstPriceTable);
        this.context = getContext();
        new AsyncGetDataPrice().execute();
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_price_table), View.INVISIBLE);
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
