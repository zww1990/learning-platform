package com.example.springschedule.service.exchange;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 更新文件请求参数
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月5日,下午2:50:57
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UpdateFileRequest extends CreateNewFileRequest {
	/** 文件的 Blob SHA，可通过 [获取仓库具体路径下的内容] API 获取 */
	private String sha;

	/**
	 * @param accessToken 用户授权码
	 * @param content     文件内容, 要用 base64 编码
	 * @param message     提交信息
	 * @param sha         文件的 Blob SHA，可通过 [获取仓库具体路径下的内容] API 获取
	 */
	public UpdateFileRequest(String accessToken, String content, String message, String sha) {
		super(accessToken, content, message);
		this.sha = sha;
	}

	public UpdateFileRequest() {
		super();
	}
}
