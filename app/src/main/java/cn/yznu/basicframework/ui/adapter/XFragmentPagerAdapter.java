package cn.yznu.basicframework.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：uiho_mac
 * 时间：2018/7/31
 * 描述：将只保留当前页面，当页面离开视线后，就会被消除，释放其资源；
 * 而在页面需要显示时，生成新的页面
 * 版本：1.0
 * 修订历史：
 */
public class XFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public XFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
