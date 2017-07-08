package com.xstock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xstock.R;
import com.xstock.models.GetTradeListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class SearchViewAdapterFavorite extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    public List<GetTradeListItem> getTradeListItem = null;
    public ArrayList<GetTradeListItem> arraylist;
    String note;

    public SearchViewAdapterFavorite(Context context, List<GetTradeListItem> getTradeListItem, String note) {
        mContext = context;
        this.getTradeListItem = getTradeListItem;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(getTradeListItem);
        this.note = note;
    }

    public class ViewHolder {
        TextView tvTradeName;
        TextView tvNote;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return getTradeListItem.size();
    }

    @Override
    public GetTradeListItem getItem(int position) {
        return getTradeListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.swipe_item, null);
            holder.tvTradeName = (TextView) view.findViewById(R.id.tvTradeName);
            holder.tvNote = (TextView) view.findViewById(R.id.tvNote);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.tvTradeName.setText(getTradeListItem.get(position).getName());
        holder.tvNote.setText(note);
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        getTradeListItem.clear();
        if (charText.length() == 0) {
            getTradeListItem.addAll(arraylist);
        } else {
            for (GetTradeListItem wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    getTradeListItem.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}