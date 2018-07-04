package cn.yznu.basicframework.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import cn.yznu.apt.InstanceFactory;
import cn.yznu.basicframework.R;
import cn.yznu.basicframework.receiver.netstatereceiver.NetStateReceiver;
import cn.yznu.basicframework.service.InitializeService;
import cn.yznu.basicframework.utils.DensityUtils;
import cn.yznu.basicframework.utils.instance.IFactory;
import cn.yznu.basicframework.utils.instance.InstanceUtil;
import cn.yznu.common.loadviewhelper.LoadViewHelper;

import static java.lang.System.exit;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：App
 * 版本：1.0
 * 修订历史：
 */
public class App extends Application {
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DensityUtils.setDensity(this);
        initLoadingHelper();
    }

    public static Context getAppContext() {
        return instance;
    }

    public static Resources getAppResources() {
        return instance.getResources();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        //在子线程中完成其他初始化
        InitializeService.start(this);
        InstanceUtil.init(new IFactory() {
            @Override
            public Object create(Class clazz) throws Exception {
                return InstanceFactory.create(clazz);
            }
        });
        //网络状态监听
        NetStateReceiver.registerNetworkStateReceiver(this);//初始化网络监听
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        NetStateReceiver.unRegisterNetworkStateReceiver(this);
        android.os.Process.killProcess(android.os.Process.myPid());
        exit(0);
    }

    /**
     * 初始化加载界面，空界面，错误界面
     */
    private void initLoadingHelper() {
        LoadViewHelper.getBuilder()
                .setLoadEmpty(R.layout.empty_view)
                .setLoadError(R.layout.error_view)
                .setLoadIng(R.layout.loading_view);
    }
}
