package cn.yznu.common.dialog.callback;


import cn.yznu.common.dialog.SuperDialog;
import cn.yznu.common.dialog.res.values.ColorRes;

/**
 * Created by hupei on 2016/3/10 15:05.
 */
public abstract class ProviderFooterPositive extends ProviderFooter {
    public abstract SuperDialog.OnClickPositiveListener getOnPositiveListener();

    public int getTextColor() {
        return ColorRes.positiveButton;
    }
}
