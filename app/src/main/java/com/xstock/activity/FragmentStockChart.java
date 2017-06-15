package com.xstock.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xstock.R;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.rippleview.RippleView;
import com.xstock.service.SrvCheckTrialLicence;
import com.xstock.utils.Utils;

public class FragmentStockChart extends Fragment {
    public static final String TAG = FragmentStockChart.class.getSimpleName();
    private Context context;
    FragmentStockChartCommunicator activityCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stock_chart, container, false);
        context = this.getContext();
        RippleView btn_x24basic = (RippleView) v.findViewById(R.id.btn_x24basic);
        RippleView btn_x24basic_plus = (RippleView) v.findViewById(R.id.btn_x24basic_plus);
        RippleView btn_x24traders = (RippleView) v.findViewById(R.id.btn_x24traders);
        RippleView btn_x24traders_plus = (RippleView) v.findViewById(R.id.btn_x24traders_plus);
        RippleView btn_x24plus = (RippleView) v.findViewById(R.id.btn_x24plus);
        final SessionManager session = new SessionManager(context);

        btn_x24basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (session.GetPrefGroupID() == 5) {
                    Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                    activityX24Basic.putExtra("TRADE_TYPE", 1);
                    startActivity(activityX24Basic);
                }
                if (session.GetPrefGroupID() == 1
                        || (session.GetPrefX24BasicLicense() && (session.GetPrefGroupID() == 2 || session.GetPrefGroupID() == 3
                        || session.GetPrefGroupID() == 3 || session.GetPrefGroupID() == 5 || session.GetPrefGroupID() == 6))
                        || (session.GetPrefX24TradersLicense() && (session.GetPrefGroupID() == 2 || session.GetPrefGroupID() == 3
                        || session.GetPrefGroupID() == 4 || session.GetPrefGroupID() == 5 || session.GetPrefGroupID() == 6))
                        || (session.GetPrefX24PlusLicense() && (session.GetPrefGroupID() == 2 || session.GetPrefGroupID() == 3
                        || session.GetPrefGroupID() == 6 || session.GetPrefGroupID() == 5 || session.GetPrefGroupID() == 6))) {
                    Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                    activityX24Basic.putExtra("TRADE_TYPE", 1);
                    startActivity(activityX24Basic);
                } else {
                    Common.ShowToast(context, String.format(Constant.MSG_LICENSE, ""), Toast.LENGTH_LONG);
                }
            }
        });

        btn_x24basic_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 4);
                startActivity(activityX24Basic);
            }
        });

        btn_x24traders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 2);
                startActivity(activityX24Basic);
            }
        });

        btn_x24traders_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 5);
                startActivity(activityX24Basic);
            }
        });

        btn_x24plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
                activityX24Basic.putExtra("TRADE_TYPE", 3);
                startActivity(activityX24Basic);
            }
        });

        activityCommunicator.passDataToActivity(getResources().getString(R.string.item_stock_chart), View.INVISIBLE);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activityCommunicator = (FragmentStockChartCommunicator) getActivity();
    }

    public interface FragmentStockChartCommunicator {
        void passDataToActivity(String str, int visible);
    }

    private class AsyncTrialLicence extends
            AsyncTask<Integer, Void, String> {
        int tradeType = 0;

        @Override
        protected String doInBackground(Integer... params) {
            tradeType = params[0];
            SessionManager session = new SessionManager(context);
            String token = session.GetPrefToken();
            return SrvCheckTrialLicence.CheckTrialLicence(token);
        }

        @Override
        protected void onPostExecute(String sTrialLicence) {
            String msg = "";

//            switch (tradeType) {
//                case 1:
//                    Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
//                    activityX24Basic.putExtra("TRADE_TYPE", 1);
//                    startActivity(activityX24Basic);
//                    break;
//                case 2:
//                    tvTitleX24.setText(R.string.txt_X24_traders);
//                    rvX24BasicChart.setVisibility(View.VISIBLE);
//                    break;
//                case 3:
//                    tvTitleX24.setText(R.string.txt_X24_plus);
//                    rvX24BasicChart.setVisibility(View.INVISIBLE);
//                    break;
//                case 4:
//                    tvTitleX24.setText(R.string.txt_X24_basic_plus);
//                    rvX24BasicChart.setVisibility(View.INVISIBLE);
//                    break;
//                case 5:
//                    tvTitleX24.setText(R.string.txt_X24_traders_plus);
//                    rvX24BasicChart.setVisibility(View.INVISIBLE);
//                    break;
//                default:
//                    break;
//            }
//            Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
//            activityX24Basic.putExtra("TRADE_TYPE", tradeType);
//            startActivity(activityX24Basic);
//
//            if (sTrialLicence.equals("OK")) {
//                Intent activityX24Basic = new Intent(context, ActivityX24BasicMain.class);
//                activityX24Basic.putExtra("TRADE_TYPE", 1);
//                startActivity(activityX24Basic);
//            } else {
//
//                Common.ShowToast(context, Constant.MSG_ERROR, Toast.LENGTH_LONG);
//            }

            Utils.hideLoadingDialog();

            new AlertDialog.Builder(context)
                    .setTitle("Xstock.vn")
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }

        @Override
        protected void onPreExecute() {
            Utils.showLoadingDialog(context);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
