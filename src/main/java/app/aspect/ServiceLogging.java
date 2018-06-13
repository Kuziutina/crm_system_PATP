package app.aspect;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class ServiceLogging {

    private Logger logger = LogManager.getLogger(ServiceLogging.class);

    @Pointcut("execution(* app.*.*.*(..))")
    public void selectMethods() {
    }

    @Before("selectMethods()")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Method " + joinPoint.getSignature().getName() + " is called");
    }

    @AfterThrowing(pointcut = "within(app.service..*)", throwing = "ex")
    public void logException(JoinPoint jp, Exception ex) {
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        logger.error(method.getDeclaringClass().getName() + "." + method.getName() + " has problem:  " + ex);
    }

    @Before("execution(* app.controller.MainController.getMain(..))")
    private void loggingLoadingNews(JoinPoint jp){
        logger.info("Loading news.");
    }


}
