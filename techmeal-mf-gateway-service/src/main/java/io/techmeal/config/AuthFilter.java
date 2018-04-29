package io.techmeal.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class AuthFilter extends ZuulFilter {
	
	@Override
	public Object run() {
		System.out.println("*******************************************************");
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		HttpSession session = RequestContext.getCurrentContext().getRequest().getSession(false);
		if (session == null) {
			System.out.println("Null session");
			return null;
		}
		String header = request.getHeader("Authorization");
        System.out.println("Token is '" + header + "'");
        
		if (header != null) {
			context.addZuulRequestHeader("Authorization", header);
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
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
