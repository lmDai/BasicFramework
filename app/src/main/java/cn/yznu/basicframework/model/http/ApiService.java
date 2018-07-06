package cn.yznu.basicframework.model.http;

import cn.yznu.basicframework.base.BaseResponse;
import cn.yznu.basicframework.model.bean.TestBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public interface ApiService {
    @GET("{type}/{pageSize}/{pageNumber}")
    Flowable<BaseResponse<TestBean>> getGanks(@Path("type") String type, @Path("pageSize") String pageSize,
                                              @Path("pageNumber") String pageNumber);
}
