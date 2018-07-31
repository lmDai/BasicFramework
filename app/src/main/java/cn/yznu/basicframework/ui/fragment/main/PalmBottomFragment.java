package cn.yznu.basicframework.ui.fragment.main;

import android.os.Bundle;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/7/31
 * 描述：掌上宝骏底部
 * 版本：1.0
 * 修订历史：
 */
public class PalmBottomFragment extends BaseFragment {
    public static PalmBottomFragment newInstance() {
        Bundle bundle = new Bundle();
        PalmBottomFragment fragment = new PalmBottomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_palm_bottom;
    }

    @Override
    protected void initView() {

    }
}
