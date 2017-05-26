package com.xstock.commons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xstock.app.AppConfig;
import com.xstock.constants.Constant;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class for formatting and parsing the various date formats we expect
 * to encounter.
 */
public class Common {
	private static final String TAG = "DateUtils";

	private static final SimpleDateFormat[] dateFormats;
	private static final int iDateFormat = 2;

	private Common() {
	}

	static {
		final String[] possibleDateFormats = {
				"yyyy MM dd HH:mm:ss",
				"EEE, dd MMM yyyy HH:mm:ss z", // RFC_822
				"EEE, dd MMM yyyy HH:mm zzzz",
				"yyyy-MM-dd'T'HH:mm:ssZ",
				"yyyy-MM-dd'T'HH:mm:ss.SSSzzzz", // Blogger Atom feed has
													// millisecs also
				"yyyy-MM-dd'T'HH:mm:sszzzz", "yyyy-MM-dd'T'HH:mm:ss z",
				"yyyy-MM-dd'T'HH:mm:ssz", // ISO_8601
				"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HHmmss.SSSz", };

		dateFormats = new SimpleDateFormat[possibleDateFormats.length];
		TimeZone gmtTZ = TimeZone.getTimeZone("GMT");

		for (int i = 0; i < possibleDateFormats.length; i++) {
			/* TODO: Support other locales? */
			dateFormats[i] = new SimpleDateFormat(possibleDateFormats[i],
					Locale.ENGLISH);

			dateFormats[i].setTimeZone(gmtTZ);
		}
	}

	/**
	 * Parse a date string. The format of RSS/Atom dates come in many different
	 * forms, so this method is extremely flexible and attempts to understand
	 * many different formats.
	 * 
	 * Copied verbatim from Informa 0.7.0-alpha2, ParserUtils.java.
	 * 
	 * @param strdate
	 *            Date string to attempt to parse.
	 * 
	 * @return If successful, returns a {@link Date} object representing the
	 *         parsed date; otherwise, null.
	 */
	public static Date parseDate(String strdate) {
		Date result = null;
		strdate = strdate.trim();
		if (strdate.endsWith("Z")) {
			strdate = strdate.substring(0, strdate.length() - 1) + "GMT";
		}

		if (strdate.length() > 10) {

			// TODO deal with +4:00 (no zero before hour)
			if ((strdate.substring(strdate.length() - 5).indexOf("+") == 0 || strdate
					.substring(strdate.length() - 5).indexOf("-") == 0)
					&& strdate.substring(strdate.length() - 5).indexOf(":") == 2) {

				String sign = strdate.substring(strdate.length() - 5,
						strdate.length() - 4);

				strdate = strdate.substring(0, strdate.length() - 5) + sign
						+ "0" + strdate.substring(strdate.length() - 4);
				// logger.debug("CASE1 : new date " + strdate + " ? "
				// + strdate.substring(0, strdate.length() - 5));

			}

			String dateEnd = strdate.substring(strdate.length() - 6);

			// try to deal with -05:00 or +02:00 at end of date
			// replace with -0500 or +0200
			if ((dateEnd.indexOf("-") == 0 || dateEnd.indexOf("+") == 0)
					&& dateEnd.indexOf(":") == 3) {
				// TODO deal with GMT-00:03
				if ("GMT".equals(strdate.substring(strdate.length() - 9,
						strdate.length() - 6))) {
					Log.d(TAG, "General time zone with offset, no change");
				} else {
					// continue treatment
					String oldDate = strdate;
					String newEnd = dateEnd.substring(0, 3)
							+ dateEnd.substring(4);
					strdate = oldDate.substring(0, oldDate.length() - 6)
							+ newEnd;
					// logger.debug("!!modifying string ->"+strdate);
				}
			}
		}
		int i = 0;
		int dateFormatsLength = dateFormats.length;
		while (i < dateFormatsLength) {
			try {
				result = dateFormats[i].parse(strdate);
				// logger.debug("******Parsing Success "+strdate+"->"+result+"
				// with
				// "+dateFormats[i].toPattern());
				break;
			} catch (java.text.ParseException eA) {
				i++;
			}
		}

		return result;
	}

	/**
	 * Format a date in a manner that would be most suitable for serialized
	 * storage.
	 * 
	 * @param date
	 *            {@link Date} object to format.
	 * 
	 * @return Robust, formatted date string.
	 */
	public static String formatDate(Date date) {
		return dateFormats[iDateFormat].format(date);
	}

	public static String getUniqueID(Context context) {
		String myAndroidDeviceId = "";
		TelephonyManager mTelephony = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (mTelephony.getDeviceId() != null) {
			myAndroidDeviceId = mTelephony.getDeviceId();
		} else {
			myAndroidDeviceId = Secure.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
		}
		return myAndroidDeviceId;
	}

	public static void ShowToast(Context context, String str, int duration) {
		Toast toast = Toast.makeText(context, str, duration);
		LinearLayout toastLayout = (LinearLayout) toast.getView();
		TextView toastTV = (TextView) toastLayout.getChildAt(0);
		if ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE) {
			toastTV.setTextSize(20);
		}else {
			toastTV.setTextSize(25);
		}

