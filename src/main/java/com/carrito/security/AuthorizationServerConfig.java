package com.carrito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private Environment env;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		
		
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthonticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		int tiempoExpiracionToken =Integer.parseInt(env.getProperty("config.security.oauth.jwt.token.seconds"));
		
		clients.inMemory().withClient(env.getProperty("config.security.oauth.client.id"))
				.secret(env.getProperty("config.security.oauth.client.secret")).scopes("read", "write")
				.authorizedGrantTypes("password").accessTokenValiditySeconds(tiempoExpiracionToken);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		endpoints.authenticationManager(authenticationManager).accessTokenConverter(accesTokenConverter())
				.tokenStore(tokenStore());
	}

	@Bean
	public JwtTokenStore tokenStore() {

		return new JwtTokenStore(accesTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accesTokenConverter() {

		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));

		return tokenConverter;
	}
}
