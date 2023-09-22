package io.example.c3p0.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.util.StringUtils;

public class PropertyMapperTests {
	@Test
	public void testFirst() {
		Request request = new Request();
		request.setId1(System.currentTimeMillis());
		request.setName1("张三");
		request.setGender1(null);
		request.setBirthday1(null);

		Response response = new Response();
		if (request.getId1() != null) {
			response.setId2(request.getId1());
		}
		if (StringUtils.hasText(request.getName1())) {
			response.setName2(request.getName1());
		}
		if (request.getGender1() != null) {
			response.setGender2(request.getGender1());
		}
		if (request.getBirthday1() != null) {
			response.setBirthday2(request.getBirthday1());
		}

		System.err.println(request);
		System.err.println(response);
	}

	@Test
	public void testSecond() {
		Request request = new Request();
		request.setId1(System.currentTimeMillis());
		request.setName1("张三");
		request.setGender1(null);
		request.setBirthday1(null);

		Response response = new Response();
		PropertyMapper mapper = PropertyMapper.get();
		mapper.from(request::getId1).whenNonNull().to(response::setId2);
		mapper.from(request::getName1).whenHasText().to(response::setName2);
		mapper.from(request::getGender1).whenNonNull().to(response::setGender2);
		mapper.from(request::getBirthday1).whenNonNull().to(response::setBirthday2);

		System.err.println(request);
		System.err.println(response);
	}
}
