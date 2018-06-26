package cn.yznu.basicframework.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.yznu.basicframework.R;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：底部导航栏
 * 版本：1.0
 * 修订历史：
 */
public class BottomBar extends LinearLayout {
    @BindView(R.id.img_index)
    ImageView imgIndex;
    @BindView(R.id.txt_index)
    TextView txtIndex;
    @BindView(R.id.rl_index)
    RelativeLayout rlIndex;
    @BindView(R.id.img_message)
    ImageView imgMessage;
    @BindView(R.id.txt_message)
    TextView txtMessage;
    @BindView(R.id.rl_message)
    RelativeLayout rlMessage;
    @BindView(R.id.img_contact)
    ImageView imgContact;
    @BindView(R.id.txt_contact)
    TextView txtContact;
    @BindView(R.id.rl_contact)
    RelativeLayout rlContact;
    @BindView(R.id.img_mine)
    ImageView imgMine;
    @BindView(R.id.txt_mine)
    TextView txtMine;
    @BindView(R.id.rl_mine)
    RelativeLayout rlMine;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private Context mContext;
    private OnBottomBarClick mOnBottomBarClick;
    private int[] rlIds = {R.id.rl_index, R.id.rl_message, R.id.rl_contact, R.id.rl_mine};
    private int[] imgSelected = {R.drawable.welcome_bg, R.drawable.welcome_bg, R.drawable.welcome_bg, R.drawable.welcome_bg};
    private int[] imgUnSelected = {R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round};

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
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_bottom, this, true);
        ButterKnife.bind(this);
        onBottomBarClick();
    }

    /**
     * 底部按钮点击监听器
     */
    private void onBottomBarClick() {
        rlIndex.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNavColor(rlIds[0]);
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onIndexClick();
                }
            }
        });
        rlMessage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNavColor(rlIds[1]);
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onMessageClick();
                }
            }
        });
        rlContact.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNavColor(rlIds[2]);
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onContactClick();
                }
            }
        });
        rlMine.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNavColor(rlIds[3]);
                if (mOnBottomBarClick != null) {
                    mOnBottomBarClick.onMineClick();
                }
            }
        });
        changeNavColor(R.id.rl_index);
    }

    public void setOnBottombarOnclick(OnBottomBarClick onBottomBarOnclick) {
        mOnBottomBarClick = onBottomBarOnclick;
    }

    public interface OnBottomBarClick {
        void onIndexClick();

        void onMessageClick();

        void onContactClick();

        void onMineClick();
    }

    private void changeNavColor(int resId) {
        for (int i = 0; i < rlIds.length; i++) {
            RelativeLayout relativeLayout = findViewById(rlIds[i]);
            ImageView imageView = (ImageView) relativeLayout.getChildAt(0);
            TextView textView = (TextView) relativeLayout.getChildAt(relativeLayout.getChildCount() - 1);
            if (rlIds[i] == resId) {
                textView.setTextColor(ContextCompat.getColor(mContext, R.color.colordialogPirmary));
                imageView.setImageDrawable(ContextCompat.getDrawable(mContext, imgSelected[i]));
            } else {
                textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                imageView.setImageDrawable(ContextCompat.getDrawable(mContext, imgUnSelected[i]));
            }
        }
    }
}
