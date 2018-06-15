package cn.yznu.apt.interfaces;

import javax.annotation.processing.RoundEnvironment;

import cn.yznu.apt.AnnotationProcessor;


/**
 * 作者：潇湘夜雨 on 2018/2/2.
 * 邮箱：879689064@qq.com
 */

public interface IProcessor {
    void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor);
}

