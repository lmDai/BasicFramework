package cn.yznu.basicframework.base.rxbase;

import org.reactivestreams.Publisher;

import java.util.List;

import cn.yznu.basicframework.base.BaseResponse;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class RxSchedulers {

    public static <T> FlowableTransformer<T, T> io_main() {

        return new FlowableTransformer<T, T>() {

            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<BaseResponse<T>, List<T>> handleResult() {
        return new FlowableTransformer<BaseResponse<T>, List<T>>() {
            @Override
            public Publisher<List<T>> apply(Flowable<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, Publisher<List<T>>>() {
                    @Override
                    public Publisher<List<T>> apply(BaseResponse<T> tBaseResponse) {
                        if (tBaseResponse != null) {
                            if (!tBaseResponse.isError()) {
                                return createData(tBaseResponse.getResults());
                            }
                        }
                        return Flowable.error(new ServerException("服务器错误"));
                    }
                });
            }
        };
    }

    private static <T> Flowable<List<T>> createData(final List<T> data) {
        return Flowable.create(new FlowableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(FlowableEmitter<List<T>> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
