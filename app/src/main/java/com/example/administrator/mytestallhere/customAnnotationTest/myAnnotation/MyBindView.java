package com.example.administrator.mytestallhere.customAnnotationTest.myAnnotation;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * (1). 通过 @interface 定义，注解名即为自定义注解名
 * (2). 注解配置参数名为注解类的方法名，且：
 * a. 所有方法没有方法体，没有参数没有修饰符，实际只允许 public & abstract 修饰符，默认为 public，不允许抛异常
 * b. 方法返回值只能是基本类型，String, Class, annotation, enumeration 或者是他们的一维数组
 * c. 若只有一个默认属性，可直接用 value() 函数。一个属性都没有表示该 Annotation 为 Mark Annotation
 * (3). 可以加 default 表示默认值
 * 4.3 元 Annotation
 *
 * @Documented 是否会保存到 Javadoc 文档中
 * @Retention 保留时间，可选值 SOURCE（源码时），CLASS（编译时），RUNTIME（运行时），默认为 CLASS，SOURCE 大都为 Mark Annotation，这类 Annotation 大都用来校验，比如 Override, SuppressWarnings
 * @Target 可以用来修饰哪些程序元素，如 TYPE, METHOD, CONSTRUCTOR, FIELD, PARAMETER 等，未标注则表示可修饰所有
 * @Inherited 是否可以被继承，默认为 false
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyBindView {
    int value() default -1;//只有一个的话可以默认value
}
