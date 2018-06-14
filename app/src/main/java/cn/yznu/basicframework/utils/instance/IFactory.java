package cn.yznu.basicframework.utils.instance;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public interface IFactory {
    Object create(Class clazz) throws Exception;
}
