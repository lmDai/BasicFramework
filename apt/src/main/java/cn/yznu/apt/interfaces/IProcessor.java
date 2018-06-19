package cn.yznu.apt.interfaces;

import javax.annotation.processing.RoundEnvironment;

import cn.yznu.apt.AnnotationProcessor;


/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：apt
 * 版本：1.0
 * 修订历史：
 */

public interface IProcessor {
    void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor);
}

