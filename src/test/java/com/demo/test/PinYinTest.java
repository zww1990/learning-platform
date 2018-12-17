package com.demo.test;

import java.util.ArrayList;
import java.util.List;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class PinYinTest {
	static HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	static {
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	}

	public static void main(String[] args) {
		try {
			String text = "小宝贝我爱你";
			List<String> list = new ArrayList<>(text.length());
			char[] array = text.toCharArray();
			for (char ch : array) {
				String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(ch, format);
				list.add(pinyin.length == 0 ? String.valueOf(ch) : pinyin[0]);
			}
			System.err.println(list.stream().map(m -> m.toCharArray()[0])
					.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString()
					.toUpperCase());
			System.err.println(list.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
					.toString().toLowerCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
