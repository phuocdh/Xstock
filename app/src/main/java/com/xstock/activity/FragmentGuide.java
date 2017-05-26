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
import com.xstock.adapter.ListHelpAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetListHelp;
import com.xstock.service.SrvGetListHelp;

import java.util.ArrayList;


public class FragmentGuide extends Fragment {

    FragmentGuideCommunicator activityCommunicator;
    AVLoadingIndicatorView avLoading;
    ListView lvListGuide;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guide, container, false);
        context = getContext();
        lvListGuide = (ListView)v.findViewById(R.id.lvGuide);
        avLoading = (AVLoadingIndicatorView)v.findViewById(R.id.avListGuide);
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_guide),View.INVISIBLE);
        new AsyncGetListHelp().execute();
        return v;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        activityCommunicator =(FragmentGuideCommunicator)getActivity();
    }

    public interface FragmentGuideCommunicator{
        public void passDataToActivity(String str, int visible);
    }

    private class AsyncGetListHelp extends
            AsyncTask<String, Void, ArrayList<GetListHelp>> {

        @Override
        protected ArrayList<GetListHelp> doInBackground(String... params) {
            ArrayList<GetListHelp> alstGetListHelp = new ArrayList<GetListHelp>();

            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            alstGetListHelp = SrvGetListHelp.GetListHelp(token);
            return alstGetListHelp;
        }

        @Override
        protected void onPostExecute(final ArrayList<GetListHelp> alstGetListHelp) {
            ListHelpAdapter getListHelpAdapter = new ListHelpAdapter(context, alstGetListHelp);
            lvListGuide.setAdapter(getListHelpAdapter);
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
