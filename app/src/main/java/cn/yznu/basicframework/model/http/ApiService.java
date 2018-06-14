package cn.yznu.basicframework.model.http;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public interface ApiService {
    @GET("system/listDict")
    Flowable<Object> getListDict();
}
