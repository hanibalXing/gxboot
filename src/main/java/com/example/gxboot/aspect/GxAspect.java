package com.example.gxboot.aspect;

import com.example.gxboot.annotation.GxAnnotation;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gx
 * @ClassName: GxAspect
 * @Description: java类作用描述
 * @date 2019/5/18 0:04
 * @Version: 1.0
 * @since
 */
@Aspect    //该标签把LoggerAspect类声明为一个切面
@Order(1)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把LoggerAspect类放到IOC容器中
public class GxAspect {
    @Pointcut("execution(public * com.example.gxboot.controller.*Controller.*(..))")
    public void declearJoinPointExpression() {
    }

    @Around(value = "declearJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint point) {

        Object result;
        String methodName = point.getSignature().getName();
        try {
            //前置通知
            //System.out.println("The method "+ methodName+" start. param<"+ Arrays.asList(point.getArgs())+">");
            //执行目标方法
            result = point.proceed();
            List content;
            if (result instanceof Page) {
                Page result1 = (Page) result;
                content = ((Page) result).getContent();
                content.forEach(this::makeValue);
            } else if (result instanceof ResponseEntity) {
                makeValue(((ResponseEntity) result).getBody());
            } else {
                makeValue(result);
            }

          /*  if (clazz.isAssignableFrom(Page.class)) {
                Page result1 = (Page) result;
                List content = result1.getContent();
            }*/

            //返回通知
            System.out.println("The method " + methodName + " end. result<" + result + ">");
        } catch (Throwable e) {
            //异常通知
            System.out.println("this method " + methodName + " end.ex message<" + e + ">");
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("The method " + methodName + " end.");
        return result;
    }

    private void makeValue(Object result) {
        Class<?> clazz = result.getClass();
        Arrays.asList(clazz.getDeclaredFields()).stream()
                .filter(field -> null != field.getAnnotation(GxAnnotation.class))
                .forEach(field -> {
                    try {
                        BeanUtils.setProperty(result, field.getName(), BeanUtils.getProperty(result, field.getName()) + "123");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }
}
