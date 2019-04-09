package com.qiusheng.www;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelFiled {

    /**
     * 值
     * @return
     */
    String value()default "";

    /**
     * 题目
     * @return
     */
    String title();
    /**
     * 定义字段类型
     */
    int type() default 0;

    /**
     * 导出字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
     */
    int align() default 0;

    /**
     * 导出字段字段排序（升序）
     */
    int sort() default 0;

    /**
     * 如果是字典类型，请设置字典的type值
     */
    String dictType() default "";

    /**
     * 反射类型??????????这是干什么的
     */
    Class<?> fieldType() default Class.class;

    /**
     * 字段归属组（根据分组导出导入）
     */
    int[] groups() default {};

}
