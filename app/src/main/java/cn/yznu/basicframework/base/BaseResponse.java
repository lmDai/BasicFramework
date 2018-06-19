package cn.yznu.basicframework.base;

import java.io.Serializable;

/**
 * 作者：uiho_mac
 * 时间：2018/6/15
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class BaseResponse<T> implements Serializable {
    public int status;
    public String message;

    public T param;

    public boolean success() {
        return status == 200;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + status + '\'' +
                ", msg='" + message + '\'' +
                ", data=" + param +
                '}';
    }
}

