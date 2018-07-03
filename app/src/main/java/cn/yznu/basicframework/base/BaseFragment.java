package cn.yznu.basicframework.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.utils.StatusBarCompat;
import cn.yznu.basicframework.utils.instance.InstanceUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：基础fragment
 * 版本：1.0
 * 修订历史：
 */

public abstract class BaseFragment<T extends BasePresenter, M extends BaseModel> extends SupportFragment {
    public Toolbar mToolbar;
    public TextView title;
    public View back;
    protected View rootView;
    public RxManager mRxManager;
    private Unbinder bind;
    protected final String TAG = this.getClass().getSimpleName();
    public T mPresenter;
    private boolean isPrepared;
    public Context mContext;
    protected boolean showBack = true;//是否显示返回按钮，默认true
    private TextView textCancel;

    public BaseFragment() { /* compiled code */ }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (null != getArguments()) {
            getBundleExtras(getArguments());
        }
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        mRxManager = new RxManager();
        bind = ButterKnife.bind(this, rootView);
        if (getUserVisibleHint()) {
            if (isFirstVisible) {
                onFirstUserVisible();
                isFirstVisible = false;
            }
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    public void setTopTitle(String str) {
        TextView title = (TextView) rootView.findViewById(R.id.bt_tv_title);
        title.setText(str);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFirstUserVisible();
            isFirstVisible = false;
        }
    }

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    private boolean isFirstVisible = true;


    private void onFirstUserVisible() {
        if (rootView == null) {
            return;
        }
        mPresenter = InstanceUtil.getInstance(this, 0);
        M mModel = InstanceUtil.getInstance(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }
        initView();
        SetStatusBarColor();
        mToolbar = rootView.findViewById(R.id.toolbar);
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setTitle("");
            textCancel = (TextView) mToolbar.findViewById(R.id.btn_left);
            if (textCancel != null) {
                textCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackListener();
                    }
                });
            }
            ((AppCompatActivity) mContext).setSupportActionBar(mToolbar);
            if (showBack) {
                final Drawable upArrow = ContextCompat.getDrawable(mContext, R.mipmap.ic_launcher);
                upArrow.setColorFilter(ContextCompat.getColor(mContext, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                ActionBar actionBar = ((AppCompatActivity) mContext).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setHomeAsUpIndicator(upArrow);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.mContext = this.getActivity();
            mPresenter.setVM(this, mModel);
        }

    }

    protected void onBackListener() {
        ((AppCompatActivity) mContext).finish();
    }

    private void SetStatusBarColor() {
        if (getActivity() != null) {
            StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.colorAccent));
        }
    }

    /**
     * 获取bundle信息
     *
     * @param bundle
     */
    protected abstract void getBundleExtras(Bundle bundle);

    //获取布局文件
    protected abstract int getLayoutResource();

    //初始化view
    protected abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        isFirstVisible = true;
        bind.unbind();
        if (mPresenter != null)
            mPresenter.detachView();
        mRxManager.clear();
    }
}
