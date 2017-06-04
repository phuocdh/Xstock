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
import com.xstock.models.GetDataPrice;

import java.util.List;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class PriceTableAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context _context;
    public List<GetDataPrice> lstGetDataPrice;

    public PriceTableAdapter(Context context, List<GetDataPrice> lstObject) {
        this.lstGetDataPrice = lstObject;
        this._context = context;
        this.layoutInflater = LayoutInflater.from(_context);

    }

    public class ViewHolder {
        TextView txtPriceTableTrade, txtPriceTablePclose, txtPriceTablePoint, txtPriceTableUpdown, txtPriceTableVolume;
        View vPriceTable;
        LinearLayout lnPriceTable;
    }

    @Override
    public int getCount() {
        return lstGetDataPrice.size();
    }

    @Override
    public GetDataPrice getItem(int position) {
        return lstGetDataPrice.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
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
            convertView = layoutInflater.inflate(R.layout.fragment_price_table_item, null);
            holder.lnPriceTable = (LinearLayout) convertView.findViewById(R.id.lnPriceTable);
            holder.txtPriceTableTrade = (TextView) convertView.findViewById(R.id.txtPriceTableTrade);
            holder.txtPriceTablePclose = (TextView) convertView.findViewById(R.id.txtPriceTablePclose);
            holder.txtPriceTableUpdown = (TextView) convertView.findViewById(R.id.txtPriceTableUpdown);
            holder.txtPriceTablePoint = (TextView) convertView.findViewById(R.id.txtPriceTablePoint);
            holder.txtPriceTableVolume = (TextView) convertView.findViewById(R.id.txtPriceTableVolume);
            holder.vPriceTable = convertView.findViewById(R.id.viewPriceTable);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GetDataPrice getlstGetDataPrice = lstGetDataPrice.get(position);
        holder.txtPriceTableTrade.setText(getlstGetDataPrice.getTrade_name());
        holder.txtPriceTablePclose.setText(getlstGetDataPrice.getPclose());
        holder.txtPriceTableUpdown.setText(getlstGetDataPrice.getUpdown());
        holder.txtPriceTablePoint.setText(getlstGetDataPrice.getPoint());
        holder.txtPriceTableVolume.setText(getlstGetDataPrice.getVolume());
        int mColor = _context.getResources().getColor(R.color.price_table_bg_1);
        if (position % 2 != 0) {
            holder.lnPriceTable.setBackgroundColor(mColor);
        }

        if (getlstGetDataPrice.getType() == 1) {
            mColor = _context.getResources().getColor(R.color.white);
            holder.txtPriceTablePclose.setTextColor(mColor);
            holder.txtPriceTablePoint.setTextColor(mColor);
            holder.txtPriceTableUpdown.setTextColor(mColor);
        } else if (getlstGetDataPrice.getType() == 2) {
            mColor = _context.getResources().getColor(R.color.red);
            holder.txtPriceTablePclose.setTextColor(mColor);
            holder.txtPriceTablePoint.setTextColor(mColor);
            holder.txtPriceTableUpdown.setTextColor(mColor);
            holder.vPriceTable.setBackgroundColor(mColor);
        } else if (getlstGetDataPrice.getType() == 3) {
            mColor = _context.getResources().getColor(R.color.yellow);
            holder.txtPriceTablePclose.setTextColor(mColor);
            holder.txtPriceTablePoint.setTextColor(mColor);
            holder.txtPriceTableUpdown.setTextColor(mColor);
            holder.vPriceTable.setBackgroundColor(mColor);
        } else {
            mColor = _context.getResources().getColor(R.color.green);
            holder.txtPriceTablePclose.setTextColor(mColor);
            holder.txtPriceTablePoint.setTextColor(mColor);
            holder.txtPriceTableUpdown.setTextColor(mColor);
            holder.vPriceTable.setBackgroundColor(mColor);
        }
        return convertView;
    }
}