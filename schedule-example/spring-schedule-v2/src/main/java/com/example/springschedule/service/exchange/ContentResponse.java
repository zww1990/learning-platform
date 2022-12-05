package com.example.springschedule.service.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 获取仓库具体路径下的内容
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月5日,下午3:31:01
 */
@Getter
@Setter
@ToString
public class ContentResponse {
	private String type;
	private String encoding;
	private int size;
	private String name;
	private String path;
	private String content;
	private String sha;
	private String url;
	@JsonProperty("html_url")
	private String htmlUrl;
	@JsonProperty("download_url")
	private String downloadUrl;
}
