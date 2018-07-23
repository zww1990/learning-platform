package com.wiwj.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class PoiTest {
	@Test
	public void testCreate() {
		File file = new File("E:\\projects\\test.xlsx");
		try (Workbook wb = new XSSFWorkbook(); OutputStream stream = new FileOutputStream(file)) {
			Sheet sheet = wb.createSheet("数据验证");
			Name name = this.createSheet1(wb);
			DataValidationHelper helper = new XSSFDataValidationHelper((XSSFSheet) sheet);
			DataValidationConstraint constraint = helper.createFormulaListConstraint(name.getNameName());
			CellRangeAddressList addressList = new CellRangeAddressList(1, // 起始行号
					65535, // 终止行号
					1, // 起始列号
					1// 终止列号
			);
			DataValidation validation = helper.createValidation(constraint, addressList);
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
			sheet.addValidationData(validation);
			wb.write(stream);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Name createSheet1(Workbook wb) {
		List<String> list = Arrays.asList("一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十",
				"一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十",
				"一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十",
				"一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十");
		Sheet sheet = wb.createSheet("数据源");
		for (int i = 0, size = list.size(); i < size; i++) {
			sheet.createRow(i).createCell(0).setCellValue(list.get(i));
		}
		Name name = wb.createName();
		name.setRefersToFormula("数据源!$A$1:$A$65535");
		name.setNameName("数据源");
		wb.setSheetHidden(wb.getSheetIndex(sheet), true);// 设置隐藏的sheet页
		return name;
	}

	@Test
	public void testCascade2007() {
		// 查询所有的省名称
		List<String> provNameList = Arrays.asList("安徽省", "浙江省");
		// 整理数据，放入一个Map中，mapkey存放父地点，value 存放该地点下的子区域
		Map<String, List<String>> siteMap = new HashMap<String, List<String>>();
		siteMap.put("浙江省", Arrays.asList("杭州市", "宁波市"));
		siteMap.put("安徽省", Arrays.asList("芜湖市", "滁州市"));
		siteMap.put("芜湖市", Arrays.asList("戈江区", "三山区"));
		siteMap.put("滁州市", Arrays.asList("来安县", "凤阳县"));
		try (FileOutputStream os = new FileOutputStream("E:/projects/testCascade2007.xlsx");
				Workbook book = new XSSFWorkbook()) {
			// 创建一个excel
			// 创建需要用户填写的数据页
			// 设计表头
			Sheet sheet1 = book.createSheet("sheet1");
			Row row0 = sheet1.createRow(0);
			row0.createCell(0).setCellValue("省");
			row0.createCell(1).setCellValue("市");
			row0.createCell(2).setCellValue("区");
			// 创建一个专门用来存放地区信息的隐藏sheet页
			// 因此也不能在现实页之前创建，否则无法隐藏。
			Sheet hideSheet = book.createSheet("site");
			book.setSheetHidden(book.getSheetIndex(hideSheet), true);
			int rowId = 0;
			// 设置第一行，存省的信息
			Row proviRow = hideSheet.createRow(rowId++);
			proviRow.createCell(0).setCellValue("省列表");
			for (int i = 0; i < provNameList.size(); i++) {
				Cell proviCell = proviRow.createCell(i + 1);
				proviCell.setCellValue(provNameList.get(i));
			}
			// 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
			for (Entry<String, List<String>> en : siteMap.entrySet()) {
				String key = en.getKey();
				List<String> son = en.getValue();
				Row row = hideSheet.createRow(rowId++);
				row.createCell(0).setCellValue(key);
				for (int i = 0; i < son.size(); i++) {
					Cell cell = row.createCell(i + 1);
					cell.setCellValue(son.get(i));
				}
				// 添加名称管理器
				String range = getRange(1, rowId, son.size());
				Name name = book.createName();
				name.setNameName(key);
				String formula = "site!" + range;
				name.setRefersToFormula(formula);
			}
			XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet1);
			// 省规则
			DataValidationConstraint provConstraint = dvHelper
					.createExplicitListConstraint(provNameList.toArray(new String[] {}));
			CellRangeAddressList provRangeAddressList = new CellRangeAddressList(1, 65535, 0, 0);
			DataValidation provinceDataValidation = dvHelper.createValidation(provConstraint, provRangeAddressList);
			provinceDataValidation.setShowErrorBox(true);
			provinceDataValidation.setSuppressDropDownArrow(true);
			sheet1.addValidationData(provinceDataValidation);
			// 市以规则，此处仅作一个示例
			// "INDIRECT($A2)" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，如果A2是浙江省，那么此处就是
			// 浙江省下的区域信息。
			DataValidationConstraint formula = dvHelper.createFormulaListConstraint("INDIRECT($A2)");
			CellRangeAddressList rangeAddressList = new CellRangeAddressList(1, 65535, 1, 1);
			DataValidation cacse = dvHelper.createValidation(formula, rangeAddressList);
			cacse.setSuppressDropDownArrow(true);
			cacse.setShowErrorBox(true);
			sheet1.addValidationData(cacse);
			// 区规则
			formula = dvHelper.createFormulaListConstraint("INDIRECT($B2)");
			rangeAddressList = new CellRangeAddressList(1, 65535, 2, 2);
			cacse = dvHelper.createValidation(formula, rangeAddressList);
			cacse.setSuppressDropDownArrow(true);
			cacse.setShowErrorBox(true);
			sheet1.addValidationData(cacse);
			book.write(os);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param offset 偏移量，如果给0，表示从A列开始，1，就是从B列
	 * @param rowId 第几行
	 * @param colCount 一共多少列
	 * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
	 */
	private String getRange(int offset, int rowId, int colCount) {
		char start = (char) ('A' + offset);
		if (colCount <= 25) {
			char end = (char) (start + colCount - 1);
			return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
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
			return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
		}
	}
}
