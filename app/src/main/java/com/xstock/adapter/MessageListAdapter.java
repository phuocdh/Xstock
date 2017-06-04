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
import android.widget.TextView;

import com.xstock.R;
import com.xstock.models.GetDetailMessage;

import java.util.List;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class MessageListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _context;
    public List<GetDetailMessage> lstGetDetailMessage;

    public MessageListAdapter(Context context, List<GetDetailMessage> lstObject) {
        this.lstGetDetailMessage = lstObject;
        this._context = context;
        this.layoutInflater = LayoutInflater.from(_context);
    }

    public class ViewHolder {
        TextView txtDetailMsg, txtDateMsg;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return lstGetDetailMessage.size();
    }

    @Override
    public GetDetailMessage getItem(int position) {
        return lstGetDetailMessage.get(position);
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
            convertView = layoutInflater.inflate(R.layout.swipe_msg_item, null);
            holder.txtDetailMsg = (TextView) convertView.findViewById(R.id.tvMessage);
            holder.txtDateMsg = (TextView) convertView.findViewById(R.id.tvMessageDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GetDetailMessage getDetailMessage = lstGetDetailMessage.get(position);

        holder.txtDetailMsg.setText(getDetailMessage.getName());
        holder.txtDateMsg.setText(getDetailMessage.getDate());
        return convertView;
    }


}