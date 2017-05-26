package com.xstock.service;

import com.xstock.app.AppConfig;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class SrvAddUserTradeList {

    public static String AddDeleteUserTradeList(String token, String tradeid,String tradename, int modify) {

        String str = "";
        // Soap config
        String service = "";
        if (modify == 0) {
            service = AppConfig.ADD_USER_TRADE_LIST;
        } else {
            service = AppConfig.DELETE_USER_TRADE_LIST;
        }
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + service;
        final String METHOD_NAME = service;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String URL = AppConfig.XSTOCK_URL_SERVICE;

        // Chứa table của dataset trả về thông qua SoapObject
        SoapObject table = null;

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
            if (modify == 0) {
                PropertyInfo tradeNameInfo = new PropertyInfo();
                tradeNameInfo.setName("tradename");
                tradeNameInfo.setValue(tradename);
                tradeNameInfo.setType(String.class);
                client.addProperty(tradeNameInfo);
            }

            PropertyInfo tradeidInfo = new PropertyInfo();
            tradeidInfo.setName("tradeid");
            tradeidInfo.setValue(tradeid);
            tradeidInfo.setType(String.class);
            client.addProperty(tradeidInfo);

            PropertyInfo keyInfo = new PropertyInfo();
            keyInfo.setName("key");
            keyInfo.setValue(AppConfig.KEY);
            keyInfo.setType(String.class);
            client.addProperty(keyInfo);

            sse.setOutputSoapObject(client);
            sse.bodyOut = client;
            androidHttpTransport.call(SOAP_ACTION, sse);
            SoapPrimitive responseBody = (SoapPrimitive) sse.getResponse();
            str = responseBody.toString();
        } catch (Exception e) {
            e.toString();
        }
        return str;
    }
}
