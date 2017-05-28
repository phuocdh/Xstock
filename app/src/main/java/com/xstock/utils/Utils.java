package com.xstock.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.android.volley.Response;
import com.neolab.jinjermanagerment.R;
import com.neolab.jinjermanagerment.model.Profile;
import com.neolab.jinjermanagerment.new_manager.VolleyErrorHandler;
import com.neolab.jinjermanagerment.new_manager.features.authentication.login.ManagerLoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nguyen on 25/12/2015.
 */
public class Utils {

    public static final String language = "JPN";
    /**
     * ibeacon
     */
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static Dialog dialog;

    public static void showDialogPickerTime1238(Context context, final TextView txtHour, final TextView txtMinute, boolean isOnlyMinute, boolean to60m) {
        int TIME_PICKER_INTERVAL = 5;
        int hour = 24;
        int minute = 60;

        if (isOnlyMinute) {
            minute = 61;
        }
        final String valuesHour[] = new String[hour];
//        final Map<String, Integer> valuesMinute = new HashMap<>();
        LinkedHashMap valuesMinute = new LinkedHashMap();

        for (int i = 0; i < hour; i++) {
            valuesHour[i] = String.format("%02d", i);
        }
        if (to60m) {
            String fmstring = context.getString(R.string.rounded);
//            for (int i = 5; i < minute+5; i += TIME_PICKER_INTERVAL) {

            valuesMinute.put(context.getString(R.string.not_rounded), 0);
            valuesMinute.put(String.format(fmstring, 5), 1);
            valuesMinute.put(String.format(fmstring, 10), 2);
            valuesMinute.put(String.format(fmstring, 15), 3);
            valuesMinute.put(String.format(fmstring, 20), 4);
            valuesMinute.put(String.format(fmstring, 30), 5);
            valuesMinute.put(context.getString(R.string.rounded_1h), 6);
//            }
        } else {
//            for (int i = 0; i < minute; i += TIME_PICKER_INTERVAL) {
//                valuesMinute.put(String.format("%02d", i));
//            }
        }
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_number_picker_minute);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        // set dialog component
        TextView btnOk = (TextView) dialog.findViewById(R.id.btn_ok);
        TextView btnCancel = (TextView) dialog.findViewById(R.id.btn_cancel);
        final NumberPicker pickerHour = (NumberPicker) dialog.findViewById(R.id.npHour);
        final NumberPicker pickerMinute = (NumberPicker) dialog.findViewById(R.id.npMinute);
        if (isOnlyMinute) {
            pickerHour.setVisibility(View.GONE);
        } else {
            pickerHour.setWrapSelectorWheel(false);
            pickerHour.setFadingEdgeLength(20);
            pickerHour.setMaxValue(hour - 1);
            pickerHour.setMinValue(0);
//        Utils.setDividerColor(picker, context.getResources().getDrawable(R.drawable.number_picker));
            pickerHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            pickerHour.setDisplayedValues(valuesHour);
            String h = txtHour.getText().toString();
            if (h.equals("--")) {
                h = "00";
            }
            pickerHour.setValue(Integer.parseInt("".equals(h) ? "0" : h));
        }


        Set<String> keys = valuesMinute.keySet();
        String[] array = keys.toArray(new String[keys.size()]);
        pickerMinute.setWrapSelectorWheel(false);
        pickerMinute.setFadingEdgeLength(20);
        pickerMinute.setMaxValue(array.length - 1);
        pickerMinute.setMinValue(0);
//        Utils.setDividerColor(picker, context.getResources().getDrawable(R.drawable.number_picker));
        pickerMinute.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerMinute.setDisplayedValues(array);
        String m = txtMinute.getText().toString().length() > 1 ? txtMinute.getText().toString() : "0" + txtMinute.getText().toString().trim();
//        Log.d("tessst", "234234234 b 234    "+m);
//        if(m.equals("--") || m.equals(context.getString(R.string.not_rounded))){
//            m="00";
//        } else if(m.equals(context.getString(R.string.rounded_1h))) {
//            m="60";
//        }
//        Log.d("tes23423423423sst", "234234234 b 234    "+m);
        pickerMinute.setValue(((Integer) valuesMinute.get(m)).intValue());

