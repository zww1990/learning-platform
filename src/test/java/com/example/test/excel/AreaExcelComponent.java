package com.example.test.excel;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AreaExcelComponent implements ExcelComponent {

	@Override
	public void write(OutputStream os) {
		try (Workbook wb = new XSSFWorkbook()) {
			XSSFSheet sheet = (XSSFSheet) wb.createSheet("商圈信息");
			String[] headers = { "*商圈类型", "*所在城市", "*所属区域" };
			this.createHeaderRow(wb, sheet, headers);
			this.createCityAndDistrictSheet(wb, sheet);
			this.createAreaTypeSheet(wb, sheet);
			this.addDistrictValidationData(sheet);
			wb.write(os);
			log.info("{}写入Excel完成!", sheet.getSheetName());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 区域
	 * 
	 * @author ZhangWeiWei
	 * @date 2018年7月25日,上午11:27:16
	 * @param sheet
	 */
	private void addDistrictValidationData(XSSFSheet sheet) {
		this.addFormulaListValidationData(sheet, "INDIRECT($B2)", 2);
	}

	/**
	 * 商圈类型
	 * 
	 * @author ZhangWeiWei
	 * @date 2018年7月25日,下午1:23:39
	 * @param wb
	 * @param sheet
	 */
	private void createAreaTypeSheet(Workbook wb, XSSFSheet sheet) {
		Sheet sheet2 = wb.createSheet("商圈类型");
		List<ExcelData> values = this.selectAreaType();
		for (int i = 0; i < values.size(); i++) {
			sheet2.createRow(i).createCell(0).setCellValue(values.get(i).getCellValue());
		}
		Name name = wb.createName();
		name.setNameName(sheet2.getSheetName());
		name.setRefersToFormula(sheet2.getSheetName() + "!$A$1:$A$" + values.size());
		this.addFormulaListValidationData(sheet, name.getNameName(), 0);
		wb.setSheetHidden(wb.getSheetIndex(sheet2), true);// 设置隐藏的sheet页
	}

	private List<ExcelData> selectAreaType() {
		return Arrays.asList(//
				new ExcelData().setCellValue("河北省"), //
				new ExcelData().setCellValue("山西省"), //
				new ExcelData().setCellValue("安徽省"));
	}

	/**
	 * 城市区域规划
	 * 
	 * @author ZhangWeiWei
	 * @date 2018年7月25日,下午1:23:48
	 * @param wb
	 * @param sheet
	 */
	private void createCityAndDistrictSheet(Workbook wb, XSSFSheet sheet) {
		Sheet sheet2 = wb.createSheet("城市区域规划");
		List<ExcelData> cityList = this.selectCityAndDistrict();
		for (int i = 0; i < cityList.size(); i++) {
			Row row = sheet2.createRow(i);
			ExcelData city = cityList.get(i);
			row.createCell(0).setCellValue(city.getCellValue());
			List<ExcelData> distList = city.getChildrens();
			for (int j = 0; j < distList.size(); j++) {
				row.createCell(j + 1).setCellValue(distList.get(j).getCellValue());
			}
			Name name = wb.createName();
			name.setNameName(city.getCellValue());
			name.setRefersToFormula(sheet2.getSheetName() + '!' + this.calcRange(1, i + 1, distList.size()));
		}
		Name name = wb.createName();
		name.setNameName("城市");
		name.setRefersToFormula(sheet2.getSheetName() + "!$A$1:$A$" + cityList.size());
		this.addFormulaListValidationData(sheet, name.getNameName(), 1);
		wb.setSheetHidden(wb.getSheetIndex(sheet2), true);// 设置隐藏的sheet页
	}

	private List<ExcelData> selectCityAndDistrict() {
		return Arrays.asList(//
				new ExcelData().setCellValue("河北省").setChildrens(//
						Arrays.asList(//
								new ExcelData().setCellValue("石家庄市"), //
								new ExcelData().setCellValue("唐山市"), //
								new ExcelData().setCellValue("秦皇岛市"))),
				new ExcelData().setCellValue("山西省").setChildrens(//
						Arrays.asList(//
								new ExcelData().setCellValue("太原市"), //
								new ExcelData().setCellValue("大同市"), //
								new ExcelData().setCellValue("阳泉市"))),
				new ExcelData().setCellValue("安徽省").setChildrens(//
						Arrays.asList(//
								new ExcelData().setCellValue("合肥市"), //
								new ExcelData().setCellValue("芜湖市"), //
								new ExcelData().setCellValue("蚌埠市"))));
	}
}
