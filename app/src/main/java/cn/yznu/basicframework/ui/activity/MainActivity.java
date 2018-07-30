package cn.yznu.basicframework.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseActivity;


public class MainActivity extends BaseActivity {
//    @BindView(R.id.bottom_navigation_bar)
//    BottomNavigationBar bottomNavigationBar;
//    @BindView(R.id.content)
//    FrameLayout rlTabContainer;
//    private Long firstTime = 0L;
//    private static final int INDEX = 0;
//    private static final int SECOND = 1;
//    private static final int THREE = 2;
//    private SupportFragment[] mFragments = new SupportFragment[3];
//    private int mSelectPosition = 0, mCurrentPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState == null) {
//            mFragments[INDEX] = IndexFragment.newInstance();
//            mFragments[SECOND] = SecondFragment.newInstance();
//            mFragments[THREE] = ThreeFragment.newInstance();
//            loadMultipleRootFragment(R.id.content, INDEX,
//                    mFragments[INDEX],
//                    mFragments[SECOND],
//                    mFragments[THREE]);
//        } else {
//            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
//            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
//            mFragments[INDEX] = findFragment(IndexFragment.class);
//            mFragments[SECOND] = findFragment(SecondFragment.class);
//            mFragments[THREE] = findFragment(ThreeFragment.class);
//        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
//        initBottomNavigationBar(mSelectPosition);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            if (System.currentTimeMillis() - firstTime > 2000) {
//                ToastHelper.showToast(mContext, "再次点击退出程序", TastyToast.INFO);
//                firstTime = System.currentTimeMillis();
//            } else {
//                finish();
//                System.exit(0);
//            }
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

//    private void initBottomNavigationBar(@IntRange(from = 0, to = 3) int position) {
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.str_index)).setInactiveIconResource(R.mipmap.ic_launcher_round));
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.str_message)).setInactiveIconResource(R.mipmap.ic_launcher_round));
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.str_contact)).setInactiveIconResource(R.mipmap.ic_launcher_round));
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, getString(R.string.str_mine)).setInactiveIconResource(R.mipmap.ic_launcher_round));
//
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        bottomNavigationBar.setActiveColor(R.color.colorAccent);
//        bottomNavigationBar.setInActiveColor(R.color.colorBgDark);
//        bottomNavigationBar.setFirstSelectedPosition(position);
//        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position) {
//                doOnTabSelected(position);
//            }
//        });
//        bottomNavigationBar.setBarBackgroundColor(R.color.colorWhite);
//        bottomNavigationBar.initialise();
//        bottomNavigationBar.setAutoHideEnabled(false);
//    }
//
//    private void doOnTabSelected(int position) {
//        mSelectPosition = position;
//        showHideFragment(mFragments[position], mFragments[mCurrentPosition]);
//        mCurrentPosition = position;
//    }
}
