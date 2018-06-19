package cn.yznu.basicframework.ui.fragment;

import android.os.Bundle;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class SecondFragment extends BaseFragment {
    public static SecondFragment newInstance() {

        Bundle bundle = new Bundle();

        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {

    }
}
