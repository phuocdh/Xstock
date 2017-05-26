package com.xstock.service;

import com.google.common.primitives.Ints;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xstock.app.AppConfig;
import com.xstock.models.GetDataNganh;
import com.xstock.models.GetDataPrice;
import com.xstock.models.GetNewsHeader;
import com.xstock.models.X24Data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SrvGetPriceTable {

    public static ArrayList<GetDataPrice> GetPriceTable(String token, String from_date) {

        ArrayList<GetDataPrice> lst = new ArrayList<>();
        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.GET_DATA_PRICE;
        final String METHOD_NAME = AppConfig.GET_DATA_PRICE;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String URL = AppConfig.XSTOCK_URL_SERVICE;

        // Chứa table của dataset trả về thông qua SoapObject
        SoapObject table = null;
        SoapObject tableRow = null;

        // Its the client petition to the web
        SoapObject client = null;
        SoapSerializationEnvelope sse = null;

        sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        sse.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            client = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo tokenInfo = new PropertyInfo();
            tokenInfo.setName("token");
            tokenInfo.setValue(token);
            tokenInfo.setType(String.class);
            client.addProperty(tokenInfo);

            PropertyInfo fromDateInfo = new PropertyInfo();
            fromDateInfo.setName("from_date");
            fromDateInfo.setValue(from_date);
            fromDateInfo.setType(String.class);
            client.addProperty(fromDateInfo);

            PropertyInfo keyInfo = new PropertyInfo();
            keyInfo.setName("key");
            keyInfo.setValue(AppConfig.KEY);
            keyInfo.setType(String.class);
            client.addProperty(keyInfo);

            sse.setOutputSoapObject(client);
            sse.bodyOut = client;
            androidHttpTransport.call(SOAP_ACTION, sse);
            SoapPrimitive responseBody = (SoapPrimitive) sse.getResponse();
            String json = responseBody.toString();
            JSONArray jsonArray = new JSONArray(json);
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                lst.add(new GetDataPrice(
                        object.getString("trade_name").toString(),
                        object.getString("pclose").toString(),
                        object.getString("point").toString(),
                        object.getString("updown").toString(),
                        object.getString("volume").toString(),
                        object.getInt("type")));
            }
        } catch (Exception e) {
            e.toString();
        }

        return lst;
    }
}