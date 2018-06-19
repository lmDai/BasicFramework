package cn.yznu.basicframework.base.rxbase;

import android.content.Context;

import cn.yznu.basicframework.R;
import cn.yznu.basicframework.app.App;
import cn.yznu.basicframework.utils.NetWorkUtils;
import io.reactivex.subscribers.DisposableSubscriber;


/********************使用例子********************/
/* Api.getDefault().postData()
         .compose(RxSchedulers.<BaseRespose<User>>io_main()).subscribeWith(new RxSubscriber<BaseRespose<User>>(context, false) {
@Override
protected void _onNext(BaseRespose<User> userBaseRespose) {

        }

@Override
protected void _onError(String message) {

        }
        });*/

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public abstract class RxSubscriber<T> extends DisposableSubscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, App.getAppContext().getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, App.getAppContext().getString(R.string.loading), showDialog);
    }

    @Override
    public void onComplete() {
//        if (showDialog)
//            LoadingDialog.cancelLoadingDialog();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
//                LoadingDialog.showLoadingDialog((Activity) mContext,0,msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog)
//            LoadingDialog.cancelLoadingDialog();
            e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(App.getAppContext())) {
            _onError(App.getAppContext().getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof ServerException) {
            _onError(e.getMessage());
        } else if (e instanceof ApiException) {//服务器返回状态码判断，主要用于判断是否登陆失效
            _onError(e.getMessage());
        }//其它
        else {
            _onError(App.getAppContext().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
