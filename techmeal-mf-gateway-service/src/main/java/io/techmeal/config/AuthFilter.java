package io.techmeal.config;

import java.util.Enumeration;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthFilter extends ZuulFilter {
	
	 private static Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
	 
	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		
		String header = request.getHeader("Authorization");
        System.out.println("Token is '" + header + "'");
        
		if (header != null) {
			context.addZuulRequestHeader("Authorization", header);
		}
		
		LOGGER.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            LOGGER.debug("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
        }

        Enumeration params = request.getParameterNames();
        while(params.hasMoreElements()){
        	String paramName = (String)params.nextElement();
            LOGGER.debug("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                LOGGER.debug("Cookie Name - "+ cookies[i].getName() +", Value - "+cookies[i].getValue());
            }
        }
		
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public int filterOrder() {
		return 1110;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
