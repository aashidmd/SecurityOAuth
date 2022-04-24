package com.security.SecurityOAuth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;

@Configuration
@EnableAuthorizationServer
public class AuthorizationserverConfig 
extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("aashid")
		.secret("md123")
		.accessTokenValiditySeconds(5000)
		.scopes("read").authorizedGrantTypes("password","refresh_token")
		.and()
		
		
		//grant type = password
		
		
		.withClient("aashid2")
		.secret("md456")
		.accessTokenValiditySeconds(5000)
		.scopes("read")
		.authorizedGrantTypes("authorization_code")
		.redirectUris("http://localhost:8083")
		.and()
		
		//grant type = authorization_code  
		
		
		.withClient("aashid3")
		.secret("md789")
		.scopes("read")
		.accessTokenValiditySeconds(5000)
		.authorizedGrantTypes("client_credentials","refresh_token");
		//grant type = client_credentials
		//grant type = refresh_token (it is always use with other grant type )
	}


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(NoOpPasswordEncoder.getInstance())
//		        .checkTokenAccess("permitAll()")
		        .checkTokenAccess("isAuthenticated()");
		
		        
	}

	
	
}
