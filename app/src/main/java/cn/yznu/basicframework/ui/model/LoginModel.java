package cn.yznu.basicframework.ui.model;

import cn.yznu.anno.InstanceFactory;
import cn.yznu.basicframework.base.rxbase.RxSchedulers;
import cn.yznu.basicframework.model.http.Api;
import cn.yznu.basicframework.ui.contract.LoginContract;
import io.reactivex.Flowable;


/**
 * Created by linSir
 * date at 2018/2/7.
 * describe:
 */
@InstanceFactory()
public class LoginModel implements LoginContract.Model {
    @Override
    public Flowable<Object> postLogin() {
        return Api.getDefault().getListDict().compose(RxSchedulers.<Object>io_main());
    }
}
