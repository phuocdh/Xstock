package com.xstock.service;

import com.xstock.app.AppConfig;
import com.xstock.constants.Constant;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class SrvOnOffPushDevice {

    public static String OnOffPushDevice(String token, String deviceToken, int isPush) {

        String str = "";

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.ON_OFF_PUSH_DEVICE;
        final String METHOD_NAME = AppConfig.ON_OFF_PUSH_DEVICE;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String XSTOCK_URL = AppConfig.XSTOCK_URL_SERVICE;
        try {
            HttpURLConnection urlc = (HttpURLConnection) new URL(XSTOCK_URL).openConnection();
            urlc.setConnectTimeout(Constant.TIME_OUT);
            if (urlc.getResponseCode() != HttpURLConnection.HTTP_OK) {
                str = Constant.TIMEOUT;
                return str;
            }
        } catch (SocketTimeoutException e) {
            str = Constant.TIMEOUT;
            return str;
        } catch (java.io.IOException e) {
            str = Constant.TIMEOUT;
            return str;
        }
        // Chứa table của dataset trả về thông qua SoapObject
        SoapObject table = null;

        // Its the client petition to the web
        SoapObject client = null;
        SoapSerializationEnvelope sse = null;

        sse = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        sse.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(XSTOCK_URL);

        try {
            client = new SoapObject(NAMESPACE, METHOD_NAME);
            PropertyInfo tokenInfo = new PropertyInfo();
            tokenInfo.setName("token");
            tokenInfo.setValue(token);
            tokenInfo.setType(String.class);
            client.addProperty(tokenInfo);

            PropertyInfo deviceTokenInfo = new PropertyInfo();
            deviceTokenInfo.setName("device_token");
            deviceTokenInfo.setValue(deviceToken);
            deviceTokenInfo.setType(String.class);
            client.addProperty(deviceTokenInfo);

            PropertyInfo osInfo = new PropertyInfo();
            osInfo.setName("os");
            osInfo.setValue("android");
            osInfo.setType(String.class);
            client.addProperty(osInfo);

            PropertyInfo isPushInfo = new PropertyInfo();
            isPushInfo.setName("ispush");
            isPushInfo.setValue(isPush);
            isPushInfo.setType(Integer.class);
            client.addProperty(isPushInfo);

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
        } catch (SocketTimeoutException e) {
            e.toString();
        } catch (Exception e) {
            e.toString();
        }
        return str;
    }
}
