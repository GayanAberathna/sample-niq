package lk.sample.application.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class ExecutionTimeAdvice {

    private static final Logger LOGGER = LogManager.getLogger(ExecutionTimeAdvice.class);

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(lk.sample.application.aspects.TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object object = point.proceed();
        stopWatch.stop();

        LOGGER.info("TraceId : {}: Class Name: {}. Method Name: {}. Time taken for Execution is : {}ms",
                request.getHeader("Trace_id"), point.getSignature().getDeclaringTypeName(),
                point.getSignature().getName(), stopWatch.getLastTaskTimeMillis());
        return object;
    }
}
