package io.example.statemachine;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ExpressionTests {
	@Test
	public void testSpelExpression() {
		try {
			ExpressionParser parser = new SpelExpressionParser();
			String expressionString = " T(java.lang.Math).max(3,4) ";
			Expression expression = parser.parseExpression(expressionString);
			System.err.println(expression.getExpressionString() + " = " + expression.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
