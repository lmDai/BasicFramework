package cn.yznu.basicframework.utils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * 作者：uiho_mac
 * 时间：2018/3/13
 * 描述：分页加载
 * 版本：1.0
 * 修订历史：新建
 */

public class PageResultUtils {
    private static final PageResultUtils ourInstance = new PageResultUtils();

    public static PageResultUtils getInstance() {
        return ourInstance;
    }

    private PageResultUtils() {
    }

    public <T> int handleResult(List<T> list, BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter, SmartRefreshLayout smartRefreshLayout, boolean isRefresh, int page) {
        if (isRefresh) {
            //刷新
            baseQuickAdapter.setNewData(list);
            smartRefreshLayout.finishRefresh(0, true);
            page++;
        } else {
            //加载更多
            //加载出错在RxJava的onError里
            baseQuickAdapter.loadMoreComplete();
            smartRefreshLayout.finishLoadMore();
            if (list == null || list.size() <= 0) {
                baseQuickAdapter.loadMoreEnd(false);
                smartRefreshLayout.setEnableLoadMore(false);
            } else {
                baseQuickAdapter.addData(list);
                page++;
            }
        }

        return page;
    }

    public <T> void handleError(BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter, SmartRefreshLayout smartRefreshLayout, boolean isRefresh) {
        if (isRefresh) {
            smartRefreshLayout.finishRefresh(false);
        } else {
            baseQuickAdapter.loadMoreFail();
        }
    }
}
