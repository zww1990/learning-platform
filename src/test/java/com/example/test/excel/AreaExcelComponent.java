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
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * AreaExcelComponent
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午11:05:12
 */
@Slf4j
public class AreaExcelComponent implements ExcelComponent {

	@Override
	public void write(OutputStream os) {
		try (Workbook wb = new XSSFWorkbook()) {
			XSSFSheet sheet = (XSSFSheet) wb.createSheet("省份-城市-区县");
			String[] headers = { "省份", "城市", "区县" };
			this.createHeaderRow(wb, sheet, headers);
			this.createProvinceSheet(wb, sheet);
			this.createCityDistrictSheet(wb, sheet);
			wb.write(os);
			log.info("成功写入工作簿中电子表格的数量: {}", wb.getNumberOfSheets());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * createProvinceSheet
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:05:18
	 * @param wb
	 * @param sheet
	 */
	private void createProvinceSheet(Workbook wb, XSSFSheet sheet) {
		List<ExcelData> provinceList = this.findProvinceCityDistrictList();
		if (CollectionUtils.isEmpty(provinceList)) {
			return;
		}
		Sheet provinceSheet = wb.createSheet("全国省份");
		for (int i = 0; i < provinceList.size(); i++) {
			provinceSheet.createRow(i).createCell(0).setCellValue(provinceList.get(i).getCellValue());
		}
		Name provinceName = wb.createName();
		provinceName.setNameName(provinceSheet.getSheetName());
		provinceName.setRefersToFormula(provinceSheet.getSheetName() + "!$A$1:$A$" + provinceList.size());
		this.addFormulaListValidationData(sheet, provinceName.getNameName(), 0);
		// 设置隐藏的sheet页
		wb.setSheetHidden(wb.getSheetIndex(provinceSheet), true);
	}

	/**
	 * createCityDistrictSheet
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:05:22
	 * @param wb
	 * @param sheet
	 */
	private void createCityDistrictSheet(Workbook wb, XSSFSheet sheet) {
		List<ExcelData> provinceList = this.findProvinceCityDistrictList();
		if (CollectionUtils.isEmpty(provinceList)) {
			return;
		}
		// 为每个省份创建一个单独的sheet页
		for (ExcelData province : provinceList) {
			List<ExcelData> cityList = province.getChildrens();
			if (CollectionUtils.isEmpty(cityList)) {
				continue;
			}
			Sheet provinceSheet = wb.createSheet(province.getCellValue());
			for (int i = 0; i < cityList.size(); i++) {
				ExcelData city = cityList.get(i);
				Row row = provinceSheet.createRow(i);
				row.createCell(0).setCellValue(city.getCellValue());
				List<ExcelData> districtList = city.getChildrens();
				int districtSize;
				if (CollectionUtils.isEmpty(districtList)) {
					districtSize = 10;
				} else {
					districtSize = districtList.size();
					for (int j = 0; j < districtSize; j++) {
						row.createCell(j + 1).setCellValue(districtList.get(j).getCellValue());
					}
				}
				Name cityName = wb.createName();
				cityName.setNameName(String.join("_", province.getCellValue(), city.getCellValue()));
				cityName.setRefersToFormula(
						provinceSheet.getSheetName() + '!' + this.calcRange(1, i + 1, districtSize));
			}
			Name provinceName = wb.createName();
			provinceName.setNameName(provinceSheet.getSheetName());
			provinceName.setRefersToFormula(provinceSheet.getSheetName() + "!$A$1:$A$" + cityList.size());
			// 设置隐藏的sheet页
			wb.setSheetHidden(wb.getSheetIndex(provinceSheet), true);
		}
		this.addFormulaListValidationData(sheet, "INDIRECT($A2)", 1);
		this.addFormulaListValidationData(sheet, "INDIRECT($A2&\"_\"&$B2)", 2);
	}

	/**
	 * 模拟数据
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午8:22:54
	 * @return
	 */
	private List<ExcelData> findProvinceCityDistrictList() {
		return Arrays.asList(//
				new ExcelData().setCellValue("河北省").setChildrens(//
						Arrays.asList(//
								new ExcelData().setCellValue("石家庄市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("长安区"), //
												new ExcelData().setCellValue("桥西区"), //
												new ExcelData().setCellValue("新华区"), //
												new ExcelData().setCellValue("井陉矿区"))),
								new ExcelData().setCellValue("唐山市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("路南区"), //
												new ExcelData().setCellValue("路北区"), //
												new ExcelData().setCellValue("古冶区"), //
												new ExcelData().setCellValue("开平区"))),
								new ExcelData().setCellValue("秦皇岛市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("海港区"), //
												new ExcelData().setCellValue("山海关区"), //
												new ExcelData().setCellValue("北戴河区"), //
												new ExcelData().setCellValue("抚宁区"))))),
				new ExcelData().setCellValue("山西省").setChildrens(//
						Arrays.asList(//
								new ExcelData().setCellValue("太原市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("小店区"), //
												new ExcelData().setCellValue("迎泽区"), //
												new ExcelData().setCellValue("杏花岭区"), //
												new ExcelData().setCellValue("尖草坪区"))),
								new ExcelData().setCellValue("大同市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("新荣区"), //
												new ExcelData().setCellValue("平城区"), //
												new ExcelData().setCellValue("云冈区"), //
												new ExcelData().setCellValue("云州区"))),
								new ExcelData().setCellValue("阳泉市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("城区"), //
												new ExcelData().setCellValue("矿区"), //
												new ExcelData().setCellValue("郊区"), //
												new ExcelData().setCellValue("平定县"))))),
				new ExcelData().setCellValue("安徽省").setChildrens(//
						Arrays.asList(//
								new ExcelData().setCellValue("合肥市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("瑶海区"), //
												new ExcelData().setCellValue("庐阳区"), //
												new ExcelData().setCellValue("蜀山区"), //
												new ExcelData().setCellValue("包河区"))),
								new ExcelData().setCellValue("芜湖市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("镜湖区"), //
												new ExcelData().setCellValue("鸠江区"), //
												new ExcelData().setCellValue("弋江区"), //
												new ExcelData().setCellValue("湾沚区"))),
								new ExcelData().setCellValue("蚌埠市").setChildrens(//
										Arrays.asList(//
												new ExcelData().setCellValue("龙子湖区"), //
												new ExcelData().setCellValue("蚌山区"), //
												new ExcelData().setCellValue("禹会区"), //
												new ExcelData().setCellValue("淮上区"))))));
	}
}
