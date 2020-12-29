package com.example.springschedule;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.springschedule.domain.QuartzModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class QuartzModelTests {
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * <pre>
	 * {
	 *   "jobName" : "myjob",
	 *   "groupName" : "mygroup",
	 *   "jobClass" : "myclass",
	 *   "cronExpression" : "0/5 * * * * ?",
	 *   "param" : {
	 *     "key" : "value"
	 *   }
	 * }
	 * </pre>
	 */
	@Test
	public void printJson() {
		try {
			QuartzModel model = new QuartzModel();
			model.setCronExpression("0/5 * * * * ?");
			model.setGroupName("mygroup");
			model.setJobClass("myclass");
			model.setJobName("myjob");
			Map<String, Object> map = new HashMap<>();
			map.put("key", "value");
			model.setParam(map);
			this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
			System.err.println(this.mapper.writeValueAsString(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
