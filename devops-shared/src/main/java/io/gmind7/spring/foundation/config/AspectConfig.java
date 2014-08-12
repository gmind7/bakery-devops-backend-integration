package io.gmind7.spring.foundation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true) // <aop:aspectj-autoproxy>
public class AspectConfig {

}
