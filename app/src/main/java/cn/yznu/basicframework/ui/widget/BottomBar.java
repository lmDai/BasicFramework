package cn.yznu.basicframework.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.yznu.basicframework.R;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：底部导航栏
 * 版本：1.0
 * 修订历史：
 */
public class BottomBar extends LinearLayout {
    private Context mContext;
    private RelativeLayout mFirstBottom, mSecondBottom, mCenterBottom;
    private OnBottomBarClick mOnBottomBarClick;

    public BottomBar(Context context) {
        super(context);
        init(context);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化控件
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_bottom, this, true);
        initId();//获取控件id
        onBottomBarClick();
    }

    private void initId() {
        mFirstBottom = findViewById(R.id.rl_first);
        mSecondBottom = findViewById(R.id.rl_second);
        mCenterBottom = findViewById(R.id.rl_center);
    }

    /**
     * 底部按钮点击监听器
     */
    private void onBottomBarClick() {

        mFirstBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onFirstClick();
                }
            }
        });
        mSecondBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onSecondClick();
                }
            }
        });
        mCenterBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onCenterClick();
                }
            }
        });

    }

    public void setOnBottombarOnclick(OnBottomBarClick onBottomBarOnclick) {

        mOnBottomBarClick = onBottomBarOnclick;
    }

    public interface OnBottomBarClick {
        void onFirstClick();

        void onSecondClick();

        void onCenterClick();
    }
}
