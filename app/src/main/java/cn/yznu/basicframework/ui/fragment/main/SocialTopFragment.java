package cn.yznu.basicframework.ui.fragment.main;

import android.os.Bundle;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/7/31
 * 描述：车友社交顶部
 * 版本：1.0
 * 修订历史：
 */
public class SocialTopFragment extends BaseFragment {
    public static SocialTopFragment newInstance() {
        Bundle bundle = new Bundle();
        SocialTopFragment fragment = new SocialTopFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_social_top;
    }

    @Override
    protected void initView() {

    }
}
