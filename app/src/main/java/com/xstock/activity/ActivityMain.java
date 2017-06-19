package com.xstock.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xstock.R;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.realm.RealmController;
import com.xstock.residemenu.ResideMenu;
import com.xstock.residemenu.ResideMenuItem;
import com.xstock.service.SrvCheckTrialLicence;
import com.xstock.utils.Utils;

import io.realm.Realm;

/**
 * Created by PhuocDH on 7/8/2016.
 */

public class ActivityMain extends FragmentActivity implements View.OnClickListener,
        FragmentNews.InterfaceNews, FragmentListCode.FragmentListCodeCommunicator, FragmentMain.FragmentMainCommunicator, FragmentStockChart.FragmentStockChartCommunicator,
        FragmentPriceTable.FragmentPriceTableCommunicator, FragmentIndexInfo.FragmentIndexInfoCommunicator, FragmentNews.FragmentNewsCommunicator, FragmentContact.FragmentContactCommunicator, FragmentWeb.FragmentWebCommunicator,
        FragmentGuide.FragmentGuideCommunicator, FragmentSettings.FragmentSettingsCommunicator {

    private ResideMenu resideMenu;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemStockChart;
    private ResideMenuItem itemPriceTable;
    private ResideMenuItem itemIndexInfo;
    private ResideMenuItem itemMessages;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemListCode;
    private ResideMenuItem itemWeb;
    private ResideMenuItem itemContact;
    private ResideMenuItem itemGuide;
    private ResideMenuItem itemSetting;
    private ResideMenuItem itemLogOut;
    private TextView txtTitleBar;
    private Button btTrades;
    private Context context;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;
        session = new SessionManager(context);
        getActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txtTitleBar = (TextView) findViewById(R.id.txtTitleBar);
        btTrades = (Button) findViewById(R.id.btTrades);
        btTrades.setVisibility(View.INVISIBLE);

        setUpMenu();
        if (savedInstanceState == null) {
            changeFragment(new FragmentMain(), getResources().getString(R.string.app_name), View.INVISIBLE, FragmentMain.TAG);
        }

        btTrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityItemSearchFavorite = new Intent(ActivityMain.this, ActivityItemSearchFavorite.class);
                startActivity(activityItemSearchFavorite);
            }
        });
    }

    private void setUpMenu() {

        // attach to current activity;
        Realm realm = Realm.getDefaultInstance();
        RealmController realmController = new RealmController();
        String name = realmController.getUserDetail(realm).get(0).getEmail();

        resideMenu = new ResideMenu(context, name);
        resideMenu.setUse3D(true);
//        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.setBackground(R.color.blue);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;

        itemHome = new ResideMenuItem(this, R.drawable.ic_item_home, R.string.item_home);
        itemStockChart = new ResideMenuItem(this, R.drawable.ic_item_stock_chart, R.string.item_stock_chart);
        itemIndexInfo = new ResideMenuItem(this, R.drawable.ic_item_index_info, R.string.item_index_info);
        itemMessages = new ResideMenuItem(this, R.drawable.ic_item_message, R.string.item_messages);
        itemNews = new ResideMenuItem(this, R.drawable.ic_item_news, R.string.item_news);
        itemListCode = new ResideMenuItem(this, R.drawable.ic_item_favorite, R.string.item_favorite);
        itemPriceTable = new ResideMenuItem(this, R.drawable.ic_item_price_table, R.string.item_price_table);
        itemGuide = new ResideMenuItem(this, R.drawable.ic_item_guide, R.string.item_guide);
        itemWeb = new ResideMenuItem(this, R.drawable.ic_item_web, R.string.item_web);
        itemContact = new ResideMenuItem(this, R.drawable.ic_item_contact, R.string.item_contact);
        itemSetting = new ResideMenuItem(this, R.drawable.ic_item_settings, R.string.item_settings);
        itemLogOut = new ResideMenuItem(this, R.drawable.ic_item_logout, R.string.btn_logout);

        itemHome.setOnClickListener(this);
        itemStockChart.setOnClickListener(this);
        itemPriceTable.setOnClickListener(this);
        itemIndexInfo.setOnClickListener(this);
        itemMessages.setOnClickListener(this);
        itemNews.setOnClickListener(this);
        itemWeb.setOnClickListener(this);
        itemListCode.setOnClickListener(this);
        itemContact.setOnClickListener(this);
        itemGuide.setOnClickListener(this);
        itemSetting.setOnClickListener(this);
        itemLogOut.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemStockChart, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemListCode, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemPriceTable, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNews, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemIndexInfo, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemMessages, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemGuide, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemWeb, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemContact, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSetting, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLogOut, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        if (view == itemHome) {
            changeFragment(new FragmentMain(), getResources().getString(R.string.app_name), View.INVISIBLE, FragmentMain.TAG);
        } else if (view == itemStockChart) {
            changeFragment(new FragmentStockChart(), getResources().getString(R.string.item_stock_chart), View.INVISIBLE, FragmentStockChart.TAG);
        } else if (view == itemPriceTable) {
            changeFragment(new FragmentPriceTable(), getResources().getString(R.string.item_price_table), View.INVISIBLE, FragmentPriceTable.TAG);
        } else if (view == itemIndexInfo) {
            changeFragment(new FragmentIndexInfo(), getResources().getString(R.string.item_index_info), View.INVISIBLE, FragmentIndexInfo.TAG);
        } else if (view == itemMessages) {
            changeFragment(new FragmentMessage(), getResources().getString(R.string.item_messages), View.INVISIBLE, FragmentMessage.TAG);
        } else if (view == itemNews) {
            changeFragment(new FragmentNews(), getResources().getString(R.string.item_news), View.INVISIBLE, FragmentNews.TAG);
        } else if (view == itemListCode) {
            changeFragment(new FragmentListCode(), getResources().getString(R.string.item_favorite), View.VISIBLE, FragmentListCode.TAG);
        } else if (view == itemGuide) {
            changeFragment(new FragmentGuide(), getResources().getString(R.string.item_guide), View.INVISIBLE, FragmentGuide.TAG);
        } else if (view == itemContact) {
            changeFragment(new FragmentContact(), getResources().getString(R.string.item_contact), View.INVISIBLE, FragmentContact.TAG);
        } else if (view == itemWeb) {
            changeFragment(new FragmentWeb(), getResources().getString(R.string.item_web), View.INVISIBLE, FragmentWeb.TAG);
        } else if (view == itemSetting) {
            changeFragment(new FragmentSettings(), getResources().getString(R.string.item_settings), View.INVISIBLE, FragmentSettings.TAG);
        } else if (view == itemLogOut) {
            this.finish();
        }
        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
        }

        @Override
        public void closeMenu() {
        }
    };

    private void changeFragment(Fragment targetFragment, String text, int visible, String tag) {
        if (tag.equals(FragmentIndexInfo.TAG)) {
            if (session.GetPrefGroupID() == 5) {
                new AsyncTrialLicence().execute(targetFragment, text, visible, tag);
            } else if (session.GetPrefGroupID() == 1
                    || (session.GetPrefX24BasicLicense() && (session.GetPrefGroupID() == 2 || session.GetPrefGroupID() == 3
                    || session.GetPrefGroupID() == 4 || session.GetPrefGroupID() == 6))) {
                clearFragmentByTag(tag);
                txtTitleBar.setText(text);
                btTrades.setVisibility(visible);
                resideMenu.clearIgnoredViewList();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment, targetFragment, tag);
                fragmentTransaction.addToBackStack(tag);
                fragmentTransaction.commitAllowingStateLoss();
            } else {
                Common.ShowToast(context, String.format(Constant.MSG_LICENSE, "này"), Toast.LENGTH_LONG);
            }
        } else {
            if (!tag.equals(FragmentMain.TAG)) {
                clearFragmentByTag(tag);
            }
            txtTitleBar.setText(text);
            btTrades.setVisibility(visible);
            resideMenu.clearIgnoredViewList();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment, targetFragment, tag);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    private Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        return fragmentManager
                .findFragmentByTag(fragmentTag);
    }

    public void clearFragmentByTag(String tag) {
        try {
            FragmentManager fm = getSupportFragmentManager();

            for (int i = fm.getBackStackEntryCount() - 1; i >= 0; i--) {
                String backEntry = fm.getBackStackEntryAt(i).getName();
                if (backEntry.equals(tag) || backEntry.equals(FragmentMain.TAG)) {
                    break;
                } else {
                    fm.popBackStack();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SetTitle(String title) {
        txtTitleBar.setText(title);
    }

    @Override
    public void passDataToActivity(String str, int visible) {
        btTrades.setVisibility(visible);
        txtTitleBar.setText(str);
    }

    WebView wv;

    @Override
    public void onBackData(WebView wv) {
        this.wv = wv;
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() instanceof FragmentMain) {
            return;
        }
        if (wv != null) {
            if (this.wv.canGoBack()) {
                this.wv.goBack();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void passDataToActivity(Fragment targetFragment, String str, int visible, String tag) {
        changeFragment(targetFragment, str, visible, tag);
    }

    private class AsyncTrialLicence extends
            AsyncTask<Object, Void, String> {
        String text;
        Fragment targetFragment;
        int visible;
        String tag;

        @Override
        protected String doInBackground(Object... params) {
            targetFragment = (Fragment) params[0];
            text = (String) params[1];
            visible = (int) params[2];
            tag = (String) params[3];
            String token = session.GetPrefToken();
            return SrvCheckTrialLicence.CheckTrialLicence(token);
        }

        @Override
        protected void onPostExecute(String sTrialLicence) {
            Utils.hideLoadingDialog();
            if (sTrialLicence.equals("OK")) {
                clearFragmentByTag(tag);
                txtTitleBar.setText(text);
                btTrades.setVisibility(visible);
                resideMenu.clearIgnoredViewList();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment, targetFragment, tag);
                fragmentTransaction.addToBackStack(tag);
                fragmentTransaction.commitAllowingStateLoss();
                return;
            } else {
                if (sTrialLicence.isEmpty()) {
                    Common.ShowToast(context, Constant.MSG_ERROR, Toast.LENGTH_LONG);
                    return;
                }
            }

            new AlertDialog.Builder(context)
                    .setTitle("Xstock.vn")
                    .setMessage(String.format(Constant.MSG_TRIAL, sTrialLicence.split(";")))
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