        // set listener
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnOk.setOnClickListener(v -> {
            int pickerHourValue = pickerHour.getValue();
            int pickerMinuteValue = pickerMinute.getValue();
            if (txtHour != null)
                txtHour.setText(valuesHour[pickerHourValue]);
            txtMinute.setText(array[pickerMinuteValue]);
            dialog.dismiss();
        });
        // show
        dialog.show();
    }

    public interface IDialogTimeChanged {
        void onTimeChanged(double hour, double min);
    }

    public static void showDialogPickerTime48h(Context context,
                                               final TextView tvTime, boolean isOnlyMinute, boolean isText,
                                               int TIME_PICKER_INTERVAL, IDialogTimeChanged timeChanged) {
        int hour = 48;
        int minute = 60;

        if (isOnlyMinute) {
            minute = 61;
        }

        if (isText) {
            minute = 9;
        }
        final String valuesHour[] = new String[hour];
        final List<String> valuesMinute = new ArrayList<>();

        for (int i = 0; i < hour; i++) {
            valuesHour[i] = String.format("%02d", i);
        }
        if (!isText) {
            for (int i = 0; i < minute; i += TIME_PICKER_INTERVAL) {
                valuesMinute.add(String.format("%02d", i));
            }
        } else {
            for (int i = 0; i < minute; i += TIME_PICKER_INTERVAL) {
                valuesMinute.add(String.format("0.%d", (i + 1)));
            }
        }
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_number_picker_minute);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        // set dialog component
        TextView btnOk = (TextView) dialog.findViewById(R.id.btn_ok);
        TextView btnCancel = (TextView) dialog.findViewById(R.id.btn_cancel);
        final NumberPicker pickerHour = (NumberPicker) dialog.findViewById(R.id.npHour);
        final NumberPicker pickerMinute = (NumberPicker) dialog.findViewById(R.id.npMinute);
        if (isOnlyMinute || isText) {
            pickerHour.setVisibility(View.GONE);
        } else {
            pickerHour.setWrapSelectorWheel(false);
            pickerHour.setFadingEdgeLength(20);
            pickerHour.setMaxValue(hour - 1);
            pickerHour.setMinValue(0);
            pickerHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            pickerHour.setDisplayedValues(valuesHour);
            pickerHour.setValue(TextUtils.isEmpty(tvTime.getText()) ? 0
                    : (Integer.parseInt(getTimeFromString(isText, tvTime.getText().toString().trim(), true))));
        }

        pickerMinute.setWrapSelectorWheel(false);
        pickerMinute.setFadingEdgeLength(20);
        pickerMinute.setMaxValue((minute / TIME_PICKER_INTERVAL) - 1);
        pickerMinute.setMinValue(0);
        pickerMinute.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        pickerMinute.setDisplayedValues(valuesMinute.toArray(new String[0]));
        if (!isText) {
            pickerMinute.setValue(TextUtils.isEmpty(tvTime.getText()) ? 0 : (Integer.parseInt(getTimeFromString(isText, tvTime.getText().toString().trim(), false)) / TIME_PICKER_INTERVAL));
        } else {
            pickerMinute.setValue(TextUtils.isEmpty(tvTime.getText()) ? 0 : ((Integer.parseInt(getTimeFromString(isText, tvTime.getText().toString().trim(), false)) - 1) / TIME_PICKER_INTERVAL));
        }


        // set listener
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnOk.setOnClickListener(v -> {
            int pickerHourValue = pickerHour.getValue();
            int pickerMinuteValue = pickerMinute.getValue();
            if (!isText) {
                String time = valuesHour[pickerHourValue] + ":" + valuesMinute.get(pickerMinuteValue);
                tvTime.setText(time);
            } else {
                tvTime.setText(valuesMinute.get(pickerMinuteValue));
            }
            if (timeChanged != null) {
                timeChanged.onTimeChanged(Double.parseDouble(valuesHour[pickerHourValue]), Double.parseDouble(valuesMinute.get(pickerMinuteValue)));
            }
//            if (listener != null)
//                listener.onDialogActionOK();
            dialog.dismiss();
        });
        dialog.show();

    }

    /**
     * vuvt
     *
     * @param str
     * @param isHour
     * @return
     */
    private static String getTimeFromString(boolean isText, String str, boolean isHour) {
        if (!isText) {
            String[] times = str.split(":");
            if (times.length == 0) {
                return "";
            }

            if (isHour) {
                return times[0].trim();
            } else {
                return times[1].trim();
            }
        } else {
            return str.replace("0.", "").trim();
        }
    }


    // Convert unix time to date
    public static String convertUnix2Date(long unix) {
        if (unix == 0) return "";
        String result = "";
        Date date = new Date(TimeUnit.SECONDS.toMillis(unix));
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        f.setTimeZone(TimeZone.getDefault());
        result = f.format(date);
        return result;
    }

    public static String convertUnix2DateTime(long unix, String dateFormat) {
        if (unix == 0) return "";
        String result = "";
        Date date = new Date(TimeUnit.SECONDS.toMillis(unix));
        SimpleDateFormat f = new SimpleDateFormat(dateFormat);
        f.setTimeZone(TimeZone.getDefault());
        result = f.format(date);
        return result;
    }


    /**
     * check valid email address
     *
     * @param email your email
     * @return boolean
     * @author VuVT
     */
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * show Dialog with OK button only
     *
     * @param context app context
     * @param message message to show
     * @return Dialog
     */
    public static Dialog showDialog(Context context, String message) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(android.R.string.ok,
                        (dialog1, which) -> {
                            // do nothing
                        }).show();
    }

    /**
     * show loading dialog when call API
     *
     * @param context app context
     */
    public static void showLoadingDialog(Context context) {
        if (null == context) return;
        if (dialog != null) {
            if (dialog.isShowing()) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                }
            }
            dialog = null;
        }
        dialog = new Dialog(context, android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //here we set layout of progress dialog
        dialog.setContentView(R.layout.custom_progress_dialog);
        dialog.show();
    }

    /**
     * dismiss loading dialog when call API done
     */
    public static void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * convert dp to pixel
     *
     * @param dp dp
     * @return int pixel
     * @author VUVT
     */
    public static int convertDpToPixel(double dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * get profile
     *
     * @param context app context
     * @return profile Object
     */
    public static Profile getProfile(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        Profile user = new Profile();
        user.setId(pref.getString(Constants.SP_ID_USER, ""));
        user.setUserName(pref.getString(Constants.SP_USER_NAME, ""));
        user.setToken(pref.getString(Constants.SP_TOKEN, ""));
        user.setAuthTokenChat(pref.getString(Constants.SP_AUTH_TOKEN_CHAT, ""));
        user.setUserIdTokenChat(pref.getString(Constants.SP_USER_ID_TOKEN_CHAT, ""));
        return user;
    }


    public static Response.ErrorListener getResponseErrorListener(Context context, Dialog dialog) {
        if (dialog != null) dialog.dismiss();
        return getResponseErrorListener(context);
    }

    public static Response.ErrorListener getResponseErrorListener(Context context) {
        return error -> {
            VolleyErrorHandler.volleyErrorHandler(context, error);
            Log.d("API error >>> ", error.toString());
        };
    }


    public static String getDateFromTimestampJapan(String timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(calendar.getTime());
    }

    public static String getDateFromTimestamp(String timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(calendar.getTime());
    }

    /**
     * get time from timestamp
     *
     * @param timestamp String timestamp get from server
     * @return String with format HH:mm
     */
    public static String getTimeFromTimestamp(String timestamp) {
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.valueOf(timestamp)*1000);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        return ((h < 10 ? ("0" + h) : h) + ":" + (m < 10 ? ("0" + m) : m));*/

        if (timestamp == null || timestamp.isEmpty() || "0".equals(timestamp)) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.format(calendar.getTime());
    }

    @SuppressLint("DefaultLocale")
    public static String getTimeFromTimestamp(long date, long unix) {
        if (unix == 0) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(unix));
        if (android.text.format.DateFormat.format("dd-MM-yyyy"
                , new Date(TimeUnit.SECONDS.toMillis(date)))
                .equals(android.text.format.DateFormat.format("dd-MM-yyyy"
                        , new Date(TimeUnit.SECONDS.toMillis(unix))))) {
            return (String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))
                    + ":" + String.format("%02d", calendar.get(Calendar.MINUTE)));
        } else {
            return (String.format("%02d", (calendar.get(Calendar.HOUR_OF_DAY)
                    + 24)) + ":" + String.format("%02d", calendar.get(Calendar.MINUTE)));
        }
    }

    @SuppressLint("DefaultLocale")
    public static String getTimeFrom24h(long createAtDate, long breakTime, long returnTime) {
        if (returnTime == 0) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(TimeUnit.SECONDS.toMillis(createAtDate));
        if (android.text.format.DateFormat.format("dd-MM-yyyy"
                , new Date(TimeUnit.SECONDS.toMillis(createAtDate)))
                .equals(android.text.format.DateFormat.format("dd-MM-yyyy"
                        , new Date(TimeUnit.SECONDS.toMillis(returnTime))))) {
            return (String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))
                    + ":" + String.format("%02d", calendar.get(Calendar.MINUTE)));
        } else {
            return (String.format("%02d", (calendar.get(Calendar.HOUR_OF_DAY) + 24))
                    + ":" + String.format("%02d", calendar.get(Calendar.MINUTE)));
        }
    }


    /**
     * Header use for volley
     */
    public static Map<String, String> headersVolley() {
        Map<String, String> map = new HashMap<>();
        map.put("Jinjer-Timezone-Offset", TimeZone.getDefault().getID());
        map.put("Content-Type", "application/json");
        return map;
    }


    /**
     * Check valid token
     */
    public static final boolean isResponseOK(Context context, int code) {
        if (code == 200) return true;
        if (code == 401) {
//            Intent intent = new Intent(context, LoginActivity.class);
            Intent intent = new Intent(context, ManagerLoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        return false;
    }


    // Calculate age
    public static int calculateAge(String birthDate) {
        int age = 0;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar dob = Calendar.getInstance();
        dob.setTime(convertedDate);
        Calendar today = Calendar.getInstance();
        age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) <= dob.get(Calendar.DAY_OF_YEAR))
            age--;
        if (age < 0) age = 0;
        return age;
    }


    public static void DialogConfirm(Context context, String msgContent) {
        DialogConfirm(context, msgContent, null, null, null, null, 1);
    }

    public static void DialogConfirm(Context context, String msgContent, String strOk, String strCancel, IResponseOk resOk, IResponseCancel resCancel, int numButton) {
        Dialog dlConfirm = new Dialog(context);
        dlConfirm.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlConfirm.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dlConfirm.setContentView(R.layout.dialog_show_info);
        dlConfirm.setCancelable(false);
        dlConfirm.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dlConfirm.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dlConfirm.getWindow().setAttributes(lp);
        ((TextView) dlConfirm.findViewById(R.id.tv_content)).setText(TextUtils.isEmpty(msgContent) ? "" : msgContent);
        Button btnOk = (Button) dlConfirm.findViewById(R.id.btn_ok);
        if (!TextUtils.isEmpty(strOk)) btnOk.setText(strOk);
        btnOk.setOnClickListener(v -> {
            dlConfirm.dismiss();
            if (resOk != null) resOk.onClickOk();
        });
        Button btnCancel = (Button) dlConfirm.findViewById(R.id.btn_cancel);
        if (!TextUtils.isEmpty(strCancel)) btnCancel.setText(strCancel);
        btnCancel.setOnClickListener(v -> {
            dlConfirm.dismiss();
            if (resCancel != null) resCancel.onClickCancel();
        });
        switch (numButton) {
            case 1:
                btnCancel.setVisibility(View.GONE);
        }
        dlConfirm.show();
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null
                || activity.getCurrentFocus() == null
                || activity.getCurrentFocus().getWindowToken() == null) return;
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public interface IResponseOk {
        void onClickOk();
    }

    public interface IResponseCancel {
        void onClickCancel();
    }

    public static HashMap<String, String> createRouteTypeString() {
        HashMap<String, String> routeType = new HashMap<>();
        routeType.put("request_shift", "スケジュール承認");
        routeType.put("request_pre_over_times", "残業承認");
        routeType.put("request_timecard", "打刻管理");
        routeType.put("request_over_times", "残業管理");
        routeType.put("request_fix_and_create_time_card", "打刻修正承認");
        routeType.put("request_work_change", "休日出勤（振）承認");
        routeType.put("request_work_recoup", "休日出勤（代）承認");
        routeType.put("request_day_off", "休日・休暇管理");
        return routeType;
    }
}
