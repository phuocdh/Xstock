package com.xstock.service;

import com.google.common.primitives.Ints;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xstock.app.AppConfig;
import com.xstock.models.X24Data;

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

public class SrvGetDataAnalyis {

    public static X24Data GetDataAnalyis(String token, String trade_name, String from_date, String to_date, int type) {
        X24Data x = new X24Data();
        // Soap config
        final String SOAP_ACTION = AppConfig.SOAP_NAMESPACE + AppConfig.GET_DATA_ANALYSIS;
        final String METHOD_NAME = AppConfig.GET_DATA_ANALYSIS;
        final String NAMESPACE = AppConfig.SOAP_NAMESPACE;
        final String URL = AppConfig.XSTOCK_URL_SERVICE;
        final String COLOR = "(color)";

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

            PropertyInfo tradeNameInfo = new PropertyInfo();
            tradeNameInfo.setName("trade_name");
            tradeNameInfo.setValue(trade_name);
            tradeNameInfo.setType(String.class);
            client.addProperty(tradeNameInfo);

            PropertyInfo fromDateInfo = new PropertyInfo();
            fromDateInfo.setName("from_date");
            fromDateInfo.setValue(from_date);
            fromDateInfo.setType(String.class);
            client.addProperty(fromDateInfo);

            PropertyInfo toDateInfo = new PropertyInfo();
            toDateInfo.setName("to_date");
            toDateInfo.setValue(to_date);
            toDateInfo.setType(String.class);
            client.addProperty(toDateInfo);

            PropertyInfo typeInfo = new PropertyInfo();
            typeInfo.setName("type");
            typeInfo.setValue(type);
            typeInfo.setType(Integer.class);
            client.addProperty(typeInfo);

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
//            String json = "[{\"id\":\"2267533\",\"trade_id\":\"1\",\"trade_name\":\"AAA\",\"exchange_date\":\"9/4/2015 12:00:00 AM\",\"type_id\":\"1\",\"updown_percent\":\"10.00\",\"Buy\":\"-\",\"Buy(color)\":\"#FFFFFF;#008000\",\"Sell\":\"-\",\"Sell(color)\":\"#FFFFFF;#008000\",\"BuySell\":\"Chờ mua\",\"BuySell(color)\":\"#FFFFFF;#008000\",\"Open\":\"9.8\",\"Open(color)\":\"#FFFFFF;#FF0000\",\"High\":\"9.8\",\"High(color)\":\"#FFFFFF;#FF0000\",\"Low\":\"9.6\",\"Low(color)\":\"#FFFFFF;#FF0000\",\"Close\":\"9.6\",\"Close(color)\":\"#FFFFFF;#008000\",\"Volume\":\"116,689\",\"Volume(color)\":\"#FFFFFF;#008000\",\"Thuộc ngành\":\"Bao bì và đóng gói\",\"Thuộc ngành(color)\":\"#0000FF;#FFFFFF\",\"Giá trị sổ sách\":\"19,334\",\"Giá trị sổ sách(color)\":\"#0000FF;#FFFFFF\",\"Tìm năng doanh nghiệp\":\"KD có lãi\",\"Tìm năng doanh nghiệp(color)\":\"#0000FF;#FFFFFF\",\"Ngày dữ liệu\":\"02/05/2016 00:00:00 AM\",\"Ngày dữ liệu(color)\":\"#0000FF;#FFFFFF\",\"Data5\":\"-\",\"Data5(color)\":\"#0000FF;#FFFFFF\",\"Data6\":\"-\",\"Data6(color)\":\"#0000FF;#FFFFFF\",\"Data7\":\"-\",\"Data7(color)\":\"#0000FF;#FFFFFF\",\"Data8\":\"-\",\"Data8(color)\":\"#0000FF;#FFFFFF\",\"Data9\":\"-\",\"Data9(color)\":\"#0000FF;#FFFFFF\",\"Data10\":\"-\",\"Data10(color)\":\"#0000FF;#FFFFFF\",\"Data11\":\"-\",\"Data11(color)\":\"#0000FF;#FFFFFF\",\"Data12\":\"-\",\"Data12(color)\":\"#0000FF;#FFFFFF\",\"Data13\":\"-\",\"Data13(color)\":\"#0000FF;#FFFFFF\",\"Data14\":\"-\",\"Data14(color)\":\"#0000FF;#FFFFFF\",\"Data15\":\"-\",\"Data15(color)\":\"#0000FF;#FFFFFF\"},{\"id\":\"2268349\",\"trade_id\":\"1\",\"trade_name\":\"AAA\",\"exchange_date\":\"9/9/2015 12:00:00 AM\",\"type_id\":\"1\",\"updown_percent\":\"10.00\",\"Buy\":\"-\",\"Buy(color)\":\"#FFFFFF;#FF0000\",\"Sell\":\"-\",\"Sell(color)\":\"#FFFFFF;#FF0000\",\"BuySell\":\"Chờ mua\",\"BuySell(color)\":\"#FFFFFF;#FF0000\",\"Open\":\"9.7\",\"Open(color)\":\"#FFFFFF;#008000\",\"High\":\"9.8\",\"High(color)\":\"#FFFFFF;#008000\",\"Low\":\"9.6\",\"Low(color)\":\"#FFFFFF;#008000\",\"Close\":\"9.7\",\"Close(color)\":\"#FFFFFF;#FF0000\",\"Volume\":\"130,491\",\"Volume(color)\":\"#FFFFFF;#FF0000\",\"Thuộc ngành\":\"Bao bì và đóng gói\",\"Thuộc ngành(color)\":\"#0000FF;#FFFFFF\",\"Giá trị sổ sách\":\"19,334\",\"Giá trị sổ sách(color)\":\"#0000FF;#FFFFFF\",\"Tìm năng doanh nghiệp\":\"KD có lãi\",\"Tìm năng doanh nghiệp(color)\":\"#0000FF;#FFFFFF\",\"Ngày dữ liệu\":\"02/05/2016 00:00:00 AM\",\"Ngày dữ liệu(color)\":\"#0000FF;#FFFFFF\",\"Data5\":\"-\",\"Data5(color)\":\"#0000FF;#FFFFFF\",\"Data6\":\"-\",\"Data6(color)\":\"#0000FF;#FFFFFF\",\"Data7\":\"-\",\"Data7(color)\":\"#0000FF;#FFFFFF\",\"Data8\":\"-\",\"Data8(color)\":\"#0000FF;#FFFFFF\",\"Data9\":\"-\",\"Data9(color)\":\"#0000FF;#FFFFFF\",\"Data10\":\"-\",\"Data10(color)\":\"#0000FF;#FFFFFF\",\"Data11\":\"-\",\"Data11(color)\":\"#0000FF;#FFFFFF\",\"Data12\":\"-\",\"Data12(color)\":\"#0000FF;#FFFFFF\",\"Data13\":\"-\",\"Data13(color)\":\"#0000FF;#FFFFFF\",\"Data14\":\"-\",\"Data14(color)\":\"#0000FF;#FFFFFF\",\"Data15\":\"-\",\"Data15(color)\":\"#0000FF;#FFFFFF\"}]";

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(json);
            int count = element.getAsJsonArray().size();
            List<ArrayList<String>> x24Data = new ArrayList<>();
            List<ArrayList<String>> x24Colors = new ArrayList<>();
            List<String> x24Keys = new ArrayList<>();
            List<Integer> x24Widths = new ArrayList<>();
            x24Widths.add(160);
            x24Keys.add("Mã|Ngày");
            boolean isCheck = false;
            for (int i = 0; i < count; i++) {
                JsonObject object = element.getAsJsonArray().get(i).getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entries = object.entrySet();
                ArrayList<String> lstTradeData = new ArrayList<>();
                ArrayList<String> x24Color = new ArrayList<>();
                String value = object.get("trade_name").toString() + "|" + object.get("exchange_date").toString();
                value = value.replace("\"", "");
                lstTradeData.add(value);
                for (Map.Entry<String, JsonElement> entry : entries) {
                    String key = entry.getKey();
                    String keyWithoutColor = "";
                    if (key.contains(COLOR) == true) {
                        keyWithoutColor = key.trim().substring(0, key.length() - 7);
                        keyWithoutColor = keyWithoutColor.replace("\"", "");
                        if (isCheck == false) {
                            x24Keys.add(keyWithoutColor);
                            if (keyWithoutColor.equals("Buy") || keyWithoutColor.equals("Sell")) {
                                x24Widths.add(60);
                            } else if (keyWithoutColor.trim().equals("Tên doanh nghiệp") || keyWithoutColor.trim().equals("Ngành nghề chính")) {
                                x24Widths.add(170);
                            } else {
                                x24Widths.add(100);
                            }
                        }
                        try {
                            value = object.get(key).toString();
                            value = value.replace("\"", "");
                            x24Color.add(value);
                            value = object.get(keyWithoutColor).toString();
                            value = value.replace("\"", "");
                            lstTradeData.add(value);
                        } catch (JsonIOException e) {
                        }
                    }
                }
                value = object.get("exchange_date").toString();
                value = value.replace("\"", "");
                lstTradeData.add(value);
                x24Data.add(lstTradeData);
                x24Colors.add(x24Color);
                isCheck = true;
            }

            x.setX24Data(x24Data);
            x.setHeaders(x24Keys.toArray(new String[x24Keys.size()]));
            x.setColors(x24Colors);
            x.setWidths(Ints.toArray(x24Widths));
        } catch (Exception e) {
            e.toString();
        }

        return x;
    }
}