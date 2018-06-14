package cn.yznu.basicframework.utils.instance;

import java.lang.reflect.ParameterizedType;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：
 * 版本：1.0
 * 修订历史：
 */
public class InstanceUtil {
    private static IFactory iFactory;

    public static void init(IFactory factory) {
        iFactory = factory;
    }

    public static <T> T getInstance(Object o, int i) {
        try {
            Class mClass = (Class<T>) ((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments()[i];
            return (T) iFactory.create(mClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
