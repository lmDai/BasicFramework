package cn.yznu.basicframework.base.rxbase;

/**
 * 作者：uiho_mac
 * 时间：2018/6/19
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class ApiException extends Exception {
    private int code;
    private String message;

    public ApiException(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
