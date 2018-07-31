package cn.yznu.common.dialog.callback;


import cn.yznu.common.dialog.res.values.ColorRes;
import cn.yznu.common.dialog.res.values.DimenRes;

/**
 * Created by hupei on 2016/3/10 15:06.
 */
public abstract class ProviderHeader extends Provider {

    public int getTextSize() {
        return DimenRes.headerTextSize;
    }

    public int getHeight() {
        return DimenRes.headerHeight;
    }

    public int getTextColor() {
        return ColorRes.title;
    }
}

