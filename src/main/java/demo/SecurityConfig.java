package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.support.OAuth2RestClientHttpServiceGroupConfigurer;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

	@Bean
	OAuth2RestClientHttpServiceGroupConfigurer oauth2RestClientConfigurer(
			OAuth2AuthorizedClientManager authorizedClientManager) {
		return OAuth2RestClientHttpServiceGroupConfigurer.from(authorizedClientManager);
	}
}
