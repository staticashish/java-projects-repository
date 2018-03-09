package com.skeleton.springboot;

import java.util.Arrays;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SkeletonProjectApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SkeletonProjectApplication.class, args);
		
		String[] springBootBeans = ctx.getBeanDefinitionNames();
		Arrays.sort(springBootBeans);
		for (String beanName : springBootBeans) {
			System.out.println(beanName);
		}
    }
	
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        tomcat.addContextCustomizers(getWelcomePage());
        tomcat.setPort(7001);
        return tomcat;
    }

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            connector.setScheme("http");
            connector.setSecure(false);
            connector.setPort(7000);
            protocol.setMinSpareThreads(10);
            return connector;
    }
    
    private TomcatContextCustomizer getWelcomePage(){
    return new TomcatContextCustomizer() {
			@Override
	        public void customize(Context context) {
	            context.addWelcomeFile("/index.html");
	        }
	    };
    }
}
