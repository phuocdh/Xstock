package com.xstock.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by PhuocDH on 9/13/2016.
 */
public class X24Data implements Comparator<X24Data> {
    public List<ArrayList<String>> x24Data;
    public List<ArrayList<String>> x24Colors;
    public String[] headers;
    public int[] widths;


    public X24Data(List<ArrayList<String>> x24Data, String[] headers, int[] widths, List<ArrayList<String>> x24Colors) {
        this.x24Data = x24Data;
        this.headers = headers;
        this.x24Colors = x24Colors;
        this.widths = widths;
    }

    public X24Data() {

    }

    public X24Data(List<ArrayList<String>> x24Data) {
        this.x24Data = x24Data;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<ArrayList<String>> getX24Data() {
        return x24Data;
    }

    public void setX24Data(List<ArrayList<String>> x24Data) {
        this.x24Data = x24Data;
    }

    public int[] getWidths() {
        return widths;
    }

    public void setWidths(int[] widths) {
        this.widths = widths;
    }

    public List<ArrayList<String>> getColors() {
        return x24Colors;
    }

    public void setColors(List<ArrayList<String>> x24Colors) {
        this.x24Colors = x24Colors;
    }

    @Override
    public int compare(X24Data lhs, X24Data rhs) {
        return lhs.getX24Data().get(0).get(0).compareTo(rhs.getX24Data().get(0).get(0));
    }
}
