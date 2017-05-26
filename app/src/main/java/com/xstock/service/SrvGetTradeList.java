package com.xstock.service;

import android.os.StrictMode;

import com.xstock.app.AppConfig;
import com.xstock.models.GetTradeListItem;
import com.xstock.realm.RealmController;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import io.realm.Realm;

public class SrvGetTradeList {

//    public static void GetTradeList(String token) {
//
//        // Open the Realm for the UI thread.
//        Realm realm = Realm.getDefaultInstance();
//        RealmController realmController = new RealmController();
//
//        // Soap config
//        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.GET_TRADE_LIST;
//        final String METHOD_NAME = AppConfig.GET_TRADE_LIST;
//        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
//        final String URL = AppConfig.XSTOCK_URL_SERVICE;
//
//        // Chứa table của dataset trả về thông qua SoapObject
//        SoapObject table = null;
//        SoapObject tableRow = null;
//
//        // Its the client petition to the web
//        SoapObject client = null;
//        SoapSerializationEnvelope sse = null;
//
//        sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//        sse.dotNet = true;
//        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
//
//        try {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//            client = new SoapObject(NAMESPACE, METHOD_NAME);
//
//            PropertyInfo tokenInfo = new PropertyInfo();
//            tokenInfo.setName("token");
//            tokenInfo.setValue(token);
//            tokenInfo.setType(String.class);
//            client.addProperty(tokenInfo);
//
//            PropertyInfo keyInfo = new PropertyInfo();
//            keyInfo.setName("key");
//            keyInfo.setValue(AppConfig.KEY);
//            keyInfo.setType(String.class);
//            client.addProperty(keyInfo);
//
//            sse.setOutputSoapObject(client);
//            sse.bodyOut = client;
//            androidHttpTransport.call(SOAP_ACTION, sse);
//            SoapPrimitive responseBody = (SoapPrimitive) sse.getResponse();
//            String json = responseBody.toString();
//            final JSONArray jsonArray = new JSONArray(json);
//            realmController.clearGetTradeList(realm);
//            realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    // Add a UserDetail
//                    realm.createAllFromJson(GetTradeListItem.class, jsonArray);
//                }
//            });
//
//        } catch (Exception e) {
//            e.toString();
//        }
//    }

    public static ArrayList<GetTradeListItem> GetUserTradeList(String token) {

        ArrayList<GetTradeListItem> alstGetTradeList = new ArrayList<GetTradeListItem>();

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.GET_USER_TRADE_LIST;
        final String METHOD_NAME = AppConfig.GET_USER_TRADE_LIST;
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
            for (int i = 0; i < count; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                alstGetTradeList.add(new GetTradeListItem(
                        object.getString("id").toString(),
                        object.getString("name").toString()));
            }

        } catch (Exception e) {
            e.toString();
        }
        return alstGetTradeList;
    }

    public static ArrayList<GetTradeListItem> GetTradeList(String token) {

        ArrayList<GetTradeListItem> alstGetTradeList = new ArrayList<GetTradeListItem>();

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.GET_TRADE_LIST;
        final String METHOD_NAME = AppConfig.GET_TRADE_LIST;
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
            for (int i = 0; i < count; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                alstGetTradeList.add(new GetTradeListItem(
                        object.getString("id").toString(),
                        object.getString("name").toString()));
            }

        } catch (Exception e) {
            e.toString();
        }
        return alstGetTradeList;
    }
}