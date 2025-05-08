package demo.github.service;

import demo.github.User;

import org.springframework.security.oauth2.client.annotation.ClientRegistrationId;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/user")
public interface UserService {

	@ClientRegistrationId("github")
	@GetExchange
	User getAuthenticatedUser();
}
