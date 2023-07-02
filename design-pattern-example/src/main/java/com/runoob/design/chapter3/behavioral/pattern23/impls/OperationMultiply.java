package com.runoob.design.chapter3.behavioral.pattern23.impls;

import com.runoob.design.chapter3.behavioral.pattern23.Strategy;

/**
 * 乘法操作
 */
public class OperationMultiply implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1 * num2;
	}

}
