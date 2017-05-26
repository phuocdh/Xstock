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
import com.xstock.adapter.IndexInfoAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetDataNganh;
import com.xstock.service.SrvGetDataNganh;

import java.util.ArrayList;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class FragmentIndexInfo extends Fragment {

    IndexInfoAdapter indexInfoAdapter;
    private Context context;
    FragmentIndexInfoCommunicator activityCommunicator;
    ListView lvGetDataNganh;
    AVLoadingIndicatorView avLoading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_index_info, container, false);
        avLoading = (AVLoadingIndicatorView) v.findViewById(R.id.index_info_loading);
        lvGetDataNganh = (ListView) v.findViewById(R.id.lstIndexInfo);
        this.context = getContext();
        new AsyncGetDataNganh().execute();
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_index_info),View.INVISIBLE);
        return v;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        activityCommunicator =(FragmentIndexInfoCommunicator)getActivity();
    }

    public interface FragmentIndexInfoCommunicator{
        public void passDataToActivity(String str, int visible);
    }

    private class AsyncGetDataNganh extends
            AsyncTask<String, Void, ArrayList<GetDataNganh>> {

        @Override
        protected ArrayList<GetDataNganh> doInBackground(String... params) {
            ArrayList<GetDataNganh> lstGetDataNganh = new ArrayList<GetDataNganh>();
            SessionManager session = new SessionManager(getActivity().getApplicationContext());
            lstGetDataNganh = SrvGetDataNganh.GetDataNganh(session.GetPrefToken());
            return lstGetDataNganh;
        }

        @Override
        protected void onPostExecute(ArrayList<GetDataNganh> lstObject) {

            indexInfoAdapter = new IndexInfoAdapter(context, lstObject);
            lvGetDataNganh.setAdapter(indexInfoAdapter);
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
