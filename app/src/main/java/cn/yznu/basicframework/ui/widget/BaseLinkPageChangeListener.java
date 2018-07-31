package cn.yznu.basicframework.ui.widget;

import android.support.v4.view.ViewPager;

/**
 * 作者：uiho_mac
 * 时间：2018/7/31
 * 描述：实现viewpager的联动
 * 版本：1.0
 * 修订历史：
 */
public class BaseLinkPageChangeListener implements ViewPager.OnPageChangeListener {

    private ViewPager linkViewPager;
    private ViewPager selfViewPager;

    private int pos;

    public BaseLinkPageChangeListener(ViewPager selfViewPager, ViewPager linkViewPager) {
        this.linkViewPager = linkViewPager;
        this.selfViewPager = selfViewPager;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        int marginX = ((selfViewPager.getWidth() + selfViewPager.getPageMargin()) * position
                + positionOffsetPixels) * (linkViewPager.getWidth() + linkViewPager.getPageMargin()) / (
                selfViewPager.getWidth()
                        + selfViewPager.getPageMargin());

        if (linkViewPager.getScrollX() != marginX) {
            linkViewPager.scrollTo(marginX, 0);
        }
    }

    @Override
    public void onPageSelected(int position) {
        this.pos = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            linkViewPager.setCurrentItem(pos);
        }
    }
}

