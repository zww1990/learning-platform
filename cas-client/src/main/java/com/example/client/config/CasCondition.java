package com.example.client.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CasCondition implements Condition {
	public static final String CAS_MODE = "prod";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return CAS_MODE.equalsIgnoreCase(context.getEnvironment().getProperty("cas.mode"));
	}

}