		toast.show();
	}

	private static char GetAlterChar(char pC) {
		if ((int) pC == 32) {
			return ' ';
		}

		char[] charA = { 'à', 'á', 'ạ', 'ả', 'ã', 'â', 'ầ', 'ấ', 'ậ', 'ẩ', 'ẫ',
				'ă', 'ằ', 'ắ', 'ặ', 'ẳ', 'ẵ' };// 0->16
		char[] charE = { 'ê', 'ề', 'ế', 'ệ', 'ể', 'ễ', 'è', 'é', 'ẹ', 'ẻ', 'ẽ' };// 17->27
		char[] charI = { 'ì', 'í', 'ị', 'ỉ', 'ĩ' };// i 28->32
		char[] charO = { 'ò', 'ó', 'ọ', 'ỏ', 'õ', 'ô', 'ồ', 'ố', 'ộ', 'ổ', 'ỗ',
				'ơ', 'ờ', 'ớ', 'ợ', 'ở', 'ỡ' };// o 33->49
		char[] charU = { 'ù', 'ú', 'ụ', 'ủ', 'ũ', 'ư', 'ừ', 'ứ', 'ự', 'ử', 'ữ' };// u
		char[] charY = { 'ỳ', 'ý', 'ỵ', 'ỷ', 'ỹ' };// y 61->65
		char[] charD = { 'đ', ' ' }; // 66-67

		String charact = String.valueOf(charA, 0, charA.length)
				+ String.valueOf(charE, 0, charE.length)
				+ String.valueOf(charI, 0, charI.length)
				+ String.valueOf(charO, 0, charO.length)
				+ String.valueOf(charU, 0, charU.length)
				+ String.valueOf(charY, 0, charY.length)
				+ String.valueOf(charD, 0, charD.length);

		char tmp = pC;

		int i = 0;
		while (i < charact.length() && charact.charAt(i) != tmp) {
			i++;
		}
		if (i < 0 || i > 67)
			return pC;

		if (i == 66) {
			return 'd';
		}
		if (i >= 0 && i <= 16) {
			return 'a';
		}
		if (i >= 17 && i <= 27) {
			return 'e';
		}
		if (i >= 28 && i <= 32) {
			return 'i';
		}
		if (i >= 33 && i <= 49) {
			return 'o';
		}
		if (i >= 50 && i <= 60) {
			return 'u';
		}
		if (i >= 61 && i <= 65) {
			return 'y';
		}
		return pC;
	}

	@SuppressLint("DefaultLocale")
	public static String ConvertString(String str) {

		String convertString = str.toLowerCase();
		int stringLength = convertString.length();
		for (int i = 0; i < stringLength; i++) {
			char tmp = convertString.charAt(i);
			if ((int) tmp < 97 || tmp > 122) {
				char getChar = GetAlterChar(tmp);
				if ((int) tmp != 32)
					convertString = convertString.replace(tmp, getChar);
			}
		}
		return convertString;
	}

	@SuppressLint("SimpleDateFormat")
	public static String StringToDate(String date) {
		try {
			SimpleDateFormat targetFormat = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");
			Date d = targetFormat.parse(date);
			SimpleDateFormat targetFormat1 = new SimpleDateFormat(
					"yyyy/MM/dd HH:mm:ss a");
			return targetFormat1.format(d);
		} catch (Exception e) {
			return "";
		}
	}

	public static String GetLocalIpAddress(Context context) {
		String ip4Address = "";
		try {

			ConnectivityManager connManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWifi = connManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

			if (mWifi.isConnected()) {
				final Inet4Address inet4Address = getWifiInetAddress(context,
						Inet4Address.class);
				if (inet4Address == null) {
					ip4Address = "127.0.0.1";
				} else {
					ip4Address = String.valueOf(inet4Address);
					if (ip4Address.substring(0, 1).equals("/")) {
						ip4Address = ip4Address.substring(1);
					}
				}
			}
		} catch (Exception ex) {
			ip4Address = "127.0.0.1";
		}
		return ip4Address;
	}

	public static Enumeration<InetAddress> getWifiInetAddresses(
			final Context context) {
		final WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		final WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		final String macAddress = wifiInfo.getMacAddress();
		final String[] macParts = macAddress.split(":");
		final byte[] macBytes = new byte[macParts.length];
		for (int i = 0; i < macParts.length; i++) {
			macBytes[i] = (byte) Integer.parseInt(macParts[i], 16);
		}
		try {
			final Enumeration<NetworkInterface> e = NetworkInterface
					.getNetworkInterfaces();
			while (e.hasMoreElements()) {
				final NetworkInterface networkInterface = e.nextElement();
				if (Arrays.equals(networkInterface.getHardwareAddress(),
						macBytes)) {
					return networkInterface.getInetAddresses();
				}
			}
		} catch (SocketException e) {
			Log.wtf("WIFIIP",
					"Unable to NetworkInterface.getNetworkInterfaces()");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends InetAddress> T getWifiInetAddress(
			final Context context, final Class<T> inetClass) {
		try {

			final Enumeration<InetAddress> e = getWifiInetAddresses(context);
			while (e.hasMoreElements()) {
				final InetAddress inetAddress = e.nextElement();
				if (inetAddress.getClass() == inetClass) {
					return (T) inetAddress;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public static String SettingPathImage(String strChar){
		String strValue = strChar.replace(Constant.APP_ROOT_IMAGE, AppConfig.XSTOCK_URL_IMAGE);
		return strValue;
	}
}
