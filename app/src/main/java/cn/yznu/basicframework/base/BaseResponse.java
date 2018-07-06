package cn.yznu.basicframework.base;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：uiho_mac
 * 时间：2018/6/15
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class BaseResponse<T> implements Serializable {

    private boolean error;
    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}

