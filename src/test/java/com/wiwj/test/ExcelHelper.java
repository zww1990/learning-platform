package com.wiwj.test;

/**
 * @author ZhangWeiWei
 * @date 2018年7月23日,下午6:37:00
 * @description Excel辅助类
 */
public class ExcelHelper {
	/**
	 * @author ZhangWeiWei
	 * @date 2018年7月23日,下午6:38:59
	 * @param offset 偏移量，如果给0，表示从A列开始，1，就是从B列
	 * @param rowId 第几行
	 * @param colCount 一共多少列
	 * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
	 */
	public static String getRange(int offset, int rowId, int colCount) {
		char start = (char) ('A' + offset);
		if (colCount <= 25) {
			char end = (char) (start + colCount - 1);
			return new StringBuilder().append('$').append(start).append('$').append(rowId).append(":$").append(end)
					.append('$').append(rowId).toString();
		} else {
			char endPrefix = 'A';
			char endSuffix = 'A';
			if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
				if ((colCount - 25) % 26 == 0) {// 边界值
					endSuffix = (char) ('A' + 25);
				} else {
					endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
				}
			} else {// 51以上
				if ((colCount - 25) % 26 == 0) {
					endSuffix = (char) ('A' + 25);
					endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
				} else {
					endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
					endPrefix = (char) (endPrefix + (colCount - 25) / 26);
				}
			}
			return new StringBuilder().append('$').append(start).append('$').append(rowId).append(":$")
					.append(endPrefix).append(endSuffix).append('$').append(rowId).toString();
		}
	}
}
