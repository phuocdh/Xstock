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
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_code_favorite);
        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.context = this;
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


//            Toast.makeText(getContext(),searchAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void LoadDataForScroll(int count) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            // TODO Auto-generated method stub
//            Toast.makeText(getContext(),"Delete", Toast.LENGTH_SHORT).show();
            for (int i : reverseSortedPositions) {
                new ThreadAddDeleteUserTradeList(searchAdapter.getItem(i).getId(), searchAdapter.getItem(i).getName(), 0).run();
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
            ArrayList<GetTradeListItem> alstGetTradeList = new ArrayList<GetTradeListItem>();

            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            alstGetTradeList = SrvGetTradeList.GetTradeList(token);
            return alstGetTradeList;
        }

        @Override
        protected void onPostExecute(final ArrayList<GetTradeListItem> alstGetTradeList) {
            searchAdapter = new SearchViewAdapterFavorite(context, alstGetTradeList, Constant.NOTE_FAVORITE_ADD);
            lvTradeList.setAdapter(searchAdapter);
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

    public class ThreadAddDeleteUserTradeList extends Thread {
        String tradeid = "";
        String tradeName = "";
        int modify = 0;

        public ThreadAddDeleteUserTradeList(String tradeid, String tradeName, int modify) {
            this.tradeid = tradeid;
            this.modify = modify;
            this.tradeName = tradeName;
        }

        // overriden from Runnable, which Thread implements
        public void run() {
            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            final String check = SrvAddUserTradeList.AddDeleteUserTradeList(token, tradeid, tradeName, modify);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (check.equals("OK") == true) {
                        Common.ShowToast(context, Constant.MSG_ADD_SUCCESS, Toast.LENGTH_SHORT);
                    } else {
                        Common.ShowToast(context, Constant.MSG_ADD_FAIL, Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    }
}
