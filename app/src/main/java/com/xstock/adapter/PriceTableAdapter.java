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
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
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
            convertView = layoutInflater.inflate(R.layout.fragment_price_table_item, null);
            holder.txtPriceTableTrade = (TextView) convertView.findViewById(R.id.txtPriceTableTrade);
            holder.txtPriceTablePclose = (TextView) convertView.findViewById(R.id.txtPriceTablePclose);
            holder.txtPriceTableUpdown = (TextView) convertView.findViewById(R.id.txtPriceTableUpdown);
            holder.txtPriceTablePoint = (TextView) convertView.findViewById(R.id.txtPriceTablePoint);
            holder.txtPriceTableVolume = (TextView) convertView.findViewById(R.id.txtPriceTableVolume);
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
        if (position % 2 != 0) {
            holder.txtPriceTableTrade.setBackgroundColor(_context.getResources().getColor(R.color.price_table_bg_1));
            holder.txtPriceTablePclose.setBackgroundColor(_context.getResources().getColor(R.color.price_table_bg_1));
            holder.txtPriceTableUpdown.setBackgroundColor(_context.getResources().getColor(R.color.price_table_bg_1));
            holder.txtPriceTablePoint.setBackgroundColor(_context.getResources().getColor(R.color.price_table_bg_1));
            holder.txtPriceTableVolume.setBackgroundColor(_context.getResources().getColor(R.color.price_table_bg_1));
        }

        if (getlstGetDataPrice.getType() == 1) {
            holder.txtPriceTablePclose.setTextColor(_context.getResources().getColor(R.color.white));
            holder.txtPriceTablePoint.setTextColor(_context.getResources().getColor(R.color.white));
            holder.txtPriceTableUpdown.setTextColor(_context.getResources().getColor(R.color.white));
        } else if (getlstGetDataPrice.getType() == 2) {
            holder.txtPriceTablePclose.setTextColor(_context.getResources().getColor(R.color.red));
            holder.txtPriceTablePoint.setTextColor(_context.getResources().getColor(R.color.red));
            holder.txtPriceTableUpdown.setTextColor(_context.getResources().getColor(R.color.red));
        } else if (getlstGetDataPrice.getType() == 3) {
            holder.txtPriceTablePclose.setTextColor(_context.getResources().getColor(R.color.yellow));
            holder.txtPriceTablePoint.setTextColor(_context.getResources().getColor(R.color.yellow));
            holder.txtPriceTableUpdown.setTextColor(_context.getResources().getColor(R.color.yellow));
        } else {
            holder.txtPriceTablePclose.setTextColor(_context.getResources().getColor(R.color.green));
            holder.txtPriceTablePoint.setTextColor(_context.getResources().getColor(R.color.green));
            holder.txtPriceTableUpdown.setTextColor(_context.getResources().getColor(R.color.green));
        }
        return convertView;
    }
}