package cn.yznu.basicframework.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;
import cn.yznu.basicframework.model.bean.TestBean;
import cn.yznu.basicframework.ui.adapter.IndexAdapter;
import cn.yznu.common.toasthelper.TastyToast;
import cn.yznu.common.toasthelper.ToastHelper;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：首页
 * 版本：1.0
 * 修订历史：
 */
public class IndexFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    private IndexAdapter indexAdapter;
    private int mNextRequestPage = 1;
    private static final int PAGE_SIZE = 6;

    public static IndexFragment newInstance() {
        Bundle bundle = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        showBack = false;
        setTopTitle(getResources().getString(R.string.str_index));
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        initAdapter();
        initRefreshLayout();
        refresh();
    }

    private void initAdapter() {
        indexAdapter = new IndexAdapter(null);
        indexAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, recyclerView);
        indexAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
//        indexAdapter.setPreLoadNumber(3);
        recyclerView.setAdapter(indexAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                ToastHelper.showToast(mContext, position + "", TastyToast.INFO);

            }
        });
    }

    private void initRefreshLayout() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        mNextRequestPage = 1;
        indexAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<TestBean> data) {
                setData(true, data);
                indexAdapter.setEnableLoadMore(true);
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void fail(Exception e) {
                ToastHelper.showToast(mContext, getString(R.string.net_error) + "", TastyToast.ERROR);
                indexAdapter.setEnableLoadMore(true);
                swipeLayout.setRefreshing(false);
            }
        }).start();
    }

    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<TestBean> data) {
                setData(false, data);
            }

            @Override
            public void fail(Exception e) {
                indexAdapter.loadMoreFail();
                ToastHelper.showToast(mContext, getString(R.string.net_error) + "", TastyToast.ERROR);
            }
        }).start();
    }

    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            indexAdapter.setNewData(data);
        } else {
            if (size > 0) {
                indexAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            indexAdapter.loadMoreEnd(false);
            ToastHelper.showToast(mContext, "no more data", TastyToast.ERROR);

        } else {
            indexAdapter.loadMoreComplete();
        }
    }
}

interface RequestCallBack {
    void success(List<TestBean> data);

    void fail(Exception e);
}

class Request extends Thread {
    private static final int PAGE_SIZE = 6;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    public Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        if (mPage == 2 && mFirstError) {
            mFirstError = false;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.fail(new RuntimeException("fail"));
                }
            });
        } else {
            int size = PAGE_SIZE;
            if (mPage == 1) {
                if (mFirstPageNoMore) {
                    size = 1;
                }
                mFirstPageNoMore = !mFirstPageNoMore;
                if (!mFirstError) {
                    mFirstError = true;
                }
            } else if (mPage == 4) {
                size = 1;
            }

            final int dataSize = size;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    List<TestBean> testBeans = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        testBeans.add(new TestBean());
                    }
                    mCallBack.success(testBeans);
                }
            });
        }
    }
}
