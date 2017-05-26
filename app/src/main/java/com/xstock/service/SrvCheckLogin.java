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

public class SrvCheckLogin {


    public static String CheckServerLogin(String username, String password) {

        // Token trả về sau khi login thành công
        String token = "";

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.LOGIN_SERVICE;
        final String METHOD_NAME = AppConfig.LOGIN_SERVICE;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String XSTOCK_URL = AppConfig.XSTOCK_URL_SERVICE;
        try {
            HttpURLConnection urlc = (HttpURLConnection) new URL(XSTOCK_URL).openConnection();
            urlc.setConnectTimeout(Constant.TIME_OUT);
            if (urlc.getResponseCode() != HttpURLConnection.HTTP_OK) {
                token = Constant.TIMEOUT;
                return token;
            }
        } catch (java.net.SocketTimeoutException e) {
            token = Constant.TIMEOUT;
            return token;
        } catch (java.io.IOException e) {
            token = Constant.TIMEOUT;
            return token;
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
            PropertyInfo uerInfo = new PropertyInfo();
            uerInfo.setName("username");
            uerInfo.setValue(username);
            uerInfo.setType(String.class);
            client.addProperty(uerInfo);

            PropertyInfo passInfo = new PropertyInfo();
            passInfo.setName("password");
            passInfo.setValue(password);
            passInfo.setType(String.class);
            client.addProperty(passInfo);

            PropertyInfo keyInfo = new PropertyInfo();
            keyInfo.setName("key");
            keyInfo.setValue(AppConfig.KEY);
            keyInfo.setType(String.class);
            client.addProperty(keyInfo);

            sse.setOutputSoapObject(client);
            sse.bodyOut = client;
            androidHttpTransport.call(SOAP_ACTION, sse);
            SoapPrimitive responseBody = (SoapPrimitive) sse.getResponse();
            token = responseBody.toString();
        } catch (SocketTimeoutException e) {
            e.toString();
        } catch (Exception e) {
            e.toString();
        }
        return token;
    }

//    private class LoginTimeout extends Thread {
//
//        // overriden from Runnable, which Thread implements
//        public void run() {
//            Handler handler = new Handler(Looper.getMainLooper());
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    if (check.equals("OK") == true) {
//                        Common.ShowToast(context, Constant.MSG_DELETE_SUCCESS, Toast.LENGTH_SHORT);
//                    } else {
//                        Common.ShowToast(context, Constant.MSG_DELETE_FAIL, Toast.LENGTH_SHORT);
//                    }
//                }
//            });
//        }
//    }
}
