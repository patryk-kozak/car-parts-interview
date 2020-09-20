package net.hackbee.interview.carparts;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* net.hackbee.interview.carparts..*(..))")
    public void publicMethod() {
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void inServices() {
    }

    @Pointcut("publicMethod() && inServices()")
    public void servicePublicMethods() {
    }

    @Before("servicePublicMethods()")
    public void logEnterMethod(JoinPoint thisJoinPoint) {
        logger.info("[LOG_ENTER] Entering {} with arguments {}", thisJoinPoint.getSignature().toShortString(),
                Arrays.toString(thisJoinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "servicePublicMethods()", returning = "result")
    public void logExitMethod(JoinPoint thisJoinPoint, Object result) {
        logger.info("[LOG_EXIT] Exiting {} with result {}", thisJoinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "servicePublicMethods()", throwing = "exception")
    public void logException(JoinPoint thisJoinPoint, Exception exception) {
        logger.info("[LOG_ERROR] Error thrown in {} with reason {}", thisJoinPoint.getSignature().toShortString(), exception);
    }
}
