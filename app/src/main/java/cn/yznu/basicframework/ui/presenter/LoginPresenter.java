package cn.yznu.basicframework.ui.presenter;

import android.util.Log;

import cn.yznu.anno.InstanceFactory;
import cn.yznu.basicframework.base.rxbase.RxSubscriber;
import cn.yznu.basicframework.ui.contract.LoginContract;

@InstanceFactory()
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login() {
        addDispose(mModel.postLogin().subscribeWith(new RxSubscriber<Object>(mContext, false) {
            @Override
            protected void _onNext(Object userBaseRespose) {
                Log.e(TAG, userBaseRespose.toString());
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));

    }
}
