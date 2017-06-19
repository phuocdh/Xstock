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
package com.xstock.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xstock.R;
import com.xstock.activity.ActivityNewsDetail;
import com.xstock.commons.Common;
import com.xstock.constants.Constant;
import com.xstock.helper.SessionManager;
import com.xstock.models.GetNewsHeader;
import com.xstock.realm.RealmController;
import com.xstock.service.SrvCheckTrialLicence;
import com.xstock.utils.Utils;
import com.xstock.viewBadger.BadgeView;

import java.util.List;

import io.realm.Realm;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class NewsAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _context;
    public List<GetNewsHeader> lstGetNewsHeader;
    boolean isPublic = false;
    int isActive = 0;
    SessionManager session;

    public NewsAdapter(Context context, List<GetNewsHeader> lstObject) {
        this.lstGetNewsHeader = lstObject;
        this._context = context;
        this.layoutInflater = LayoutInflater.from(_context);
        session = new SessionManager(_context);
        Realm realm = Realm.getDefaultInstance();
        RealmController realmController = new RealmController();
        isActive = realmController.getUserDetail(realm).get(0).getIsActive();
//        if (session.GetPrefGroupID() == 5) {

    }

    public class ViewHolder {
        TextView txtNewsTitle, txtNewsVip, txtNewsSummary;
        ImageView imgNewsImage;
        BadgeView bdNews;
        LinearLayout lnNews;
    }

    @Override
    public int getCount() {
        return lstGetNewsHeader.size();
    }

    @Override
    public GetNewsHeader getItem(int position) {
        return lstGetNewsHeader.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.fragment_news_item, null);
            holder.txtNewsTitle = (TextView) convertView.findViewById(R.id.txtNewsTitle);
            holder.txtNewsVip = (TextView) convertView.findViewById(R.id.txtNewsVip);
            holder.txtNewsSummary = (TextView) convertView.findViewById(R.id.txtNewsSummary);
            holder.imgNewsImage = (ImageView) convertView.findViewById(R.id.imgNewsImage);
            holder.bdNews = new BadgeView(_context, holder.txtNewsVip);
            holder.lnNews = (LinearLayout) convertView.findViewById(R.id.lnNews);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GetNewsHeader getNewsHeader = lstGetNewsHeader.get(position);
        isPublic = getNewsHeader.getPublish();

        if (isPublic == false) {
            holder.txtNewsVip.setText("Tin trả phí");
        } else {
            holder.txtNewsVip.setText("");
        }
        holder.txtNewsTitle.setText(getNewsHeader.getTitle());
        holder.txtNewsSummary.setText(getNewsHeader.getSummary());
        if (holder.imgNewsImage != null) {
            Picasso.with(_context).load(getNewsHeader.getCoverimage()).into(holder.imgNewsImage);
        }

        holder.lnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPublic = getNewsHeader.getPublish();
                if (!isPublic) {
                    if (session.GetPrefGroupID() == 5) {
                        new AsyncTrialLicence().execute(getNewsHeader.getId());
                    } else if (session.GetPrefGroupID() == 1 || (session.GetPrefNewsLicense() && (session.GetPrefGroupID() == 2 || session.GetPrefGroupID() == 3
                            || session.GetPrefGroupID() == 4 || session.GetPrefGroupID() == 6))) {
                        Intent newsHeaderDetail = new Intent(_context,
                                ActivityNewsDetail.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.BUNDLE_NEWS_ID, getNewsHeader.getId());
                        newsHeaderDetail.putExtras(bundle);
                        _context.startActivity(newsHeaderDetail);
                    } else {
                        Common.ShowToast(_context, Constant.MSG_VIP, Toast.LENGTH_SHORT);
                    }
                } else {
                    Intent newsHeaderDetail = new Intent(_context,
                            ActivityNewsDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.BUNDLE_NEWS_ID, getNewsHeader.getId());
                    newsHeaderDetail.putExtras(bundle);
                    _context.startActivity(newsHeaderDetail);
                }
            }
        });
        return convertView;
    }

    private class AsyncTrialLicence extends
            AsyncTask<String, Void, String> {
        String id = "";

        @Override
        protected String doInBackground(String... params) {
            id = params[0];
            SessionManager session = new SessionManager(_context);
            String token = session.GetPrefToken();
            return SrvCheckTrialLicence.CheckTrialLicence(token);
        }

        @Override
        protected void onPostExecute(String sTrialLicence) {
            Utils.hideLoadingDialog();
            if (sTrialLicence.equals("OK")) {
                Intent newsHeaderDetail = new Intent(_context,
                        ActivityNewsDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.BUNDLE_NEWS_ID, id);
                newsHeaderDetail.putExtras(bundle);
                _context.startActivity(newsHeaderDetail);
                return;
            } else {
                if (sTrialLicence.isEmpty()) {
                    Common.ShowToast(_context, Constant.MSG_ERROR, Toast.LENGTH_LONG);
                    return;
                }
            }

            new AlertDialog.Builder(_context)
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
            Utils.showLoadingDialog(_context);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}