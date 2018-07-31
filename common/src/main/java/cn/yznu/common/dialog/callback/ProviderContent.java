package cn.yznu.common.dialog.callback;


import cn.yznu.common.dialog.res.values.ColorRes;
import cn.yznu.common.dialog.res.values.DimenRes;

/**
 * Created by hupei on 2016/3/10 15:09.
 */
public abstract class ProviderContent {
    public enum Mode {
        SINGLE, MULTIPLE, INPUT, PROGRESSBAR
    }

    public abstract Mode getMode();

    public Object getItems() {
        return null;
    }

    public int getTextSize() {
        return DimenRes.contentTextSize;
    }

    public int getTextColor() {
        return ColorRes.content;
    }
}
