package cn.yznu.common.dialog.callback;


import cn.yznu.common.dialog.SuperDialog;

/**
 * Created by hupei on 2017/3/21
 */
public abstract class ProviderContentMultiple extends ProviderContent {
    public abstract SuperDialog.OnItemClickListener getItemClickListener();

    public abstract void dismiss();

    public abstract int getItemHeight();

    @Override
    public Object getItems() {
        return null;
    }

    @Override
    public ProviderContent.Mode getMode() {
        return ProviderContent.Mode.MULTIPLE;
    }
}
