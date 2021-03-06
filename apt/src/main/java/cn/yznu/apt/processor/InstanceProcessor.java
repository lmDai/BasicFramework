package cn.yznu.apt.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.FilerException;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;

import cn.yznu.anno.InstanceFactory;
import cn.yznu.apt.AnnotationProcessor;
import cn.yznu.apt.interfaces.IProcessor;
import cn.yznu.apt.util.Utils;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;


/**
 * 作者：uiho_mac
 * 时间：2018/6/14
 * 描述：apt
 * 版本：1.0
 * 修订历史：
 */

public class InstanceProcessor  implements IProcessor {
    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor) {
        TypeElement mElment=null;
        String CLASS_NAME = "InstanceFactory";
        TypeSpec.Builder tb = classBuilder(CLASS_NAME).addModifiers(PUBLIC, FINAL).addJavadoc("@ 实例化工厂 此类由apt自动生成");
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("create")
                .addJavadoc("@此方法由apt自动生成")
                .returns(Object.class)
                .addModifiers(PUBLIC, STATIC)
                .addException(IllegalAccessException.class)
                .addException(InstantiationException.class)
                .addParameter(Class.class, "mClass");

        List<ClassName> mList = new ArrayList<>();
        CodeBlock.Builder blockBuilder = CodeBlock.builder();
        blockBuilder.beginControlFlow(" switch (mClass.getSimpleName())");//括号开始
        try {
            for (TypeElement element : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(InstanceFactory.class))) {
                mAbstractProcessor.mMessager.printMessage(Diagnostic.Kind.NOTE, "正在处理: " + element.toString());
                if(mElment==null)mElment=element;
                if (!Utils.isValidClass(mAbstractProcessor.mMessager, element)) return;
                ClassName currentType = ClassName.get(element);
                if (mList.contains(currentType)) continue;
                mList.add(currentType);
                blockBuilder.addStatement("case $S: return  new $T()", currentType.simpleName(), currentType);//初始化Presenter
            }
            blockBuilder.addStatement("default: return mClass.newInstance()");
            blockBuilder.endControlFlow();
            methodBuilder.addCode(blockBuilder.build());
            tb.addMethod(methodBuilder.build());
            if(mElment==null){
                return;
            }
            JavaFile javaFile = JavaFile.builder("cn.yznu.apt", tb.build()).build();// 生成源代码
            javaFile.writeTo(mAbstractProcessor.mFiler);// 在 app module/build/generated/source/apt 生成一份源代码
        } catch (FilerException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
