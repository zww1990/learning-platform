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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import com.example.springschedule.config.ApplicationConfig;
import com.example.springschedule.config.ApplicationConfig.GitConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * Jgit Job Service
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:18:07
 */
@DisallowConcurrentExecution
@Slf4j
public class JgitJobService extends QuartzJobBean {
	@Autowired
	private ApplicationConfig appConfig;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		GitConfig gitConfig = this.appConfig.getGitConfig();
		if (!this.validated(gitConfig)) {
			return;
		}
		Git git = null;
		try {
			File localRepo = new File(gitConfig.getLocalDirectory());
			CredentialsProvider cp = new UsernamePasswordCredentialsProvider(gitConfig.getUsername(),
					gitConfig.getPassword());
			if (!localRepo.exists()) {
				log.info("正在从远程仓库[{}]克隆[{}]分支", gitConfig.getRemoteUrl(), gitConfig.getBranchName());
				git = Git.cloneRepository()//
						.setURI(gitConfig.getRemoteUrl())//
						.setCredentialsProvider(cp)//
						.setBranch(gitConfig.getBranchName())//
						.setDirectory(localRepo).call();
				log.info("已从远程仓库[{}]克隆[{}]分支", gitConfig.getRemoteUrl(), gitConfig.getBranchName());
			} else {
				log.info("正在从远程仓库[{}]拉取[{}]分支", gitConfig.getRemoteUrl(), gitConfig.getBranchName());
				git = Git.open(localRepo);
				git.pull().setRemoteBranchName(gitConfig.getBranchName()).setCredentialsProvider(cp).call();
				log.info("已从远程仓库[{}]拉取[{}]分支", gitConfig.getRemoteUrl(), gitConfig.getBranchName());
			}
			String content = String.format("hello world %s", System.currentTimeMillis());
			Files.write(Paths.get(//
					gitConfig.getLocalDirectory(), gitConfig.getFilePattern(), String.format("%s.txt", LocalDate.now()//
							.format(DateTimeFormatter.ofPattern(gitConfig.getDatePattern())))),
					Arrays.asList(content), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
			git.add().addFilepattern(gitConfig.getFilePattern()).call();
			Status status = git.status().call();
			log.info("Git Added: {}", status.getAdded());
			log.info("Git Changed: {}", status.getChanged());
			log.info("Git Modified: {}", status.getModified());
			log.info("Git UncommittedChanges: {}", status.getUncommittedChanges());
			log.info("Git Untracked: {}", status.getUntracked());
			git.commit()//
					.setMessage(content)//
					.setAuthor(gitConfig.getAuthor(), gitConfig.getUsername())//
					.setCommitter(gitConfig.getAuthor(), gitConfig.getUsername())//
					.call();
			git.push().setCredentialsProvider(cp).call();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		} finally {
			git.close();
		}
	}

	private boolean validated(GitConfig gitConfig) {
		if (!StringUtils.hasText(gitConfig.getRemoteUrl())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置远程仓库地址"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getLocalDirectory())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置本地克隆目录"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getUsername())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置用户名"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getAuthor())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置作者"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getPassword())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置密码"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getBranchName())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置分支名"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getDatePattern())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置日期格式"));
			return false;
		}
		if (!StringUtils.hasText(gitConfig.getFilePattern())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置资源目录"));
			return false;
		}
		return true;
	}
}
