package com.demo.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

@SuppressWarnings("unused")
public class CodeTest {

	/**
	 * 嵌套循环
	 */
	@Test
	public void test1() {
		int max = 10000000;
		int min = 10;
		long stratTime = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < min; j++) {
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("外大内小耗时：" + (endTime - stratTime));
		long stratTime1 = System.currentTimeMillis();
		for (int i = 0; i < min; i++) {
			for (int j = 0; j < max; j++) {
			}
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("外小内大耗时：" + (endTime1 - stratTime1));
	}

	/**
	 * 提取与循环无关的表达式
	 */
	@Test
	public void test2() {
		int len = 10000000;
		int a = 1, b = 1;
		long stratTime = System.currentTimeMillis();
		for (int i = 0; i < len; i++) {
			i = i * a * b;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("未提取耗时：" + (endTime - stratTime));
		stratTime = System.currentTimeMillis();
		int c = a * b;
		for (int i = 0; i < len; i++) {
			i = i * c;
		}
		endTime = System.currentTimeMillis();
		System.out.println("已提取耗时：" + (endTime - stratTime));
	}

	/**
	 * 消除循环终止判断时的方法调用
	 */
	@Test
	public void test3() {
		List<Object> list = new ArrayList<Object>();
		int len = 10000000;
		for (int i = 0; i < len; i++) {
			list.add(i);
		}
		long stratTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
		}
		long endTime = System.currentTimeMillis();
		System.out.println("未优化list耗时：" + (endTime - stratTime));
		long stratTime1 = System.currentTimeMillis();
		int size = list.size();
		for (int i = 0; i < size; i++) {
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("优化list耗时：" + (endTime1 - stratTime1));
	}

	/**
	 * 异常捕获
	 */
	@Test
	public void test4() {
		int len = 10000000;
		long stratTime = System.currentTimeMillis();
		for (int i = 0; i < len; i++) {
			try {
			} catch (Exception e) {
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("在内部捕获异常耗时：" + (endTime - stratTime));
		long stratTime1 = System.currentTimeMillis();
		try {
			for (int i = 0; i < len; i++) {
			}
		} catch (Exception e) {
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("在外部捕获异常耗时：" + (endTime1 - stratTime1));
	}

	/**
	 * 循环内不要不断创建对象引用
	 */
	@Test
	public void test5() {
		int len = 10000000;
		long stratTime = System.currentTimeMillis();
		for (int i = 0; i < len; i++) {
			Object obj = new Object();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("1在内部循环创建对象耗时：" + (endTime - stratTime));
		long stratTime1 = System.currentTimeMillis();
		Object obj2 = null;
		for (int i = 0; i < len; i++) {
			obj2 = new Object();
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("2在内部循环创建对象耗时：" + (endTime1 - stratTime1));
	}

	/**
	 * 把一个基本数据类型转为字符串
	 */
	@Test
	public void test6() {
		int loopTime = 50000;
		int i = 0;
		long startTime = System.currentTimeMillis();
		for (int j = 0; j < loopTime; j++) {
			String str = String.valueOf(i);
		}
		System.out.println("String.valueOf()：" + (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		for (int j = 0; j < loopTime; j++) {
			String str = Integer.toString(i);
		}
		System.out.println("Integer.toString()：" + (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		for (int j = 0; j < loopTime; j++) {
			String str = i + "";
		}
		System.out.println("i + \"\"：" + (System.currentTimeMillis() - startTime));
	}

	@Test
	public void test7() {
		int len = 50000;
		long stratTime = System.currentTimeMillis();
		String str = "";
		for (int i = 0; i < len; i++) {
			str += i;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("String耗时：" + (endTime - stratTime));
		long stratTime1 = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(i);
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("StringBuilder耗时：" + (endTime1 - stratTime1));
	}

	@Test
	public void test8() {
		int len = 50000;
		String str = "1";
		long stratTime = System.currentTimeMillis();
		for (int i = 0; i < len; i++) {
			Integer.parseInt(str);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Integer.parseInt()耗时：" + (endTime - stratTime));
		long stratTime1 = System.currentTimeMillis();
		for (int i = 0; i < len; i++) {
			Integer.valueOf(str);
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Integer.valueOf()耗时：" + (endTime1 - stratTime1));
	}
}
