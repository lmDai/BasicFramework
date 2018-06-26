package cn.yznu.basicframework.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import butterknife.BindView;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.base.BaseActivity;
import cn.yznu.basicframework.utils.IntentUtils;
import cn.yznu.basicframework.utils.SystemUtils;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：启动页面
 * 版本：1.0
 * 修订历史：
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.txt_app_name)
    TextView txtAppName;
    @BindView(R.id.txt_app_version)
    TextView txtAppVersion;
    private SplashHandler splashHandler;
    private Handler handler;

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        txtAppName.setText(SystemUtils.getAppName(mContext));
        txtAppVersion.setText(SystemUtils.getAppVersion(mContext));
        handler = new Handler();
        splashHandler = new SplashHandler();
        SetTranslanteBar();

    }

    private class SplashHandler implements Runnable {
        @Override
        public void run() {
            IntentUtils.get().goActivityKill(mContext, MainActivity.class);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null && splashHandler != null) {
            handler.removeCallbacks(splashHandler);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (handler != null && splashHandler != null) {
            handler.postDelayed(splashHandler, 3000);
        }
    }
}
