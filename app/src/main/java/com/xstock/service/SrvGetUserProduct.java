package com.xstock.service;

import android.os.StrictMode;

import com.xstock.app.AppConfig;
import com.xstock.helper.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

public class SrvGetUserProduct {

    public static void SetUserProductLicense(SessionManager session, String token) {

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.GET_USER_PRODUCT;
        final String METHOD_NAME = AppConfig.GET_USER_PRODUCT;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String URL = AppConfig.XSTOCK_URL_SERVICE;

        // Its the client petition to the web
        SoapObject client;
        SoapSerializationEnvelope sse;

        sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        sse.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
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
            session.SetPrefX24BasicLicense(false);
            session.SetPrefX24TradersLicense(false);
            session.SetPrefX24PlusLicense(false);
            session.SetPrefNewsLicense(false);
            session.SetPrefIndexInfoLicense(false);
            session.SetPref5TradeLicense(false);
            session.SetPref10TradeLicense(false);
            session.SetPref15TradeLicense(false);
            for (int i = 0; i < count; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                switch (Integer.valueOf(jsonObject.getString("product_id"))) {
                    case 1:
                        session.SetPrefX24BasicLicense(true);
                        break;
                    case 2:
                        session.SetPrefX24TradersLicense(true);
                        break;
                    case 3:
                        session.SetPrefX24PlusLicense(true);
                        break;
                    case 4:
                        session.SetPrefNewsLicense(true);
                        break;
                    case 5:
                        session.SetPrefIndexInfoLicense(true);
                        break;
                    case 9:
                        session.SetPref5TradeLicense(true);
                        break;
                    case 10:
                        session.SetPref10TradeLicense(true);
                        break;
                    case 11:
                        session.SetPref15TradeLicense(true);
                        break;
                }
            }
        } catch (Exception e) {
            e.toString();
        }
    }
}