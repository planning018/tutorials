package com.planning;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yxc
 * @date 2021/4/16 15:35
 */
@Aspect
@Component
public class ExampleAspect {

    @Around("@annotation(com.planning.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();

        final Object proceed = joinPoint.proceed();

        final long executionTime = System.currentTimeMillis() - start;

        String desc = fetchAnnotationInfo(joinPoint).desc();

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        System.out.println("Annotation desc is : " + desc);

        return proceed;
    }

    public LogExecutionTime fetchAnnotationInfo(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        String methodName = pjp.getSignature().getName();
        Class<?> targetClass = pjp.getTarget().getClass();
        Class<?>[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        LogExecutionTime operatorLogAnnotation = objMethod.getDeclaredAnnotation(LogExecutionTime.class);
        return operatorLogAnnotation;
    }
}
