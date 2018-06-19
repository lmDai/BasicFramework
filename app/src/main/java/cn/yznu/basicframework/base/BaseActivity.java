package cn.yznu.basicframework.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.app.AppManager;
import cn.yznu.basicframework.receiver.netstatereceiver.NetChangeObserver;
import cn.yznu.basicframework.receiver.netstatereceiver.NetStateReceiver;
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
    public Toolbar mToolbar;
    public TextView title;
    public View back;
    public Context mContext;
    public RxManager mRxManager;
    private Unbinder bind;
    protected NetChangeObserver mNetChangeObserver = null;
    protected boolean network = true;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        doBeforeSetContentView();
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        mContext = this;
        mPresenter = InstanceUtil.getInstance(this, 0);
        M mModel = InstanceUtil.getInstance(this, 1);
        if (mPresenter != null && this instanceof BaseView) {
            mPresenter.mContext = this;
            mPresenter.setVM(this, mModel);
        }
        initNetWorkState();//初始化网络状态
        mToolbar = findViewById(R.id.toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        initTitle();
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initView();
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
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        initStatusBarColor();
    }

    protected void initTitle() {
        title = findViewById(R.id.toolbar_title);
        back = findViewById(R.id.toolbar_back);
        if (null != back) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
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
