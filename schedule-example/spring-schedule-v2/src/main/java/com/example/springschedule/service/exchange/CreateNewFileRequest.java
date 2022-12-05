package com.example.springschedule.service.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 新建文件请求参数
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月5日,下午2:29:11
 */
@Getter
@Setter
@ToString
public class CreateNewFileRequest {
	/** 用户授权码 */
	@JsonProperty("access_token")
	private String accessToken;
	/** 文件内容, 要用 base64 编码 */
	private String content;
	/** 提交信息 */
	private String message;

	public CreateNewFileRequest() {
		super();
	}

	/**
	 * @param accessToken 用户授权码
	 * @param content     文件内容, 要用 base64 编码
	 * @param message     提交信息
	 */
	public CreateNewFileRequest(String accessToken, String content, String message) {
		super();
		this.accessToken = accessToken;
		this.content = content;
		this.message = message;
	}
}
