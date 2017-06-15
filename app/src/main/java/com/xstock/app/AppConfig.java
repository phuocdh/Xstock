package com.xstock.app;

/**
 * Created by phuocdh on 7/4/2016.
 */
public class AppConfig {
    // Key
    public static String KEY = "thatlatuyetvoi";
    public static String CMP_GUIDE_KEY = "guide_app";
    public static String CMP_TRADE_KEY = "textduoichartappmobi";
    public static String ANDROID = "android";

    // Soap config
    public static String SOAP_NAMESPACE = "http://tempuri.org/";
    public static String XSTOCK_URL_SERVICE = "http://xstock.vn:8081/WS/XStockWS.asmx";
    public static String XSTOCK_URL_IMAGE = "http://xstock.vn/img/";
    public static String XSTOCK_URL_STOCK_IMAGE = "http://xstock.vn/img/stock_images/";
    public static String XSTOCK_URL = "http://xstock.vn";
    public static String XSTOCK_CONTACT = "http://xstock.vn/contacts/contact";
    public static String XSTOCK_FORGOT_PASS_URL = "http://xstock.vn/users/fortget_password.html";

    // List method tuong ung voi tung service
    public static String LOGIN_SERVICE = "Login";
    public static String GET_NEWS_HEADER_SERVICE = "GetNewsHeader";
    public static String GET_DETAIL_NEWS_SERVICE = "GetDetailNews";
    public static String GET_USER_DETAIL = "GetUserDetail";
    public static String GET_USER_PRODUCT = "GetUserProduct";
    public static String GET_DATA_NGANH = "GetDataNganh";
    public static String GET_TRADE_LIST = "GetTradeList";
    public static String GET_USER_TRADE_LIST = "GetUserTradeList";
    public static String GET_MESSAGE_HEADER = "GetMessageHeader";
    public static String GET_DETAIL_MESSAGE = "GetDetailMessage";
    public static String GET_IMAGE_TRADE = "GetImageTrade";
    public static String GET_DATA_ANALYSIS = "GetDataAnalysis";
    public static String ADD_USER_TRADE_LIST = "AddUserTradeList";
    public static String DELETE_USER_TRADE_LIST = "DeleteUserTradeList";
    public static String GET_DATA_PRICE = "GetDataPrice";
    public static String ADD_DEVICE = "AddDevice";
    public static String DELETE_MESSAGE_LIST = "DeleteMessageList";
    public static String ON_OFF_PUSH_DEVICE = "OnOffPushDevice";
    public static String GET_PUSH_DEVICE_STATUS = "GetPushDeviceStatus";
    public static String REGISTER_USER = "RegisterUser";
    public static String GET_LIST_HELP = "GetListHelp";
    public static String GET_HELP = "GetHelp";
    public static String GET_HELP_CONTENT = "GetHelpContent";
    public static String CHECK_TRIAL_LICENCE = "CheckTrialLicence";
}