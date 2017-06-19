package com.xstock.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import com.xstock.R;

public class Utils {
    private static Dialog dialog;

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
}
