package cn.yznu.basicframework.ui.contract;

import cn.yznu.basicframework.base.BaseModel;
import cn.yznu.basicframework.base.BasePresenter;
import cn.yznu.basicframework.base.BaseView;
import cn.yznu.basicframework.model.bean.TestBean;
import io.reactivex.Flowable;

/**
 * 作者：潇湘夜雨 on 2018/2/7.
 * 邮箱：879689064@qq.com
 */

public interface LoginContract {
    interface Model extends BaseModel {
        Flowable<TestBean> postLogin();
    }

    interface View extends BaseView {


    }

    abstract static class Presenter extends BasePresenter<Model, View> {
        public abstract void login();
    }
}
