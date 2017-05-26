package com.xstock.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.wang.avi.AVLoadingIndicatorView;
import com.xstock.R;
import com.xstock.adapter.SearchViewAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetTradeListItem;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvGetTradeList;

import java.util.ArrayList;

/**
 * Created by PhuocDH on 9/1/2016.
 */
public class ActivityItemSearch extends Activity implements SearchView.OnQueryTextListener {

    AVLoadingIndicatorView avLoading;
    ListView lvTradeList;
    SearchViewAdapter searchAdapter;
    SearchView svSearchTradeList;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search);
        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        context = this.getApplicationContext();
        svSearchTradeList = (SearchView) findViewById(R.id.svSearchItem);
        lvTradeList = (ListView) findViewById(R.id.lvItemSearch);
        RippleView rvSearchItemBack = (RippleView) findViewById(R.id.rvSearchItemBack);
        avLoading = (AVLoadingIndicatorView) findViewById(R.id.item_search_loading);
        svSearchTradeList.setOnQueryTextListener(this);
        new AsyncGetTradeListItem().execute();
        rvSearchItemBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        svSearchTradeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svSearchTradeList.setIconified(false);
            }
        });
    }

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

    private class AsyncGetTradeListItem extends
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
            searchAdapter = new SearchViewAdapter(context, alstGetTradeList);
            lvTradeList.setAdapter(searchAdapter);

            lvTradeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strSearchValue = alstGetTradeList.get(position).getName();
                Intent data = new Intent();
                data.putExtra("SEARCH", strSearchValue);
                data.putExtra("ID", position);
                setResult(RESULT_OK, data);
                finish();
                }
            });
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
