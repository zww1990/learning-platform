package com.runoob.design.chapter3.behavioral.pattern17;

/**
 * 迭代器模式（Iterator Pattern）
 */
public class IteratorPatternDemo {
	/**
	 * 使用 NameRepository 来获取迭代器，并打印名字。
	 */
	public static void main(String[] args) {
		NameRepository namesRepository = new NameRepository();

		for (Iterator iter = namesRepository.getIterator(); iter.hasNext();) {
			String name = (String) iter.next();
			System.out.println("Name : " + name);
		}
	}
}
