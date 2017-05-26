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

public class SrvCheckRegistry {


    public static String RegisterUser(String email, String password, String handphone) {

        // Token trả về sau khi login thành công
        String str = "NG";

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.REGISTER_USER;
        final String METHOD_NAME = AppConfig.REGISTER_USER;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String XSTOCK_URL = AppConfig.XSTOCK_URL_SERVICE;
        try {
            HttpURLConnection urlc = (HttpURLConnection) new URL(XSTOCK_URL).openConnection();
            urlc.setConnectTimeout(Constant.TIME_OUT);
            if (urlc.getResponseCode() != HttpURLConnection.HTTP_OK) {
                str = "NG";
                return str;
            }
        } catch (SocketTimeoutException e) {
            str = "NG";
            return str;
        } catch (java.io.IOException e) {
            str = "NG";
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
            PropertyInfo emailInfo = new PropertyInfo();
            emailInfo.setName("email");
            emailInfo.setValue(email);
            emailInfo.setType(String.class);
            client.addProperty(emailInfo);

            PropertyInfo passInfo = new PropertyInfo();
            passInfo.setName("password");
            passInfo.setValue(password);
            passInfo.setType(String.class);
            client.addProperty(passInfo);

            PropertyInfo handphoneInfo = new PropertyInfo();
            handphoneInfo.setName("handphone");
            handphoneInfo.setValue(handphone);
            handphoneInfo.setType(String.class);
            client.addProperty(handphoneInfo);

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
