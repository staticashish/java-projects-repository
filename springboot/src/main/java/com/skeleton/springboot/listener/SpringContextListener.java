package com.skeleton.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextListener implements
		ApplicationListener<ApplicationContextEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(SpringContextListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		// TODO Auto-generated method stub
		
		LOG.debug("ApplicationListener Invoked At Spring Container Startup event {}",event.toString());
	}

}
