package com.xstock.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inqbarna.tablefixheaders.adapters.BaseTableAdapter;
import com.xstock.R;
import com.xstock.models.GetDataAnalysis;
import com.xstock.models.X24Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhuocDH on 9/18/2016.
 */

public class DataAnalysisAdapter extends BaseTableAdapter {

    private LayoutInflater layoutInflater;
    private GetDataAnalysis getDataAnalysis[];
    private String headers[] = new String[]{};
    List<ArrayList<String>> x24Colors;
    private Context context;
    private int[] widths = new int[]{};
    public List<ArrayList<String>> lstTest;
    private final float density;

    public DataAnalysisAdapter(Context context, String headers[], int[] widths, List<ArrayList<String>> lstTest, List<ArrayList<String>> x24Colors) {
        this.headers = headers;
        this.widths = widths;
        this.lstTest = lstTest;
        this.x24Colors = x24Colors;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        getDataAnalysis = new GetDataAnalysis[]{
                new GetDataAnalysis("Xstock"),
        };

        density = context.getResources().getDisplayMetrics().density;
        getDataAnalysis[0].lstX24Data.add(new X24Data(lstTest));
    }

    @Override
    public int getRowCount() {
        int rowCount = getDataAnalysis[0].lstX24Data.get(0).x24Data.size();
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        int columnCount = headers.length - 1;
        return columnCount;
    }

    @Override
    public View getView(int row, int column, View convertView, ViewGroup parent) {
        final View view;
        switch (getItemViewType(row, column)) {
            case 0:
                view = getFirstHeader(convertView, parent);
                break;
            case 1:
                view = getHeader(column, convertView, parent);
                break;
            case 2:
                view = getFirstBody(row, convertView, parent);
                break;
            case 3:
                view = getBody(row, column, convertView, parent);
                break;
            case 4:
                view = getFamilyView( convertView, parent);
                break;
            default:
                throw new RuntimeException("wtf?");
        }
        return view;
    }

    private View getFirstHeader(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_table_header_first, parent, false);
        }
        convertView.setBackgroundColor(context.getResources().getColor(R.color.tb_boder));
        TextView tv = ((TextView) convertView.findViewById(R.id.tvHeaderTrade));
        String[] firstHeader = headers[0].split("\\|");
        tv.setText(firstHeader[0]);
        ((TextView) convertView.findViewById(R.id.tvHeaderDate)).setText(firstHeader[1]);
        convertView.setPadding(1, 1, 1, 1);
        return convertView;
    }

    private View getHeader(int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_table_header, parent, false);
        }
        convertView.setBackgroundColor(context.getResources().getColor(R.color.tb_boder));
        ((TextView) convertView.findViewById(android.R.id.text1)).setText(headers[column + 1]);

        convertView.setPadding(0, 1, 1, 1);
        return convertView;
    }

    private View getFirstBody(int row, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_table_first, parent, false);
        }
        convertView.setBackgroundColor(context.getResources().getColor(R.color.tb_boder));
        TextView tv = ((TextView) convertView.findViewById(R.id.tvBodyTrade));
        TextView tv1 = ((TextView) convertView.findViewById(R.id.tvBodyDate));
        tv.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color3 : R.drawable.bg_table_color4);
        tv1.setBackgroundResource(row % 2 == 0 ? R.drawable.bg_table_color3 : R.drawable.bg_table_color4);
        String[] firstBody = GetX24Data(row).x24Data.get(row).get(0).split("\\|");
        tv.setText(firstBody[0]);
        tv1.setText(firstBody[1]);
        convertView.setPadding(1, 0, 1, 1);
        return convertView;
    }

    private View getBody(int row, int column, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_table, parent, false);
        }
        convertView.setBackgroundColor(context.getResources().getColor(R.color.tb_boder));
        TextView tv = ((TextView) convertView.findViewById(android.R.id.text1));
        String[] Colors = x24Colors.get(row).get(column).split("\\;");
        String color0 = Colors[0];
        if (color0.trim().isEmpty() == false) {
            tv.setTextColor(Color.parseColor(Colors[0]));
            String color1 = Colors[1];
            if (color1.trim().isEmpty() == false) {
                tv.setBackgroundColor(Color.parseColor(Colors[1]));
            }
        }

        tv.setText(GetX24Data(row).x24Data.get(row).get(column + 1));
        convertView.setPadding(0, 0, 1, 1);
        return convertView;
    }

    private View getFamilyView(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_table_family, parent, false);
        }
        ((TextView) convertView.findViewById(android.R.id.text1)).setText("");
        return convertView;
    }

    @Override
    public int getWidth(int column) {
        return Math.round(widths[column + 1] * density);
    }

    @Override
    public int getHeight(int row) {
        final int height;
        if (row == -1) {
            height =  45;
        } else if (isFamily(row)) {
            height = 45;
        } else {
            height = 45;
        }
        return Math.round(height * density);
    }

    @Override
    public int getItemViewType(int row, int column) {
        int itemViewType = 0;
        if (row == -1 && column == -1) {
            itemViewType = 0;
        } else if (row == -1) {
            itemViewType = 1;
        } else if (column == -1) {
            itemViewType = 2;
//        } else if (isFamily(row)) {
//            itemViewType = 4;
        } else {
            itemViewType = 3;
        }
        return itemViewType;
    }

    private boolean isFamily(int row) {
        int x24Index = 0;
        while (row > 0) {
            row -= getDataAnalysis[x24Index].get(0).x24Data.size() + 1;
            x24Index++;
        }
        return row == 0;
    }

    private X24Data GetX24Data(int row) {
        int x24Index = 0;
        while (row >= 0) {
            row -= getDataAnalysis[x24Index].get(0).x24Data.size() + 1;
            x24Index++;
        }
        x24Index--;
        return getDataAnalysis[x24Index].lstX24Data.get(0);
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }
}