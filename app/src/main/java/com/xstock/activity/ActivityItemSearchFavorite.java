package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.xstock.R;
import com.xstock.adapter.SearchViewAdapterFavorite;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetTradeListItem;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvAddUserTradeList;
import com.xstock.service.SrvGetTradeList;
import com.xstock.swipelistview.ListViewSwipeGesture;
import com.xstock.utils.Utils;

import java.util.ArrayList;

/**
 * Created by PhuocDH on 9/1/2016.
 */
public class ActivityItemSearchFavorite extends Activity implements SearchView.OnQueryTextListener {

    ListView lvTradeList;
    SearchViewAdapterFavorite searchAdapter;
    SearchView svSearchTradeList;
    private Context mContext;
    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_code_favorite);
        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = this;
        session = new SessionManager(mContext);
        svSearchTradeList = (SearchView) findViewById(R.id.svListSearchItemFavorite);
        lvTradeList = (ListView) findViewById(R.id.lvTradeListFavorite);
        RippleView rvSearchItemBack = (RippleView) findViewById(R.id.rvTradesBack);
        svSearchTradeList.setOnQueryTextListener(this);
        new AsyncGetTradeListItemFavorite().execute();

        ListViewSwipeGesture touchListener = new ListViewSwipeGesture(
                lvTradeList, swipeListener, this);
        touchListener.SwipeType = ListViewSwipeGesture.Dismiss;    //Set two options at background of list item
        touchListener.isType = ListViewSwipeGesture.Single;

        lvTradeList.setOnTouchListener(touchListener);
        svSearchTradeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svSearchTradeList.setIconified(false);
            }
        });
        rvSearchItemBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    ListViewSwipeGesture.TouchCallbacks swipeListener = new ListViewSwipeGesture.TouchCallbacks() {

        @Override
        public void FullSwipeListView(int position) {
            // TODO Auto-generated method stub
        }

        @Override
        public void HalfSwipeListView(int position) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            // TODO Auto-generated method stub
            for (int i : reverseSortedPositions) {
                new AsyncAddDeleteUserTradeList().execute(searchAdapter.getItem(i).getId(), searchAdapter.getItem(i).getName(), "0");
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

    private class AsyncGetTradeListItemFavorite extends
            AsyncTask<String, Void, ArrayList<GetTradeListItem>> {

        @Override
        protected ArrayList<GetTradeListItem> doInBackground(String... params) {
            String token = session.GetPrefToken();
            ArrayList<GetTradeListItem> lstGetTradeList = SrvGetTradeList.GetTradeList(token);
            return lstGetTradeList;
        }

        @Override
        protected void onPostExecute(final ArrayList<GetTradeListItem> lstGetTradeList) {
            searchAdapter = new SearchViewAdapterFavorite(mContext, lstGetTradeList, Constant.NOTE_FAVORITE_ADD);
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
            if (check.equals("OK") == true) {
                session.SetPrefCountTrade(session.GetPrefCountTrade() + 1);
                Common.ShowToast(mContext, Constant.MSG_ADD_SUCCESS, Toast.LENGTH_SHORT);
            } else {
                int countTrade = 5;
                if (session.GetPref5TradeLicense()) {
                    countTrade = 5;
                }
                if (session.GetPref10TradeLicense()) {
                    countTrade += 10;
                }
                if (session.GetPref15TradeLicense()) {
                    countTrade += 15;
                }
                if (session.GetPrefGroupID() != 1) {
                    if (session.GetPrefCountTrade() == countTrade) {
                        Common.ShowToast(mContext, Constant.MSG_TRADE_LICENSE, Toast.LENGTH_SHORT);
                    } else {
                        Common.ShowToast(mContext, Constant.MSG_ADD_FAIL, Toast.LENGTH_SHORT);
                    }
                } else {
                    Common.ShowToast(mContext, Constant.MSG_ADD_FAIL, Toast.LENGTH_SHORT);
                }
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
