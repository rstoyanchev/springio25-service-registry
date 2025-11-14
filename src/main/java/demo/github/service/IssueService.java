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

package demo.github.service;

import java.util.List;

import demo.github.Assignee;
import demo.github.Issue;
import demo.github.State;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;

@HttpExchange("/repos/{org}/{repo}/issues")
public interface IssueService {

	@GetExchange
	List<Issue> getIssuesForMilestone(
			@PathVariable String org, @PathVariable String repo, @RequestParam int milestone,
			@RequestParam State state);

	@SuppressWarnings("DataFlowIssue")
	default List<Issue> getOpenIssuesForMilestone(String org, String repo, int milestone) {
		List<Issue> issues = getIssuesForMilestone(org, repo, milestone, State.open);
		issues.sort(comparing(Issue::assignee, nullsLast(comparing(Assignee::login))));
		return issues;
	}

}
