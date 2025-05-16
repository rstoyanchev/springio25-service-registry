package demo;

import java.util.List;
import java.util.stream.Collectors;

import demo.github.Issue;
import demo.github.Milestone;
import demo.github.Release;
import demo.github.Repository;
import demo.github.service.IssueService;
import demo.github.service.MilestoneService;
import demo.github.service.ReleaseService;
import demo.github.service.RepositoryService;
import demo.stackoverflow.Container;
import demo.stackoverflow.Question;
import demo.stackoverflow.service.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	private static final Logger logger = LogManager.getLogger(DemoApplication.class);

	@Bean
	public ApplicationRunner commandLineRunner(
			@Value("${github.org}") String org, @Value("${github.repo}") String repo,
			RepositoryService repositoryService, IssueService issueService,
			MilestoneService milestoneService, ReleaseService releaseService,
			QuestionService questionService) {

		return (args) -> {

			Repository repository = repositoryService.getRepository(org, repo);
			List<Milestone> milestones = milestoneService.getMilestones(org, repo);
			List<Issue> issues = issueService.getOpenIssuesForMilestone(org, repo, milestones.get(0).number());
			List<Release> releases = releaseService.getRecentReleases(org, repo);
			Container<Question> container = questionService.questions(repo, "votes");

			logger.info(String.format("""


					%s

					Milestones: %s
					Issues for milestone %s%s
					Recent releases: %s
					StackOverflow questions: %s
					""",
					repository, formatList(milestones), milestones.get(0).title(),
					formatList(issues), formatList(releases),
					formatList(container.items())));
		};
	}

	private String formatList(List<?> list) {
		return list.stream().map(Object::toString).collect(Collectors.joining("\n\t", "\n\t", "\n"));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
