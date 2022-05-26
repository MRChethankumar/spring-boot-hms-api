package com.example.hmsapi.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect  
@Component
public class Logger {
	@Pointcut("execution(* com.example.hmsapi.controllers.UserController.*(..)) || execution(* com.example.hmsapi.services.UserService.*(..))")  
    public void k(){}//pointcut name
      
    @Before("k()")//applying pointcut on before advice  
    public void myadvice(JoinPoint jp)//it is advice (before advice)  
    {  
        System.out.println("Additional concern of user controller.");   
    }  
}
