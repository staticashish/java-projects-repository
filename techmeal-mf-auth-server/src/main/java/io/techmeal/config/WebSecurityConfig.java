package io.techmeal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService appUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.requestMatchers()
			.antMatchers("/login", "/oauth/authorize")
			.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
			.and()
				.logout()
                	.invalidateHttpSession(true)
                	.clearAuthentication(true)
                	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                	.logoutSuccessUrl("/login?logout")
                	.permitAll()
            .and()
            	.exceptionHandling()
            		.accessDeniedPage("/access-denied");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(authenticationManager)
			.userDetailsService(appUserDetailsService);
	}
	
}
