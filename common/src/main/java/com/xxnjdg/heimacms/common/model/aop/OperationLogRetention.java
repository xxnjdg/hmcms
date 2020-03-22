package com.xxnjdg.heimacms.common.model.aop;

import java.lang.annotation.*;

/**
 * 自定义操作日志注解
 *
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface OperationLogRetention {
    String operationModel() default ""; // 操作模块
     String operationType() default "";  // 操作类型
     String operationDesc() default "";  // 操作说明
}
