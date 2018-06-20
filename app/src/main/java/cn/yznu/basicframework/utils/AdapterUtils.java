package cn.yznu.basicframework.utils;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 作者：uiho_mac
 * 时间：2018/6/20
 * 描述：AdapterUtils
 * 版本：1.0
 * 修订历史：
 */
public class AdapterUtils {
    private static final AdapterUtils ourInstance = new AdapterUtils();

    public static AdapterUtils getInstance() {
        return ourInstance;
    }

    private AdapterUtils() {
    }

    public <T extends BaseQuickAdapter> T buildDefault(T adapter, RecyclerView recyclerView) {
        adapter.bindToRecyclerView(recyclerView);
        adapter.setEnableLoadMore(true);
        return adapter;
    }
}
