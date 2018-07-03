package cn.yznu.common.loadviewhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * 作者：uiho_mac
 * 时间：2018/7/3
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public interface IVaryViewHelper {
    View getCurrentLayout();

    void restoreView();

    void showLayout(@NonNull View view);

    void showLayout(int layoutId);

    View inflate(int layoutId);

    Context getContext();

    View getView();

    void release();
}
