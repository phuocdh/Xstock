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

import com.xstock.R;
import com.xstock.adapter.ListHelpAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetListHelp;
import com.xstock.service.SrvGetListHelp;
import com.xstock.utils.Utils;

import java.util.ArrayList;


public class FragmentGuide extends Fragment {
    public static final String TAG = FragmentGuide.class.getSimpleName();
    FragmentGuideCommunicator activityCommunicator;
    ListView lvListGuide;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guide, container, false);
        context = getContext();
        lvListGuide = (ListView)v.findViewById(R.id.lvGuide);
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
