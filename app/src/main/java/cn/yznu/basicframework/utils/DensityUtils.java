package cn.yznu.basicframework.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import cn.yznu.basicframework.app.Constants;

/**
 * 作者：uiho_mac
 * 时间：2018/7/3
 * 描述：屏幕适配utils
 * 版本：1.0
 * 修订历史：
 */
public class DensityUtils {
    private static float appDensity;
    private static float appScaledDensity;
    private static DisplayMetrics appDisplayMetrics;

    public static void setDensity(@NonNull final Application application) {
        appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (appDensity == 0) {
            appDensity = appDisplayMetrics.density;
            appScaledDensity = appDisplayMetrics.scaledDensity;
            //添加字体变化的监听
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    //字体变化后，将appScaleDensity重新赋值
                    if (newConfig != null && newConfig.fontScale > 0) {
                        appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
    }

    //此方法在BaseActivity中做初始化
    public static void setDefault(Activity activity) {
        setAppOrientation(activity, Constants.WIDTH);
    }

    //此方法用于某个activity里面更改适配方向
    public static void setOrientation(Activity activity, String orientation) {
        setAppOrientation(activity, orientation);
    }

    private static void setAppOrientation(@NonNull Activity activity, String orientation) {
        float targetDensity;
        if (TextUtils.equals(orientation, Constants.HEIGHT)) {
            targetDensity = appDisplayMetrics.heightPixels / 667;
        } else {
            targetDensity = appDisplayMetrics.widthPixels / 360;
        }
        float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
        int targetDensityDpi = (int) (160 * targetDensity);
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}
