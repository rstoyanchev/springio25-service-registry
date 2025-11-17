/*
 * Copyright 2002-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo;

import demo.github.service.IssueService;
import demo.github.service.MilestoneService;
import demo.github.service.ReleaseService;
import demo.github.service.RepositoryService;
import demo.stackoverflow.service.QuestionService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class DemoConfig {

	// Clients

	@Bean
	RestClient githubRestClient() {
		return RestClient.builder()
				.baseUrl("https://api.github.com")
				.defaultHeader("Accept", "application/vnd.github.v3+json")
				.build();
	}

	@Bean
	RestClient stackOverflowRestClient() {
		return RestClient.builder()
				.baseUrl("https://api.stackexchange.com?site=stackoverflow")
				.build();
	}

	// HTTP Service proxy factories

	@Bean
	HttpServiceProxyFactory githubProxyFactory(RestClient githubRestClient) {
		return HttpServiceProxyFactory.builder()
				.exchangeAdapter(RestClientAdapter.create(githubRestClient))
				.build();
	}


	@Bean
	HttpServiceProxyFactory stackOverflowProxyFactory(RestClient stackOverflowRestClient) {
		return HttpServiceProxyFactory.builder()
				.exchangeAdapter(RestClientAdapter.create(stackOverflowRestClient))
				.build();
	}

	// GitHub HTTP Service proxies

	@Bean
	IssueService issueService(HttpServiceProxyFactory githubProxyFactory) {
		return githubProxyFactory.createClient(IssueService.class);
	}

	@Bean
	MilestoneService milestoneService(HttpServiceProxyFactory githubProxyFactory) {
		return githubProxyFactory.createClient(MilestoneService.class);
	}

	@Bean
	ReleaseService releaseService(HttpServiceProxyFactory githubProxyFactory) {
		return githubProxyFactory.createClient(ReleaseService.class);
	}

	@Bean
	RepositoryService repositoryService(HttpServiceProxyFactory githubProxyFactory) {
		return githubProxyFactory.createClient(RepositoryService.class);
	}

	// StackOverflow HTTP Service proxies

	@Bean
	QuestionService questionService(HttpServiceProxyFactory stackOverflowProxyFactory) {
		return stackOverflowProxyFactory.createClient(QuestionService.class);
	}

}
