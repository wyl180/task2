package com.jnshu.result;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName MyAspectJ
 * @Description 用日志记录Controller每个方法的执行时间切面类.
 * @Author 韦延伦
 * @Date 2020/7/28 10:55
 * @Version 1.0
 */
@Aspect
@Component
public class MyAspectJ {
    private static final Logger logger = LoggerFactory.getLogger(MyAspectJ.class);
    @Around("execution(* com.jnshu.controller.StudentController.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        try {
            logger.info("Method Name : [" + methodName + "] ---> AOP around start");
            long startTimeMillis = System.currentTimeMillis();
            //调用 proceed() 方法才会真正的执行实际被代理的方法
            Object result = joinPoint.proceed();
            long execTimeMillis = System.currentTimeMillis() - startTimeMillis;
            logger.info("Method Name : [" + methodName + "] ---> 方法用时 : " + execTimeMillis+"ms");
            logger.info("Method Name : [" + methodName + "] ---> AOP around end , and result is : " + result.toString());
            return result;
        } catch (Throwable te) {
            logger.error(te.getMessage(), te);
            throw new RuntimeException(te.getMessage());
        }
    }
}
