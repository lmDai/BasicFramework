package cn.yznu.basicframework.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;
import cn.yznu.basicframework.model.bean.TestBean;
import cn.yznu.basicframework.ui.adapter.IndexAdapter;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class ThreeFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private IndexAdapter indexAdapter;


    public static ThreeFragment newInstance() {

        Bundle bundle = new Bundle();

        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_three;
    }

    @Override
    protected void initView() {
        List<TestBean> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new TestBean());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        indexAdapter = new IndexAdapter(data);
        indexAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(indexAdapter);
    }
}
