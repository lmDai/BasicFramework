package cn.yznu.basicframework.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;
import cn.yznu.basicframework.model.bean.TestBean;
import cn.yznu.basicframework.ui.adapter.IndexAdapter;
import cn.yznu.basicframework.utils.AdapterUtils;
import cn.yznu.basicframework.utils.PageResultUtils;
import cn.yznu.basicframework.utils.RecyclerViewUtils;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：首页
 * 版本：1.0
 * 修订历史：
 */
public class IndexFragment extends BaseFragment implements OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefresh;
    private IndexAdapter indexAdapter;
    private List<TestBean> testBeans = new ArrayList<>();

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
        initData();
        indexAdapter = new IndexAdapter(testBeans);
        RecyclerViewUtils.getInstance().buildDefault(mContext, recyclerView);
        AdapterUtils.getInstance().buildDefault(indexAdapter, recyclerView);
        smartRefresh.setOnRefreshListener(this);
        indexAdapter.setOnLoadMoreListener(this, recyclerView);
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            testBeans.add(new TestBean());
        }
    }

    private int page = 0;

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        indexAdapter.setEnableLoadMore(false);
        page = PageResultUtils.getInstance().handleResult(testBeans, indexAdapter, smartRefresh, true, page);
    }


    @Override
    public void onLoadMoreRequested() {
        if (page>1){
            page = PageResultUtils.getInstance().handleResult(null, indexAdapter, smartRefresh, false, page);
        }else {
            page = PageResultUtils.getInstance().handleResult(testBeans, indexAdapter, smartRefresh, false, page);
        }
    }
}
