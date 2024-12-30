package com.hexaware.amazecare.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectService {
	Logger logger =  LoggerFactory.getLogger(AspectService.class);
	@Around("execution(* com.hexaware.amazecare.service..*.*(..))")
	public Object recordExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		 long startTime = System.currentTimeMillis();
		Object object =  proceedingJoinPoint.proceed(); //this calls the method 
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		logger.info("Execution time of method " + proceedingJoinPoint.getSignature() + "was " + executionTime + "mills");
		return object;                                       
	}
}
