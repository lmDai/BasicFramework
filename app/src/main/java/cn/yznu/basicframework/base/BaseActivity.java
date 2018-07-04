package cn.yznu.basicframework.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.app.AppManager;
import cn.yznu.basicframework.receiver.netstatereceiver.NetChangeObserver;
import cn.yznu.basicframework.receiver.netstatereceiver.NetStateReceiver;
import cn.yznu.basicframework.utils.DensityUtils;
import cn.yznu.basicframework.utils.StatusBarCompat;
import cn.yznu.basicframework.utils.instance.InstanceUtil;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：基础activity
 * 版本：1.0
 * 修订历史：
 */
public abstract class BaseActivity<T extends BasePresenter, M extends BaseModel> extends SupportActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected boolean showBack = true;
    protected Toolbar toolbar;
    protected TextView textCancel;
    protected TextView textRight;
    public View back;
    public Context mContext;
    public RxManager mRxManager;
    private Unbinder bind;
    protected NetChangeObserver mNetChangeObserver = null;
    protected boolean network = true;
    protected T mPresenter;
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        mContext = this;
        mActivity = this;
        doBeforeSetContentView();
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        mPresenter = InstanceUtil.getInstance(this, 0);
        M mModel = InstanceUtil.getInstance(this, 1);
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.mContext = this;
            mPresenter.setVM(this, mModel);
        }
        initNetWorkState();//初始化网络状态
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            textRight = toolbar.findViewById(R.id.btn_right);
            textCancel = toolbar.findViewById(R.id.btn_left);
            if (textCancel != null)
                textCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackListener();
                    }
                });
            setSupportActionBar(toolbar);
            if (showBack) {
                final Drawable upArrow = getResources().getDrawable(R.mipmap.ic_launcher);
                upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onBackListener() {
        finish();
    }

    /**
     * 设置标题
     *
     * @param str
     */
    public void setTopTitle(String str) {
        TextView title = findViewById(R.id.bt_tv_title);
        title.setText(str);
    }

    protected void initNetWorkState() {
        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected() {

                onNetworkConnected();
                network = true;
            }

            @Override
            public void onNetDisConnect() {
                network = false;
                onNetworkDisConnected();
            }
        };

        NetStateReceiver.registerObserver(mNetChangeObserver);
    }

    /**
     * 网络连接状态
     */
    protected void onNetworkConnected() {
    }

    /**
     * 网络断开的时候调用
     */
    protected void onNetworkDisConnected() {

    }

    protected abstract void getBundleExtras(Bundle extras);

    /**
     * 设置layout前配置
     */
    private void doBeforeSetContentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setOrientation();
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        initStatusBarColor();

    }

    private void setOrientation() {
        DensityUtils.setDefault(mActivity);
    }


    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void initStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorAccent));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }

    //获取布局文件
    public abstract int getLayoutId();


    //初始化view
    public abstract void initView();

    @Override
    public Resources getResources() {
        //获取到resources对象
        Resources res = super.getResources();
        //修改configuration的fontScale属性
        res.getConfiguration().fontScale = 1;
        //将修改后的值更新到metrics.scaledDensity属性上
        res.updateConfiguration(null, null);
        return res;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        this.mPresenter = null;
        mRxManager.clear();
        if (bind != null && bind != Unbinder.EMPTY)
            bind.unbind();
        NetStateReceiver.removeRegisterObserver(mNetChangeObserver);
        AppManager.getAppManager().finishActivity(this);
    }
}
