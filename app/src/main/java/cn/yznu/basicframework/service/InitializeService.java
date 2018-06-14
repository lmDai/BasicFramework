package cn.yznu.basicframework.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：初始化服务
 * 版本：1.0
 * 修订历史：
 */
public class InitializeService extends IntentService {
    private static final String ACTION = "InitializeService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public InitializeService(String name) {
        super("InitializeService");
    }

    //启动服务
    public static void start(Context context) {
        Intent intent = new Intent(context, IntentService.class);
        intent.setAction(ACTION);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (TextUtils.equals(ACTION, action)) {
                initApplication();
            }
        }
    }

    //一般用于第三方的初始化操作
    private void initApplication() {

    }
}
