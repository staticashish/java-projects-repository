package com.skeleton.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = false)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		System.out.println("inside configureGlobal");
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http .csrf().disable() .authorizeRequests() .antMatchers("/",
		 * "/login").permitAll() //
		 * .antMatchers("/controller/user/create").hasRole("ADMIN")
		 * .anyRequest()
		 * .authenticated().and().httpBasic().and().formLogin().loginPage
		 * ("/login").permitAll();
		 */
		http
		.exceptionHandling().accessDeniedPage("/login")
			.authenticationEntryPoint(entryPoint())
				//.and()
				//.httpBasic()
			.and()
				.csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/js/**", "/", "/index.html", "/home.html",
						"/login.html", "/registerUser.html",
						"/user/create", "/role/getallroles",
						"/authenticate")
				.permitAll()
				.anyRequest()
				.fullyAuthenticated()
				.and()
				.formLogin()
				.failureUrl("/login")
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout.html"))
				.logoutSuccessUrl("/login.html");

		http.addFilterBefore(new WebSecurityFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Bean
	public RestAuthenticationEntryPoint entryPoint() {
		RestAuthenticationEntryPoint restAuthenticationEntryPoint = new RestAuthenticationEntryPoint();
		return restAuthenticationEntryPoint;
	}
}
