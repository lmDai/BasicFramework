package cn.yznu.basicframework.ui.fragment.main;

import android.os.Bundle;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/7/31
 * 描述：掌上宝骏顶部
 * 版本：1.0
 * 修订历史：
 */
public class PalmTopFragment extends BaseFragment{
    public static PalmTopFragment newInstance() {
        Bundle bundle = new Bundle();
        PalmTopFragment fragment = new PalmTopFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_palm_top;
    }

    @Override
    protected void initView() {

    }
}
