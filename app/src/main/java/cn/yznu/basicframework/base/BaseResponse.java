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
    public String code;
    public String message;

    public T data;

    public boolean success() {
        return "200".equals(code);
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + code + '\'' +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
