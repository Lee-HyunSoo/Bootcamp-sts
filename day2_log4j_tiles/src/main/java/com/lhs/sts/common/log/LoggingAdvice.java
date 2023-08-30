package com.lhs.sts.common.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

	// target 메서드의 파라미터 등 정보를 출력.
	// execution으로 정의한 메서드 호출 전에 로그를 찍기 위한 @Before
	@Before("execution(* com.lhs.sts.*.service.*.*(..)) or execution(* com.lhs.sts.*.dao.*.*(..))")
	
	public void startLog(JoinPoint jp) {

		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// 전달되는 모든 파라미터들을 Object의 배열로 가져온다.
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		// 해당 Advice의 타입을 알아낸다.
		logger.info("2:" + jp.getKind());

		// 실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용.
		logger.info("3:" + jp.getSignature().getName());

		// target 객체를 알아낼 때 사용.
		logger.info("4:" + jp.getTarget().toString());

		// Advice를 행하는 객체를 알아낼 때 사용.
		logger.info("5:" + jp.getThis().toString());
	}

	// 메서드 호출 후에 로그를 찍기 위한 @After
	@After("execution(* com.lhs.sts.*.service.*.*(..)) or " + "execution(* com.lhs.sts.*.dao.*.*(..))")
	public void after(JoinPoint jp) {
		logger.info("-------------------------------------");
		logger.info("-------------------------------------");

		// 전달되는 모든 파라미터들을 Object의 배열로 가져온다.
		logger.info("1:" + Arrays.toString(jp.getArgs()));

		// 해당 Advice의 타입을 알아낸다.
		logger.info("2:" + jp.getKind());

		// 실행하는 대상 객체의 메소드에 대한 정보를 알아낼 때 사용.
		logger.info("3:" + jp.getSignature().getName());

		// target 객체를 알아낼 때 사용.
		logger.info("4:" + jp.getTarget().toString());

		// Advice를 행하는 객체를 알아낼 때 사용
		logger.info("5:" + jp.getThis().toString());
	}

	// 예외가 발생했을 땐 @Before, @After로 해결이 불가능 하기 때문에 @Around 추가, 예외 처리를 위해 Throwable
	// 어떤 쿼리를 실행할 때 동작 시간을 파악하기 위한 currentTimeMillis()
	@Around("execution(* com.lhs.sts.*.service.*.*(..)) or " + "execution(* com.lhs.sts.*.dao.*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis(); 
		logger.info(Arrays.toString(pjp.getArgs()));

		// 실제 타겟을 실행하는 부분이다. 이 부분이 없으면 advice가 적용된 메소드가 동작하지 않는다.
		Object result = pjp.proceed(); // proceed는 Exception 보다 상위 Throwable을 처리해야 한다.

		long endTime = System.currentTimeMillis();
		
		// target 메소드의 동작 시간을 출력한다.
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		logger.info("==============================");

		// Around를 사용할 경우 반드시 Object를 리턴해야 한다.
		return result;
	}
}
