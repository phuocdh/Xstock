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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xstock.R;
import com.xstock.models.GetDataNganh;

import java.util.List;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class IndexInfoAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _context;
    public List<GetDataNganh> lstGetDataNganh;

    public IndexInfoAdapter(Context context, List<GetDataNganh> lstObject) {
        this.lstGetDataNganh = lstObject;
        this._context = context;
        this.layoutInflater = LayoutInflater.from(_context);

    }

    public class ViewHolder {
        TextView txtIndexInfoDisplay, txtIndexInfoPclose, txtIndexInfoVolume, txtIndexInfoUpdown;
        LinearLayout lnIndexInfo;
    }

    @Override
    public int getCount() {
        return lstGetDataNganh.size();
    }

    @Override
    public GetDataNganh getItem(int position) {
        return lstGetDataNganh.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
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
            convertView = layoutInflater.inflate(R.layout.fragment_index_info_item, null);
            holder.lnIndexInfo = (LinearLayout) convertView.findViewById(R.id.lnIndexInfo);
            holder.txtIndexInfoDisplay = (TextView) convertView.findViewById(R.id.txtIndexInfoDisplay);
            holder.txtIndexInfoPclose = (TextView) convertView.findViewById(R.id.txtIndexInfoPclose);
            holder.txtIndexInfoVolume = (TextView) convertView.findViewById(R.id.txtIndexInfoVolume);
            holder.txtIndexInfoUpdown = (TextView) convertView.findViewById(R.id.txtIndexInfoUpdownPercent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetDataNganh getlstGetDataNganh = lstGetDataNganh.get(position);
        holder.txtIndexInfoDisplay.setText(getlstGetDataNganh.getIndex_display());
        holder.txtIndexInfoPclose.setText(getlstGetDataNganh.getPclose());
        holder.txtIndexInfoVolume.setText(getlstGetDataNganh.getVolume());
        holder.txtIndexInfoUpdown.setText(getlstGetDataNganh.getUpdown_percent());

        if (position % 2 != 0) {
            holder.lnIndexInfo.setBackgroundColor(_context.getResources().getColor(R.color.price_table_bg_1));
        }

        if (getlstGetDataNganh.getType() == 3) {
            holder.txtIndexInfoUpdown.setTextColor(_context.getResources().getColor(R.color.txt_Updown));
        } else if (getlstGetDataNganh.getType() == 1) {
            holder.txtIndexInfoUpdown.setTextColor(_context.getResources().getColor(R.color.red));
        }
        return convertView;
    }
}