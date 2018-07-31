package cn.yznu.basicframework.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.app.AppManager;
import cn.yznu.common.dialog.SuperDialog;

/**
 * 作者：uiho_mac
 * 时间：2018/3/1
 * 描述：系统工具类
 * 版本：1.0
 * 修订历史：
 */

public class SystemUtils {

    private static final String TAG = SystemUtils.class.getSimpleName();


    /**
     * 获取App版本
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
            String versionName = pi.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static double scale;
    static int screenWidth = 0, screenHeight = 0;

    public static void init(Context context) {
        scale = context.getResources().getDisplayMetrics().density;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dip2px(float dipValue) {
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    /**
     * 退出程序
     *
     * @param context 上下文
     */
    public static void Exit(final AppCompatActivity context) {

        new SuperDialog.Builder(context).setRadius(10)
                .setWidth(0.80f)
                .setBackgroundColor(context.getResources().getColor(R.color.color_white))
                .setTitle(context.getResources().getString(R.string.str_info))
                .setMessage(context.getResources().getString(R.string.str_exit))
                .setNegativeButton(context.getResources().getString(R.string.str_wander), null)
                .setPositiveButton(context.getResources().getString(R.string.str_ensure), new SuperDialog.OnClickPositiveListener() {
                    @Override
                    public void onClick(View v) {
                        AppManager.getAppManager().AppExit(context, false);
                    }
                }).build();
    }
}
