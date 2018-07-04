package cn.yznu.common.toasthelper;

import android.content.Context;

/**
 * 作者：uiho_mac
 * 时间：2018/7/3
 * 描述：toast工具类
 * 版本：1.0
 * 修订历史：
 */
public class ToastHelper {
    private static long lastClickTime;

    /**
     * 防止重复点击
     *
     * @return 是否重复点击
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    public static void showToast(Context mContext, String msg, int type) {
        TastyToast.makeText(mContext, msg, TastyToast.LENGTH_SHORT, type).show();
    }
}
