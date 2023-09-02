package com.example.activeprovider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@SpringBootTest
public class JmsTemplateTests {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testSendMessage() {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("alarmLogId", "f4620473-c892-43da-b276-38a10bc774ea");
			map.put("picPath", "http://192.168.10.245/fire/dutyqueue/hzmt003/92422680w.jpg");
			map.put("departmentName", "法务风控部");
			map.put("videoPath",
					"http://192.168.10.245/fire/crossing_boundary/test009/f4620473-c892-43da-b276-38a10bc774ea.mp4");
			map.put("alarmType", "11");
			map.put("cameraNo", "XXXXXX");
			map.put("alarmTypeCN", "人员入侵");
			map.put("workshopName", "化产二车间");
			map.put("alarmTime", "2021-08-13 11:13:37");
			map.put("rtsp", "rtsp://admin:admin123@192.168.20.200:554/cam/realmonitor?channel=1&subtype=0");
			map.put("alarmMessage", "您好：化产二车间化工蒸馏装置监控点位在 2021-08-13 11:13:47 发生人员入侵，请相关人员加强巡检，安排相关人员立即处理！");
			map.put("cameraName", "化工蒸馏装置");
			map.put("workshopId", "1111");
			map.put("departmentId", "2222");
			this.jmsTemplate.convertAndSend(new ActiveMQQueue("fire_alarm_queue1"), map);
			System.err.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSendMessage2() {
		try {
			Message obj = new Message()//
					.setAlarmLogId("1")//
					.setPicPath("2")//
					.setDepartmentName("3")//
					.setVideoPath("4")//
					.setAlarmType("5")//
					.setCameraNo("6")//
					.setAlarmTypeCN("7")//
					.setWorkshopName("8")//
					.setAlarmTime("9")//
					.setRtsp("10")//
					.setAlarmMessage("11")//
					.setCameraName("12")//
					.setWorkshopId("13")//
					.setDepartmentId("14");
			this.jmsTemplate.convertAndSend(new ActiveMQQueue("fire_alarm_queue1"), obj);
			System.err.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSendMessage3() {
		try {
			Message obj = new Message()//
					.setAlarmLogId("1")//
					.setPicPath("2")//
					.setDepartmentName("3")//
					.setVideoPath("4")//
					.setAlarmType("5")//
					.setCameraNo("6")//
					.setAlarmTypeCN("7")//
					.setWorkshopName("8")//
					.setAlarmTime("9")//
					.setRtsp("10")//
					.setAlarmMessage("11")//
					.setCameraName("12")//
					.setWorkshopId("13")//
					.setDepartmentId("14");
			String json = this.objectMapper.writeValueAsString(obj);
			this.jmsTemplate.convertAndSend(new ActiveMQQueue("fire_alarm_queue1"), json);
			System.err.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("serial")
	@Getter
	@Setter
	@ToString
	@Accessors(chain = true)
	static class Message implements Serializable {
		private String alarmLogId;
		private String picPath;
		private String departmentName;
		private String videoPath;
		private String alarmType;
		private String cameraNo;
		private String alarmTypeCN;
		private String workshopName;
		private String alarmTime;
		private String rtsp;
		private String alarmMessage;
		private String cameraName;
		private String workshopId;
		private String departmentId;
	}
}
