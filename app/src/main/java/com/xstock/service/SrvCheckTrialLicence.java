package com.xstock.service;

import com.xstock.app.AppConfig;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketTimeoutException;

public class SrvCheckTrialLicence {

    public static String CheckTrialLicence(String token) {

        String str = "";

        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.CHECK_TRIAL_LICENCE;
        final String METHOD_NAME = AppConfig.CHECK_TRIAL_LICENCE;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String XSTOCK_URL = AppConfig.XSTOCK_URL_SERVICE;

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
