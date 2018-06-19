package cn.yznu.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：anno
 * 版本：1.0
 * 修订历史：
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
/**
 * 实例化注解,会被主动添加到实例化工厂,自动生成new来替换掉反射的newInstance代码
 */
public @interface InstanceFactory {
}
