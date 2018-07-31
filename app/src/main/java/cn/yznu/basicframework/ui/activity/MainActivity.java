package cn.yznu.basicframework.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseActivity;
import cn.yznu.basicframework.ui.adapter.XFragmentPagerAdapter;
import cn.yznu.basicframework.ui.fragment.main.BussinessBottomFragment;
import cn.yznu.basicframework.ui.fragment.main.BussinessTopFragment;
import cn.yznu.basicframework.ui.fragment.main.PalmBottomFragment;
import cn.yznu.basicframework.ui.fragment.main.PalmTopFragment;
import cn.yznu.basicframework.ui.fragment.main.ServiceBottomFragment;
import cn.yznu.basicframework.ui.fragment.main.ServiceTopFragment;
import cn.yznu.basicframework.ui.fragment.main.SocailBottomFragment;
import cn.yznu.basicframework.ui.fragment.main.SocialTopFragment;
import cn.yznu.basicframework.ui.widget.BaseLinkPageChangeListener;
import cn.yznu.basicframework.utils.SystemUtils;
import cn.yznu.common.widget.WrapContentViewPager;


public class MainActivity extends BaseActivity {
    @BindView(R.id.top_pager)
    WrapContentViewPager topPager;
    @BindView(R.id.main_tab)
    SlidingTabLayout mainTab;
    @BindView(R.id.bottom_pager)
    WrapContentViewPager bottomPager;
    private List<Fragment> topFragments;//碎片集合
    private List<Fragment> bottomFragments;//底部碎片

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        topFragments = new ArrayList<>();
        topFragments.add(PalmTopFragment.newInstance());//掌上宝骏
        topFragments.add(ServiceTopFragment.newInstance());//生活服务
        topFragments.add(BussinessTopFragment.newInstance());//金融商场
        topFragments.add(SocialTopFragment.newInstance());//车友社交
        bottomFragments = new ArrayList<>();
        bottomFragments.add(PalmBottomFragment.newInstance());
        bottomFragments.add(ServiceBottomFragment.newInstance());
        bottomFragments.add(BussinessBottomFragment.newInstance());
        bottomFragments.add(SocailBottomFragment.newInstance());
        initData();
    }

    /**
     * 初始化viewpager+fragment
     */
    private void initData() {
        String[] mTitles = new String[]{
                mContext.getResources().getString(R.string.str_palm),
                mContext.getResources().getString(R.string.str_service),
                mContext.getResources().getString(R.string.str_business),
                mContext.getResources().getString(R.string.str_social)};
        XFragmentPagerAdapter topPagerAdapter = new XFragmentPagerAdapter(getSupportFragmentManager(), topFragments);
        topPager.setAdapter(topPagerAdapter);
        mainTab.setViewPager(topPager, mTitles);
        final XFragmentPagerAdapter bottomPagerAdapter = new XFragmentPagerAdapter(getSupportFragmentManager(), bottomFragments);
        bottomPager.setAdapter(bottomPagerAdapter);
        topPager.setOffscreenPageLimit(topFragments.size());
        bottomPager.setOffscreenPageLimit(bottomFragments.size());
        topPager.addOnPageChangeListener(new BaseLinkPageChangeListener(topPager, bottomPager) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                topPager.resetHeight(position);
                bottomPager.resetHeight(position);//高度自适应，重新设置高度
            }
        });
        bottomPager.addOnPageChangeListener(new BaseLinkPageChangeListener(bottomPager, topPager) {//底部滑动监听
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mainTab.onPageScrolled(position, positionOffset, positionOffsetPixels);
                topPager.resetHeight(position);
                bottomPager.resetHeight(position);//高度自适应，重新设置高度
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            SystemUtils.Exit(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
