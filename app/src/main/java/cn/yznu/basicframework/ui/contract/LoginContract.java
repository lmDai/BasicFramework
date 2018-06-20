package cn.yznu.basicframework.ui.contract;

import cn.yznu.basicframework.base.BaseModel;
import cn.yznu.basicframework.base.BasePresenter;
import cn.yznu.basicframework.base.BaseView;
import cn.yznu.basicframework.model.bean.TestBean;
import io.reactivex.Flowable;

public interface LoginContract {
    interface Model extends BaseModel {
        Flowable<TestBean> postLogin();
    }

    interface View extends BaseView {


    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void login();
    }
}
