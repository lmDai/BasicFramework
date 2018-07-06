package cn.yznu.common.indicator;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：viewpager 指示器
 * 版本：1.0
 * 修订历史：
 */

public class DisplayUtils {
    public static int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static int pxToDp(float px) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().getDisplayMetrics());
    }
}
