package cn.yznu.common.dialog.callback;


import cn.yznu.common.dialog.SuperDialog;

/**
 * Created by hupei on 2017/3/21
 */
public abstract class ProviderFooterPositiveInput extends ProviderFooterPositive {
    @Override
    public final SuperDialog.OnClickPositiveListener getOnPositiveListener() {
        return null;
    }

    public abstract SuperDialog.OnClickPositiveInputListener getOnPositiveInputListener();
}
