package io.gmind7.spring.foundation.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect{
	
	protected Logger log = LoggerFactory.getLogger(ExceptionAspect.class);
	
    @AfterThrowing(pointcut="execution(* io.gmind7..*Impl.*(..))", throwing="error")
    public void logAfterThrowing(JoinPoint thisJoinPoint, Throwable error) throws Exception {  
    	
    	StringBuffer buf = new StringBuffer();
		
    	Object[] args = thisJoinPoint.getArgs();
    	
	    buf.append("\n**** Excepiton Aspect executed ***********************************************");
	    buf.append("\n* " + thisJoinPoint.getTarget().getClass().getName()	+ "." + thisJoinPoint.getSignature().getName() + "()");
		
        if (args != null && args.length > 0) {
        	for (int i = 0; i < args.length; i++) {
    			buf.append("\n* " + (i + 1) + " arg's value : " + args[i].toString());
    		}
    		buf.append("\n******************************************************************************\n");
        } else {
            buf.append("\n**** No arguments ************************************************************\n");
        }
        log.debug(buf.toString());
        log.debug(error.toString());
        
    }
    
}    