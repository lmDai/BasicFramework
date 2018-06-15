package cn.yznu.basicframework;

import android.os.Bundle;

import cn.yznu.basicframework.base.BaseActivity;
import cn.yznu.basicframework.ui.contract.LoginContract;
import cn.yznu.basicframework.ui.model.LoginModel;
import cn.yznu.basicframework.ui.presenter.LoginPresenter;


public class MainActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        if (mPresenter != null)
            mPresenter.login();
    }

    @Override
    public void showErrorTip(String msg) {

    }
}
