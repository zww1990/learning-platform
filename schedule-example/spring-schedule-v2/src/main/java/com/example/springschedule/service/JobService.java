package com.example.springschedule.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class JobService extends QuartzJobBean {
	private static final Logger log = LoggerFactory.getLogger(JobService.class);
	private static final String REMOTE_URL = "https://gitee.com/zww1990/learning-platform.git";
	private static final String LOCAL_REPO_PATH = "d:/projects/learning-platform";
	private static final String USERNAME = "";
	private static final String AUTHNAME = "";
	private static final String PASSWORD = "";
	private static final String BRANCH_NAME = "master";
	private static final String DATE_PATTERN = "yyyyMMdd";
	private static final String RESOURCES_FOLDER = "design-pattern-example/src/main/resources";

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Git git = null;
		try {
			File localRepo = new File(LOCAL_REPO_PATH);
			CredentialsProvider cp = new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD);
			if (!localRepo.exists()) {
				log.info("正在从远程仓库[{}]克隆[{}]分支", REMOTE_URL, BRANCH_NAME);
				git = Git.cloneRepository().setURI(REMOTE_URL).setCredentialsProvider(cp).setBranch(BRANCH_NAME)
						.setDirectory(localRepo).call();
				log.info("已从远程仓库[{}]克隆[{}]分支", REMOTE_URL, BRANCH_NAME);
			} else {
				log.info("正在从远程仓库[{}]拉取[{}]分支", REMOTE_URL, BRANCH_NAME);
				git = Git.open(localRepo);
				git.pull().setRemoteBranchName(BRANCH_NAME).setCredentialsProvider(cp).call();
				log.info("已从远程仓库[{}]拉取[{}]分支", REMOTE_URL, BRANCH_NAME);
			}
			String content = String.format("hello world %s", System.currentTimeMillis());
			Files.write(
					Paths.get(LOCAL_REPO_PATH, RESOURCES_FOLDER,
							String.format("%s.txt", LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)))),
					Arrays.asList(content), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
			git.add().addFilepattern(RESOURCES_FOLDER).call();
			Status status = git.status().call();
			log.info("Git Added: {}", status.getAdded());
			log.info("Git Changed: {}", status.getChanged());
			log.info("Git Modified: {}", status.getModified());
			log.info("Git UncommittedChanges: {}", status.getUncommittedChanges());
			log.info("Git Untracked: {}", status.getUntracked());
			git.commit().setMessage(content).setAuthor(AUTHNAME, USERNAME).setCommitter(AUTHNAME, USERNAME).call();
			git.push().setCredentialsProvider(cp).call();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			git.close();
		}
	}
}
