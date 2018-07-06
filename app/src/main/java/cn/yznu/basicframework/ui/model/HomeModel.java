package cn.yznu.basicframework.ui.model;

import java.util.List;

import cn.yznu.anno.InstanceFactory;
import cn.yznu.basicframework.base.BaseResponse;
import cn.yznu.basicframework.base.rxbase.RxSchedulers;
import cn.yznu.basicframework.model.bean.TestBean;
import cn.yznu.basicframework.model.http.Api;
import cn.yznu.basicframework.ui.contract.HomeContract;
import io.reactivex.Flowable;


@InstanceFactory()
public class HomeModel implements HomeContract.Model {
    @Override
    public Flowable<List<TestBean>> postGanks(String type, String pageSize, String pageNumber) {
        return Api.getDefault().getGanks(type, pageSize, pageNumber).compose(RxSchedulers.<BaseResponse<TestBean>>io_main())
                .compose(RxSchedulers.<TestBean>handleResult());
    }
}
