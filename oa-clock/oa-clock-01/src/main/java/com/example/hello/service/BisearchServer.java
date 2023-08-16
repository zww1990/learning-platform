package com.example.hello.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.PostExchange;

import com.example.hello.model.ResponseBody;

import cn.net.yzl.oa.entity.SqlExecQueryDTO;

/**
 * BisearchServer
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月2日,下午5:30:56
 */
public interface BisearchServer {
	@PostExchange("/bisql/exec")
	ResponseBody<?> bisqlExec(//
			@RequestBody SqlExecQueryDTO dto, //
			@RequestHeader Map<String, ?> header);
}
