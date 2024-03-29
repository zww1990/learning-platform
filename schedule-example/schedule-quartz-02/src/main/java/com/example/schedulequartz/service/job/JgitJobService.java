package com.example.schedulequartz.service.job;

import com.example.schedulequartz.config.ApplicationConfig;
import com.example.schedulequartz.config.ApplicationConfig.JgitConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Jgit Job Service
 *
 * @author zhang weiwei
 * @since 2022年8月5日, 下午8:18:07
 */
@DisallowConcurrentExecution
@Slf4j
@AllArgsConstructor
public class JgitJobService extends QuartzJobBean {
    private ApplicationConfig appConfig;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JgitConfig jc = this.appConfig.getJgitConfig();
        if (!this.validated(jc)) {
            return;
        }
        Git git = null;
        try {
            File localRepo = new File(jc.getLocalDirectory());
            CredentialsProvider cp = new UsernamePasswordCredentialsProvider(jc.getUsername(), jc.getPassword());
            if (!localRepo.exists()) {
                log.info("正在从远程仓库[{}]克隆[{}]分支", jc.getRemoteUrl(), jc.getBranchName());
                git = Git.cloneRepository()//
                        .setURI(jc.getRemoteUrl())//
                        .setCredentialsProvider(cp)//
                        .setBranch(jc.getBranchName())//
                        .setDirectory(localRepo).call();
                log.info("已从远程仓库[{}]克隆[{}]分支", jc.getRemoteUrl(), jc.getBranchName());
            } else {
                log.info("正在从远程仓库[{}]拉取[{}]分支", jc.getRemoteUrl(), jc.getBranchName());
                git = Git.open(localRepo);
                git.pull().setRemoteBranchName(jc.getBranchName()).setCredentialsProvider(cp).call();
                log.info("已从远程仓库[{}]拉取[{}]分支", jc.getRemoteUrl(), jc.getBranchName());
            }
            LocalDateTime now = LocalDateTime.now().withNano(0);
            String content = String.format(jc.getContentFormat(), DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
            String filePattern = String.format(jc.getPathFormat(),
                    DateTimeFormatter.ofPattern(jc.getDatePattern()).format(now));
            Files.write(Paths.get(jc.getLocalDirectory(), filePattern), Arrays.asList(content),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            git.add().addFilepattern(filePattern).call();
            Status status = git.status().call();
            log.info("Git Added: {}", status.getAdded());
            log.info("Git Changed: {}", status.getChanged());
            log.info("Git Modified: {}", status.getModified());
            log.info("Git UncommittedChanges: {}", status.getUncommittedChanges());
            log.info("Git Untracked: {}", status.getUntracked());
            git.commit()//
                    .setMessage(content)//
                    .setAuthor(jc.getAuthor(), jc.getUsername())//
                    .setCommitter(jc.getAuthor(), jc.getUsername())//
                    .call();
            git.push().setCredentialsProvider(cp).call();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        } finally {
            git.close();
        }
    }

    private boolean validated(JgitConfig jc) {
        if (!StringUtils.hasText(jc.getRemoteUrl())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置远程仓库地址"));
            return false;
        }
        if (!StringUtils.hasText(jc.getLocalDirectory())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置本地克隆目录"));
            return false;
        }
        if (!StringUtils.hasText(jc.getUsername())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置用户名"));
            return false;
        }
        if (!StringUtils.hasText(jc.getAuthor())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置作者"));
            return false;
        }
        if (!StringUtils.hasText(jc.getPassword())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置密码"));
            return false;
        }
        if (!StringUtils.hasText(jc.getBranchName())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置分支名"));
            return false;
        }
        if (!StringUtils.hasText(jc.getDatePattern())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置日期格式"));
            return false;
        }
        if (!StringUtils.hasText(jc.getPathFormat())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置文件的路径格式"));
            return false;
        }
        if (!StringUtils.hasText(jc.getContentFormat())) {
            log.error("属性配置不正确，任务终止", new RuntimeException("请设置文件的内容格式"));
            return false;
        }
        return true;
    }
}
