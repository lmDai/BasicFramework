package cn.yznu.basicframework.receiver.netstatereceiver;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
interface NetChangeObserver {
    /**
     * 网络连接回调 type为网络类型
     */
    void onNetConnected();
    /**
     * 没有网络
     */
    void onNetDisConnect();
}
