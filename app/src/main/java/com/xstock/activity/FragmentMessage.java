package com.xstock.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xstock.R;
import com.xstock.adapter.MessageListAdapter;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetDetailMessage;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvDeleteMessage;
import com.xstock.service.SrvGetDetailMessage;
import com.xstock.service.SrvGetMessageList;
import com.xstock.swipelistview.ListViewSwipeGesture;
import com.xstock.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FragmentMessage extends Fragment {
    public static final String TAG = FragmentMessage.class.getSimpleName();
    MessageListAdapter msgAdapter;
    ListView lvMessageList;
    private Context context;
    protected boolean loading = false;
    protected View footerView;
    ArrayList<GetDetailMessage> lstGetDetailMessage;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_message, container, false);
        context = this.getContext();
        lvMessageList = (ListView) v.findViewById(R.id.lvMessageList);
        swipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefresh);
        new AsyncGetDetailMessager().execute();
        ListViewSwipeGesture touchListener = new ListViewSwipeGesture(
                lvMessageList, swipeListener, getActivity());
        touchListener.SwipeType = ListViewSwipeGesture.Dismiss;    //Set two options at background of list item
        touchListener.isType = ListViewSwipeGesture.Dismiss;
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefresh.isRefreshing())
                    swipeRefresh.setRefreshing(false);
                new AsyncGetDetailMessager().execute();
            }
        });
        // Configure the refreshing colors
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        lvMessageList.setOnTouchListener(touchListener);
        lvMessageList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                // nothing here
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (msgAdapter != null && visibleItemCount > 0) {
                    int currentPage = totalItemCount;
                    int mod = currentPage % 10;
                    if (mod == 0) {
                        currentPage = currentPage / 10;
                    } else {
                        currentPage = (currentPage / 10) + 1;
                    }

                    int totalPages = 8;
                    if (firstVisibleItem + visibleItemCount == totalItemCount &&
                            lvMessageList.getChildAt(visibleItemCount - 1) != null &&
                            lvMessageList.getChildAt(visibleItemCount - 1).getBottom() <= lvMessageList.getHeight() &&
                            totalItemCount / totalPages >= 1 &&
                            loading == false && currentPage <= totalPages) {
                        loading = true;
                        lvMessageList.addFooterView(footerView, null, false);
                        new LoadNextPage().execute(currentPage);
                    }
                }
            }
        });

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
        public void LoadDataForScroll(int count) {
            // TODO Auto-generated method stub
            Toast.makeText(getContext(), "LoadDataForScroll", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onDismiss(ListView listView, int[] reverseSortedPositions) {
            // TODO Auto-generated method stub
            for (int i : reverseSortedPositions) {
                new ThreadAddDeleteUserTradeList(msgAdapter.getItem(i).getId(), i).run();
                msgAdapter.lstGetDetailMessage.remove(i);
                msgAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void OnClickListView(int position) {
            // TODO Auto-generated method stub
            new ThreadLoadDetailMesage(msgAdapter.getItem(position).getId()).run();
        }

    };

    private void ShowDialogLogin(String str) {
        LayoutInflater linf = LayoutInflater.from(context);
        final View inflator = linf.inflate(R.layout.message_detail_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog loadWaitDialog = builder.create();
        loadWaitDialog.setView(inflator);
        TextView dialogMsgDetail = (TextView) inflator
                .findViewById(R.id.dialog_mesage_detail_title);
        dialogMsgDetail.setText(str);
        RippleView btOK = (RippleView) inflator
                .findViewById(R.id.dialog_mesage_detail_button);
        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWaitDialog.cancel();
            }
        });

        loadWaitDialog.show();
    }

    public class ThreadLoadDetailMesage extends Thread {
        String id = "";

        public ThreadLoadDetailMesage(String id) {
            this.id = id;
        }

        // overriden from Runnable, which Thread implements
        public void run() {
            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            ShowDialogLogin(SrvGetDetailMessage.GetDetailMessage(token, id));
        }
    }

    protected class LoadNextPage extends AsyncTask<Integer, Void, List<GetDetailMessage>> {
        @Override
        protected List<GetDetailMessage> doInBackground(Integer... page) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            SessionManager session = new SessionManager(context);
            List<GetDetailMessage> newData = SrvGetMessageList.GetMessageHeader(session.GetPrefToken(), page[0]);
            return newData;
        }

        @Override
        protected void onPostExecute(List<GetDetailMessage> newData) {
            msgAdapter.lstGetDetailMessage.addAll(newData);
            msgAdapter.notifyDataSetChanged();
            lvMessageList.removeFooterView(footerView);
            loading = false;
        }
    }

    private class AsyncGetDetailMessager extends
            AsyncTask<String, Void, ArrayList<GetDetailMessage>> {

        @Override
        protected ArrayList<GetDetailMessage> doInBackground(String... params) {
            lstGetDetailMessage = new ArrayList<>();
            SessionManager session = new SessionManager(context);
            lstGetDetailMessage = SrvGetMessageList.GetMessageHeader(session.GetPrefToken(), 1);
            return lstGetDetailMessage;
        }

        @Override
        protected void onPostExecute(final ArrayList<GetDetailMessage> lstObject) {

            footerView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.paging_loading, null, false);
            lvMessageList.addFooterView(footerView, null, false);
            msgAdapter = new MessageListAdapter(context, lstObject);
            lvMessageList.setAdapter(msgAdapter);
            lvMessageList.removeFooterView(footerView);
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
        String id = "";
        int postiton = 0;

        public ThreadAddDeleteUserTradeList(String id, int postiton) {
            this.id = id;
            this.postiton = postiton;
        }

        // overriden from Runnable, which Thread implements
        public void run() {
            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            final String check = SrvDeleteMessage.DeleteMessageList(token, id);
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (check.equals("OK") == true) {
                        Common.ShowToast(context, Constant.MSG_DELETE_MSG_SUCCESS, Toast.LENGTH_SHORT);
                    } else {
                        Common.ShowToast(context, Constant.MSG_DELETE_MSG_FAIL, Toast.LENGTH_SHORT);
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ActivityMain)getContext()).clearFragmentByTag(TAG);
    }
}
