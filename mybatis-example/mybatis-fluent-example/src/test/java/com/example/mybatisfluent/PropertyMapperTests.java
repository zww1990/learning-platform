package com.example.mybatisfluent;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.PropertyMapper;

import com.example.mybatisfluent.entity.HelloWorld;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PropertyMapperTests {
	private Long id = System.currentTimeMillis();
	private String sayHello = "你好世界！";
	private String yourName = "程序员";
	private LocalDateTime gmtCreated = LocalDateTime.now();
	private Integer isDeleted = Integer.MAX_VALUE;

	@Test
	public void testMapper() {
		try {
			PropertyMapper mapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
			HelloWorld entity = new HelloWorld();
			System.err.println(this);
			mapper.from(this::getGmtCreated).to(entity::setGmtCreated);
			mapper.from(this::getGmtCreated).to(entity::setGmtModified);
			mapper.from(this::getId).to(entity::setId);
			mapper.from(this::getIsDeleted).to(entity::setIsDeleted);
			mapper.from(this::getSayHello).to(entity::setSayHello);
			mapper.from(this::getYourName).to(entity::setYourName);
			System.err.println(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
