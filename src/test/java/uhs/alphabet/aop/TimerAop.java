package uhs.alphabet.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.rules.Stopwatch;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* uhs.alphabet..*.*(..))")
    private void cut() {}

    @Pointcut("@annotation(uhs.alphabet.annotation.Timer)")
    public void enableTimer() {}

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long before = System.currentTimeMillis();

        Object obj = proceedingJoinPoint.proceed();

        Long after = System.currentTimeMillis();

        Long res = after - before;

        System.out.println(obj.getClass().getSimpleName()+" method takes "+res/1000+"sec");
    }
}
