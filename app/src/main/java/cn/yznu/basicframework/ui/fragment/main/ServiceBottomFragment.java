package cn.yznu.basicframework.ui.fragment.main;

import android.os.Bundle;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/7/31
 * 描述：生活服务底部
 * 版本：1.0
 * 修订历史：
 */
public class ServiceBottomFragment extends BaseFragment {
    public static ServiceBottomFragment newInstance() {
        Bundle bundle = new Bundle();
        ServiceBottomFragment fragment = new ServiceBottomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void getBundleExtras(Bundle bundle) {

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_service_bottom;
    }

    @Override
    protected void initView() {

    }
}
