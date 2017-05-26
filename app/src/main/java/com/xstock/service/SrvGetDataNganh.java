package com.xstock.service;

import com.xstock.app.AppConfig;
import com.xstock.models.GetDataNganh;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class SrvGetDataNganh {

    public static ArrayList<GetDataNganh> GetDataNganh(String token) {

        ArrayList<GetDataNganh> lstGetNewsHeader = new ArrayList<GetDataNganh>();

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.GET_DATA_NGANH;
        final String METHOD_NAME = AppConfig.GET_DATA_NGANH;
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
                lstGetNewsHeader.add(new GetDataNganh(
                        object.getString("index_display").toString(),
                        object.getString("pclose").toString(),
                        object.getString("updown_percent").toString(),
                        object.getString("volume").toString(),
                        object.getInt("type")));
            }
        } catch (Exception e) {
            e.toString();
        }

        return lstGetNewsHeader;
    }
}