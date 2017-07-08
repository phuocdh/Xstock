package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.xstock.R;
import com.xstock.adapter.SearchViewAdapterFavorite;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetTradeListItem;
import com.xstock.service.SrvAddUserTradeList;
import com.xstock.service.SrvGetTradeList;
import com.xstock.swipelistview.ListViewSwipeGesture;
import com.xstock.utils.Utils;

import java.util.ArrayList;

public class FragmentListCode extends Fragment implements SearchView.OnQueryTextListener {
    public static final String TAG = FragmentListCode.class.getSimpleName();
    SearchViewAdapterFavorite searchAdapter;
    SearchView svSearchTradeList;
    private Context mContext;
    ListView lvTradeList;
    FragmentListCodeCommunicator activityCommunicator;
    SwipeRefreshLayout swipeRefresh;
    SessionManager session;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentListCodeCommunicator) getActivity();
    }

    public interface FragmentListCodeCommunicator {
        void passDataToActivity(String str, int visible);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_code, container, false);
        mContext = this.getContext();
        session = new SessionManager(mContext);
        svSearchTradeList = (SearchView) v.findViewById(R.id.svListSearchItem);
        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        lvTradeList = (ListView) v.findViewById(R.id.lvTradeList);
        svSearchTradeList.setOnQueryTextListener(this);
        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_favorite), View.VISIBLE);
        svSearchTradeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svSearchTradeList.setIconified(false);
            }
        });
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
                new AsyncGetTradeListItem().execute();
            }
        });
        // Configure the refreshing colors
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return v;
    }

    ListViewSwipeGesture.TouchCallbacks swipeListener = new ListViewSwipeGesture.TouchCallbacks() {

        @Override
        public void FullSwipeListView(int position) {
            // TODO Auto-generated method stub
            Toast.makeText(getContext(), "Action_2", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void HalfSwipeListView(int position) {
            // TODO Auto-generated method stub

//            Toast.makeText(getContext(),searchAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            // TODO Auto-generated method stub
            for (int i : reverseSortedPositions) {
                new AsyncAddDeleteUserTradeList().execute(searchAdapter.getItem(i).getId(), "", "1", i + "");
            }
        }

        @Override
        public void OnClickListView(int position) {
            // TODO Auto-generated method stub
//            startActivity(new Intent(getContext(),TestActivity.class));
        }

    };

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        if (searchAdapter != null) {
            searchAdapter.filter(text);
        }
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Common.ShowToast(getContext(), "FragmentListCode", Toast.LENGTH_SHORT);
    }

    @Override
    public void onResume() {
        super.onResume();
        new AsyncGetTradeListItem().execute();
        ListViewSwipeGesture touchListener = new ListViewSwipeGesture(
                lvTradeList, swipeListener, getActivity());
        touchListener.SwipeType = ListViewSwipeGesture.Dismiss;    //Set two options at background of list item
        touchListener.isType = ListViewSwipeGesture.Dismiss;
        lvTradeList.setOnTouchListener(touchListener);
    }

    private class AsyncGetTradeListItem extends
            AsyncTask<String, Void, ArrayList<GetTradeListItem>> {

        @Override
        protected ArrayList<GetTradeListItem> doInBackground(String... params) {
            String token = session.GetPrefToken();
            ArrayList<GetTradeListItem> alstGetTradeList = SrvGetTradeList.GetUserTradeList(token);
            return alstGetTradeList;
        }

        @Override
        protected void onPostExecute(final ArrayList<GetTradeListItem> alstGetTradeList) {
            searchAdapter = new SearchViewAdapterFavorite(mContext, alstGetTradeList, Constant.NOTE_FAVORITE_DELETE);
            lvTradeList.setAdapter(searchAdapter);
            Utils.hideLoadingDialog();
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(mContext);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private class AsyncAddDeleteUserTradeList extends
            AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {
            return params;
        }

        @Override
        protected void onPostExecute(String[] param) {
            String token = session.GetPrefToken();
            String check = SrvAddUserTradeList.AddDeleteUserTradeList(token, param[0], param[1], Integer.parseInt(param[2]));
            if (check.equals("OK")) {
                searchAdapter.getTradeListItem.remove(Integer.parseInt(param[3]));
                searchAdapter.arraylist.remove(Integer.parseInt(param[3]));
                searchAdapter.notifyDataSetChanged();
                Common.ShowToast(mContext, Constant.MSG_DELETE_SUCCESS, Toast.LENGTH_SHORT);
            } else {
                Common.ShowToast(mContext, Constant.MSG_DELETE_FAIL, Toast.LENGTH_SHORT);
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        session.SetPrefCountTrade(searchAdapter.getTradeListItem.size());
    }
}
