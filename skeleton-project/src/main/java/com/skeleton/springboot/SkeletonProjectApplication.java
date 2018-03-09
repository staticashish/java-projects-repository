package com.skeleton.springboot;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jersey.JerseyProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SkeletonProjectApplication extends SpringBootServletInitializer {

	
	@Autowired
	private JerseyProperties jersey;

	@Autowired
	private ResourceConfig config;

	public static void main(String[] args) {
        SpringApplication.run(SkeletonProjectApplication.class, args);
    }
    
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector());
        tomcat.addContextCustomizers(getWelcomePage());
        tomcat.setPort(7002);
        return tomcat;
    }

    private Connector createSslConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            connector.setScheme("http");
            connector.setSecure(false);
            connector.setPort(7003);
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
