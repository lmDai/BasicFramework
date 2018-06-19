package cn.yznu.basicframework.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：uiho_mac
 * 时间：2018/3/13
 * 描述：Toast工具类 showToast
 * 版本：1.0
 * 修订历史：新建
 */
public class EventUtils {
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

    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int res) {
        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
    }
}
