package com.example.springschedule.service.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Gitee API
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月5日,下午2:02:26
 */
@HttpExchange("https://gitee.com/api/v5/repos/{owner}/{repo}/contents/{path}")
public interface GiteeHttpExchange {

	Logger log = LoggerFactory.getLogger(GiteeHttpExchange.class);

	/**
	 * 获取仓库具体路径下的内容
	 * 
	 * @param owner       仓库所属空间地址(企业、组织或个人的地址path)
	 * @param repo        仓库路径(path)
	 * @param path        文件的路径
	 * @param accessToken 用户授权码
	 */
	@GetExchange
	String getContent(//
			@PathVariable String owner, //
			@PathVariable String repo, //
			@PathVariable String path, //
			@RequestParam("access_token") String accessToken);

	/**
	 * 新建文件
	 * 
	 * @param owner   仓库所属空间地址(企业、组织或个人的地址path)
	 * @param repo    仓库路径(path)
	 * @param path    文件的路径
	 * @param request {@link CreateNewFileRequest}
	 */
	@PostExchange
	String createNewFile(//
			@PathVariable String owner, //
			@PathVariable String repo, //
			@PathVariable String path, //
			@RequestBody CreateNewFileRequest request);

	/**
	 * 更新文件
	 * 
	 * @param owner   仓库所属空间地址(企业、组织或个人的地址path)
	 * @param repo    仓库路径(path)
	 * @param path    文件的路径
	 * @param request {@link UpdateFileRequest}
	 */
	@PutExchange
	String updateFile(//
			@PathVariable String owner, //
			@PathVariable String repo, //
			@PathVariable String path, //
			@RequestBody UpdateFileRequest request);

	default ContentResponse defaultGetContent(String owner, String repo, String path, String accessToken,
			ObjectMapper json) {
		String content = this.getContent(owner, repo, path, accessToken);
		if ("[]".equals(content)) {
			return new ContentResponse();
		}
		try {
			return json.readValue(content, ContentResponse.class);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return new ContentResponse();
		}
	}

}
