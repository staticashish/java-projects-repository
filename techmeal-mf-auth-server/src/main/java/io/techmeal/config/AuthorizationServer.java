package io.techmeal.config;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {


	@Autowired
	private AuthenticationManager authenticationManager; 

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("client")
		.secret("client")
		.authorizedGrantTypes("authorization_code","password","client_credentials")
		.scopes("user_info","openid")
		.autoApprove(true);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(appTokenEnhancer(),accessTokenConverter()));

		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

		//for Authorization server
		KeyStoreKeyFactory keyStoreKeyFactory = 
				new KeyStoreKeyFactory(new ClassPathResource("mfapp.jks"), "techmeal".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mfapp"));
		
		//for Resource Server
		Resource resource = new ClassPathResource("public.txt");
	    String publicKey = null;
	    try {
	        publicKey = IOUtils.toString(resource.getInputStream());
	        System.out.println("**********************************"+publicKey);
	    } catch (final IOException e) {
	        throw new RuntimeException(e);
	    }
	    converter.setVerifierKey(publicKey);
		
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Bean
	public TokenEnhancer appTokenEnhancer() {
		return new AppTokenEnhancer();
	}
}
