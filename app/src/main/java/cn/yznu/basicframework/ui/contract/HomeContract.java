package cn.yznu.basicframework.ui.contract;

import java.util.List;

import cn.yznu.basicframework.base.BaseModel;
import cn.yznu.basicframework.base.BasePresenter;
import cn.yznu.basicframework.base.BaseView;
import cn.yznu.basicframework.model.bean.TestBean;
import io.reactivex.Flowable;

public interface HomeContract {
    interface Model extends BaseModel {
        Flowable<List<TestBean>> postGanks(String type, String pageSize, String pageNumber);
    }

    interface View extends BaseView {
        void setData(boolean isRefresh, List data);
    }

    abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getGanks(String type, boolean isRefresh);
    }
}
