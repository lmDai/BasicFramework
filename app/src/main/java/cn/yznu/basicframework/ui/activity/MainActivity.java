package cn.yznu.basicframework.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseActivity;
import cn.yznu.basicframework.ui.fragment.IndexFragment;
import cn.yznu.basicframework.ui.fragment.SecondFragment;
import cn.yznu.basicframework.ui.widget.BottomBar;
import cn.yznu.basicframework.utils.PopupMenuUtil;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.fl_tab_container)
    FrameLayout rlTabContainer;
    private Long firstTime = 0L;
    private static final int INDEX = 0;
    private static final int SECOND = 1;
    private SupportFragment[] mFragments = new SupportFragment[2];
    private int mSelectPosition, mCurrentPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[INDEX] = IndexFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_tab_container, INDEX,
                    mFragments[INDEX],
                    mFragments[SECOND]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[INDEX] = findFragment(IndexFragment.class);
            mFragments[SECOND] = findFragment(SecondFragment.class);
        }
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
        bottomBar.setOnBottombarOnclick(new BottomBar.OnBottomBarClick() {
            @Override
            public void onIndexClick() {
                mSelectPosition = 0;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 0;
            }

            @Override
            public void onMessageClick() {
                mSelectPosition = 1;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 1;
            }

            @Override
            public void onContactClick() {

            }

            @Override
            public void onMineClick() {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (PopupMenuUtil.getInstance()._isShowing()) {
                PopupMenuUtil.getInstance()._rlClickAction();
            } else {
                if (System.currentTimeMillis() - firstTime > 2000) {
                    Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = System.currentTimeMillis();
                } else {
                    finish();
                    System.exit(0);
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
