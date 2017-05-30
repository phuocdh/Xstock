/*
 * Copyright (C) 2012 Daniel Medina <http://danielme.com>
 * 
 * This file is part of "Android Paginated ListView Demo".
 * 
 * "Android Paginated ListView Demo" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * "Android Paginated ListView Demo" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License version 3
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-3.0.html/>
 */

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
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.xstock.R;
import com.xstock.adapter.NewsAdapter;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetNewsHeader;
import com.xstock.service.SrvGetNewsHeader;
import com.xstock.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FragmentNews extends Fragment {
    public static final String TAG = FragmentNews.class.getSimpleName();
    ListView lstNews;
    private Context context;
    protected boolean loading = false;
    protected View footerView;
    NewsAdapter pagingArrayAdapter;
    ArrayList<GetNewsHeader> lstGetNewsHeader;
    InterfaceNews interfaceNew;
    FragmentNewsCommunicator activityCommunicator;
    int totalPages = 0;
    int lastPage = 0;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news, container, false);
        interfaceNew = (InterfaceNews) this.getActivity();
        interfaceNew.SetTitle(getResources().getString(R.string.item_news));
        lstNews = (ListView) v.findViewById(R.id.lstNews);
        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        this.context = getContext();

        new AsyncGetNewsHeader().execute();
        lstNews.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                // nothing here
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (pagingArrayAdapter != null && visibleItemCount > 0) {
                    int currentPage = totalItemCount;
                    int mod = currentPage % 10;
                    if (mod == 0) {
                        currentPage = currentPage / 10;
                    } else {
                        currentPage = (currentPage / 10) + 1;
                    }

                    if (firstVisibleItem + visibleItemCount == totalItemCount &&
                            lstNews.getChildAt(visibleItemCount - 1) != null &&
                            lstNews.getChildAt(visibleItemCount - 1).getBottom() <= lstNews.getHeight() &&
                            lastPage < totalPages &&
                            loading == false && currentPage <= totalPages) {
                        loading = true;
                        lstNews.addFooterView(footerView, null, false);
                        if (totalItemCount % 10 == 0) {
                            lastPage = currentPage + 1;
                        } else {
                            lastPage = currentPage;
                        }
                        new LoadNextPage().execute(lastPage);

                    }
                }
            }
        });

        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_news), View.INVISIBLE);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
                new AsyncGetNewsHeader().execute();
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
        activityCommunicator = (FragmentNewsCommunicator) getActivity();
    }

    public interface FragmentNewsCommunicator {
        public void passDataToActivity(String str, int visible);
    }

    protected class LoadNextPage extends AsyncTask<Integer, Void, String> {
        private List<GetNewsHeader> newData = null;

        @Override
        protected String doInBackground(Integer... page) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            SessionManager session = new SessionManager(context);
            newData = SrvGetNewsHeader.GetNewsHeader(session.GetPrefToken(), page[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pagingArrayAdapter.lstGetNewsHeader.addAll(newData);
            pagingArrayAdapter.notifyDataSetChanged();
            lstNews.removeFooterView(footerView);
            loading = false;
        }

    }

    private class AsyncGetNewsHeader extends
            AsyncTask<String, Void, ArrayList<GetNewsHeader>> {

        @Override
        protected ArrayList<GetNewsHeader> doInBackground(String... params) {
            lstGetNewsHeader = new ArrayList<GetNewsHeader>();
            SessionManager session = new SessionManager(context);
            lstGetNewsHeader = SrvGetNewsHeader.GetNewsHeader(session.GetPrefToken(), 1);
            if (lstGetNewsHeader.size() > 0) {
                totalPages = lstGetNewsHeader.get(0).getTotalPages();
            }
            return lstGetNewsHeader;
        }

        @Override
        protected void onPostExecute(final ArrayList<GetNewsHeader> lstObject) {

            footerView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.paging_loading, null, false);
            lstNews.addFooterView(footerView, null, false);
            pagingArrayAdapter = new NewsAdapter(context, lstObject);
            lstNews.setAdapter(pagingArrayAdapter);
            lstNews.removeFooterView(footerView);
            Utils.hideLoadingDialog();
        }

        @Override
        protected void onPreExecute() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Utils.showLoadingDialog(context);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    public interface InterfaceNews {
        public void SetTitle(String title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ActivityMain)getContext()).clearFragmentByTag(TAG);
    }
}