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

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xstock.R;
import com.xstock.activity.ActivityGuideContent;
import com.xstock.models.GetListHelp;

import java.util.List;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class ListHelpAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _context;
    public List<GetListHelp> lstGetListHelp;
    boolean isRead = false;

    public ListHelpAdapter(Context context, List<GetListHelp> lstObject) {
        this.lstGetListHelp = lstObject;
        this._context = context;
        this.layoutInflater = LayoutInflater.from(_context);
    }

    public class ViewHolder {
        TextView txtGuideTitle, txtGuideDetail;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return lstGetListHelp.size();
    }

    @Override
    public GetListHelp getItem(int position) {
        return lstGetListHelp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.fragment_guide_detail, null);
            holder.txtGuideTitle = (TextView) convertView.findViewById(R.id.tvGuideTitle);
            holder.txtGuideDetail = (TextView) convertView.findViewById(R.id.tvGuideDetail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GetListHelp getListHelp = lstGetListHelp.get(position);

        holder.txtGuideTitle.setText(getListHelp.getTitle());
        holder.txtGuideDetail.setText(getListHelp.getDescription());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(_context, ActivityGuideContent.class);
                activity.putExtra("GUIDE_ID", lstGetListHelp.get(position).getId());
                _context.startActivity(activity);
            }
        });
        return convertView;
    }


}