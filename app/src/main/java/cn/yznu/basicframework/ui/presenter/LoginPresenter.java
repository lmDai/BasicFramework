package cn.yznu.basicframework.ui.presenter;

import android.util.Log;
import android.widget.Toast;

import cn.yznu.anno.InstanceFactory;
import cn.yznu.basicframework.base.rxbase.RxSubscriber;
import cn.yznu.basicframework.model.bean.TestBean;
import cn.yznu.basicframework.ui.contract.LoginContract;

@InstanceFactory()
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login() {
        addDispose(mModel.postLogin().subscribeWith(new RxSubscriber<TestBean>(mContext, false) {
            @Override
            protected void _onNext(TestBean userBaseRespose) {
                Log.e(TAG, userBaseRespose.toString());
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            }
        }));

    }
}
