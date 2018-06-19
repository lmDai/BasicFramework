package cn.yznu.basicframework.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseActivity;
import cn.yznu.basicframework.ui.fragment.FirstFragment;
import cn.yznu.basicframework.ui.fragment.SecondFragment;
import cn.yznu.basicframework.ui.widget.BottomBar;
import cn.yznu.basicframework.utils.PopupMenuUtil;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.fl_tab_container)
    FrameLayout rlTabContainer;
    ImageView mCenterImage;
    private TextView txtIndex,txtMy;
    private Long firstTime = 0L;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private SupportFragment[] mFragments = new SupportFragment[2];
    private int mSelectPosition, mCurrentPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[FIRST] = FirstFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(FirstFragment.class);
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
        mCenterImage = (ImageView) findViewById(R.id.img_center);
        txtIndex = (TextView) findViewById(R.id.txt_index);
        txtMy = (TextView) findViewById(R.id.txt_my);
        bottomBar.setOnBottombarOnclick(new BottomBar.OnBottomBarClick() {
            @Override
            public void onFirstClick() {
                mSelectPosition = 0;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 0;
                changeNavColor();
            }

            @Override
            public void onSecondClick() {
                mSelectPosition = 1;
                showHideFragment(mFragments[mSelectPosition], mFragments[mCurrentPosition]);
                mCurrentPosition = 1;
                changeNavColor();
            }

            @Override
            public void onCenterClick() {
                PopupMenuUtil.getInstance()._show(mContext, mCenterImage);
            }
        });
        changeNavColor();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void changeNavColor() {
        switch (mSelectPosition) {
            case 0:
                txtMy.setTextColor(ContextCompat.getColor(mContext,R.color.colorIndexbgTextDark));
                txtIndex.setTextColor(ContextCompat.getColor(mContext,R.color.colorAccent));
                break;
            case 1:
                txtMy.setTextColor(ContextCompat.getColor(mContext,R.color.colorAccent));
                txtIndex.setTextColor(ContextCompat.getColor(mContext,R.color.colorIndexbgTextDark));
                break;
        }

    }
}
