package com.xstock.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhuocDH on 9/13/2016.
 */
public class GetDataAnalysis {
    public final String tradeName;
    public final List<X24Data> lstX24Data;

    public GetDataAnalysis(String tradeName) {
        this.tradeName = tradeName;
        lstX24Data = new ArrayList<X24Data>();
    }

    public int size() {
        return lstX24Data.size();
    }

    public X24Data get(int i) {
        return lstX24Data.get(i);
    }
}
