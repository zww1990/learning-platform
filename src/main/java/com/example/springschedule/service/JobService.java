package com.example.springschedule.service;

import java.io.File;

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
	private static final String REMOTE_URL = "https://gitee.com/zww1990/design-pattern.git";
	private static final String LOCAL_REPO_PATH = "d:/projects/design-pattern";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	private static final String BRANCH_NAME = "master";

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Git git = null;
		try {
			File localRepo = new File(LOCAL_REPO_PATH);
			CredentialsProvider cp = new UsernamePasswordCredentialsProvider(USERNAME, PASSWORD);
			if (!localRepo.exists()) {
				git = Git.cloneRepository().setURI(REMOTE_URL).setCredentialsProvider(cp).setBranch(BRANCH_NAME)
						.setDirectory(localRepo).call();
				log.info("从远程仓库[{}]克隆[{}]分支", REMOTE_URL, BRANCH_NAME);
			} else {
				git = Git.open(localRepo);
				git.pull().setRemoteBranchName(BRANCH_NAME).setCredentialsProvider(cp).call();
				log.info("从远程仓库[{}]拉取[{}]分支", REMOTE_URL, BRANCH_NAME);
			}
			Status status = git.status().call();
			log.info("Git Change: {}", status.getChanged());
			log.info("Git Modified: {}", status.getModified());
			log.info("Git UncommittedChanges: {}", status.getUncommittedChanges());
			log.info("Git Untracked: {}", status.getUntracked());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			git.close();
		}
	}
}
