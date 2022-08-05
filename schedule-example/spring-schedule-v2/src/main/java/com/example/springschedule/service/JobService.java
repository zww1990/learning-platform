package com.example.springschedule.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.annotation.Resource;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import com.example.springschedule.config.GitProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * Job Service
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:18:07
 */
@DisallowConcurrentExecution
@Slf4j
public class JobService extends QuartzJobBean {
	@Resource
	private GitProperties gitProps;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		if (!this.validated()) {
			return;
		}
		Git git = null;
		try {
			File localRepo = new File(this.gitProps.getLocalDirectory());
			CredentialsProvider cp = new UsernamePasswordCredentialsProvider(this.gitProps.getUsername(),
					this.gitProps.getPassword());
			if (!localRepo.exists()) {
				log.info("正在从远程仓库[{}]克隆[{}]分支", this.gitProps.getRemoteUrl(), this.gitProps.getBranchName());
				git = Git.cloneRepository()//
						.setURI(this.gitProps.getRemoteUrl())//
						.setCredentialsProvider(cp)//
						.setBranch(this.gitProps.getBranchName())//
						.setDirectory(localRepo).call();
				log.info("已从远程仓库[{}]克隆[{}]分支", this.gitProps.getRemoteUrl(), this.gitProps.getBranchName());
			} else {
				log.info("正在从远程仓库[{}]拉取[{}]分支", this.gitProps.getRemoteUrl(), this.gitProps.getBranchName());
				git = Git.open(localRepo);
				git.pull().setRemoteBranchName(this.gitProps.getBranchName()).setCredentialsProvider(cp).call();
				log.info("已从远程仓库[{}]拉取[{}]分支", this.gitProps.getRemoteUrl(), this.gitProps.getBranchName());
			}
			String content = String.format("hello world %s", System.currentTimeMillis());
			Files.write(Paths.get(//
					this.gitProps.getLocalDirectory(), this.gitProps.getFilePattern(),
					String.format("%s.txt", LocalDate.now()//
							.format(DateTimeFormatter.ofPattern(this.gitProps.getDatePattern())))),
					Arrays.asList(content), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
			git.add().addFilepattern(this.gitProps.getFilePattern()).call();
			Status status = git.status().call();
			log.info("Git Added: {}", status.getAdded());
			log.info("Git Changed: {}", status.getChanged());
			log.info("Git Modified: {}", status.getModified());
			log.info("Git UncommittedChanges: {}", status.getUncommittedChanges());
			log.info("Git Untracked: {}", status.getUntracked());
			git.commit()//
					.setMessage(content)//
					.setAuthor(this.gitProps.getAuthor(), this.gitProps.getUsername())//
					.setCommitter(this.gitProps.getAuthor(), this.gitProps.getUsername())//
					.call();
			git.push().setCredentialsProvider(cp).call();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			git.close();
		}
	}

	private boolean validated() {
		if (!StringUtils.hasText(this.gitProps.getRemoteUrl())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置远程仓库地址"));
			return false;
		}
		if (!StringUtils.hasText(this.gitProps.getLocalDirectory())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置本地克隆目录"));
			return false;
		}
		if (!StringUtils.hasText(this.gitProps.getUsername())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置用户名"));
			return false;
		}
		if (!StringUtils.hasText(this.gitProps.getAuthor())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置作者"));
			return false;
		}
		if (!StringUtils.hasText(this.gitProps.getPassword())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置密码"));
			return false;
		}
		if (!StringUtils.hasText(this.gitProps.getBranchName())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置分支名"));
			return false;
		}
		if (!StringUtils.hasText(this.gitProps.getDatePattern())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置日期格式"));
			return false;
		}
		if (!StringUtils.hasText(this.gitProps.getFilePattern())) {
			log.error("属性配置不正确，任务终止", new RuntimeException("请设置资源目录"));
			return false;
		}
		return true;
	}
}
