package com.rich.rest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

	@Pointcut("@annotation(com.rich.rest.aop.LogAction)")
	public void log() {}


	@Before("log()")
	public void doBeforeController(JoinPoint joinPoint) {
		log.info("Bfter execution of {}", joinPoint);
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		showClientInfo(joinPoint, request);
		showArgsInfo(joinPoint);

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		LogAction logAction = method.getAnnotation(LogAction.class);
		log.info("action name: {}", logAction.value());
		log.info("action enabled: {}", logAction.enabled());
	}

	private void showClientInfo(JoinPoint joinPoint, HttpServletRequest request) {
		log.info("Url: {}", request.getRequestURL().toString());
		log.info("HTTP_METHOD: {}", request.getMethod());
		log.info("IP: {}", request.getRemoteAddr());
		log.info("CLASS_METHOD: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		log.info("Args: {}", Arrays.toString(joinPoint.getArgs()));
	}

	private void showArgsInfo(JoinPoint joinPoint) {
		Object[] agrsArray = joinPoint.getArgs();
		Object argResult = new Object();
		if(agrsArray.length > 0) {
			argResult = agrsArray[0];
		}
		log.info("First arg: {}", argResult.toString());
	}

	@After("log()")
	public void after(JoinPoint joinPoint) {
		log.info("After execution of {}", joinPoint);
	}

	@AfterReturning(pointcut = "log()", returning = "retValue")
	public void doAfterController(JoinPoint joinPoint, Object retValue) {
		log.info("{} returned with value {}", joinPoint, retValue);
	}
}
