package cn.yznu.basicframework.ui.model;

import cn.yznu.anno.InstanceFactory;
import cn.yznu.basicframework.base.BaseResponse;
import cn.yznu.basicframework.base.rxbase.RxSchedulers;
import cn.yznu.basicframework.model.bean.TestBean;
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
    public Flowable<TestBean> postLogin() {
        return Api.getDefault().getListDict().compose(RxSchedulers.<BaseResponse<TestBean>>io_main())
                .compose(RxSchedulers.<TestBean>handleResult());
    }
}
