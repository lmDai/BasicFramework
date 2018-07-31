package cn.yznu.basicframework.ui.fragment.main;

import android.os.Bundle;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/7/31
 * 描述：车友社交底部
 * 版本：1.0
 * 修订历史：
 */
public class SocailBottomFragment extends BaseFragment{
    public static SocailBottomFragment newInstance() {
        Bundle bundle = new Bundle();
        SocailBottomFragment fragment = new SocailBottomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_social_bottom;
    }

    @Override
    protected void initView() {

    }
}
