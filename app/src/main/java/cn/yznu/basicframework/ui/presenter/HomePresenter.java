package cn.yznu.basicframework.ui.presenter;

import java.util.List;

import cn.yznu.anno.InstanceFactory;
import cn.yznu.basicframework.base.rxbase.RxSubscriber;
import cn.yznu.basicframework.model.bean.TestBean;
import cn.yznu.basicframework.ui.contract.HomeContract;

@InstanceFactory()
public class HomePresenter extends HomeContract.Presenter {
    private int pageSize = 10;
    private int pageNumber = 1;

    @Override
    public void getGanks(String type, final boolean isRefresh) {
        if (isRefresh) {
            pageNumber = 1;
        }
        addDispose(mModel.postGanks(type, pageSize + "", pageNumber + "").subscribeWith(new RxSubscriber<List<TestBean>>(mContext, false) {
            @Override
            protected void _onNext(List<TestBean> userBaseRespose) {
                mView.setData(isRefresh, userBaseRespose);
                pageNumber++;
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));

    }
}
