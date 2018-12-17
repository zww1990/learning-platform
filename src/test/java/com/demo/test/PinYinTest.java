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
			String text = "荆溪白石出，Hello 天寒红叶稀。Android 山路元无雨，What's up? 空翠湿人衣。666 666";
			List<String> list = new ArrayList<>(text.length());
			char[] array = text.toCharArray();
			for (char ch : array) {
				String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(ch, format);
				list.add(pinyin.length == 0 ? String.valueOf(ch) : pinyin[0]);
			}
			System.err.println(list.stream().map(m -> m.toCharArray()[0])
					.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());
			System.err.println(
					list.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
