package com.example.test.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * MenuExcelComponent
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午11:05:12
 */
@Slf4j
public class MenuExcelComponent implements ExcelComponent {
	private ObjectMapper json = new ObjectMapper();

	@Override
	public void write(OutputStream os) {
		try (Workbook wb = new XSSFWorkbook()) {
			XSSFSheet sheet = (XSSFSheet) wb.createSheet("省份-城市-区县");
			String[] headers = { "省份", "城市", "区县" };
			this.createHeaderRow(wb, sheet, headers);
			List<MenuData> provinceList = this.findMenuList();
			this.createProvinceSheet(wb, sheet, provinceList);
			this.createCityDistrictSheet(wb, sheet, provinceList);
			wb.write(os);
			log.info("成功写入工作簿中电子表格的数量: {}", wb.getNumberOfSheets());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 创建省份sheet页；<br>
	 * 每行的第一个单元格写入省份；
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:05:18
	 * @param wb
	 * @param sheet
	 */
	private void createProvinceSheet(Workbook wb, XSSFSheet sheet, List<MenuData> provinceList) {
		if (CollectionUtils.isEmpty(provinceList)) {
			return;
		}
		Sheet provinceSheet = wb.createSheet("全国省份");
		for (int i = 0; i < provinceList.size(); i++) {
			// 每行的第一个单元格写入省份
			provinceSheet.createRow(i).createCell(0).setCellValue(provinceList.get(i).getMenuName());
		}
		Name provinceName = wb.createName();
		provinceName.setNameName(provinceSheet.getSheetName());
		provinceName.setRefersToFormula(provinceSheet.getSheetName() + "!$A$1:$A$" + provinceList.size());
		// 在第一列添加数据验证
		this.addFormulaListValidationData(sheet, provinceName.getNameName(), 0);
		// 将该sheet页隐藏
		wb.setSheetHidden(wb.getSheetIndex(provinceSheet), true);
	}

	/**
	 * 为每个省份创建一个单独的sheet页；<br>
	 * 将省份做为sheet页的名称；<br>
	 * 每行的第一个单元格写入城市；<br>
	 * 每行从第二个单元格开始写入区县；
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:05:22
	 * @param wb
	 * @param sheet
	 */
	private void createCityDistrictSheet(Workbook wb, XSSFSheet sheet, List<MenuData> provinceList) {
		if (CollectionUtils.isEmpty(provinceList)) {
			return;
		}
		// 为每个省份创建一个单独的sheet页
		for (MenuData province : provinceList) {
			List<MenuData> cityList = province.getChildrens();
			if (CollectionUtils.isEmpty(cityList)) {
				// 如果该省份没有城市，不创建sheet页
				continue;
			}
			// 将省份做为sheet页的名称
			Sheet provinceSheet = wb.createSheet(province.getMenuName());
			for (int i = 0; i < cityList.size(); i++) {
				MenuData city = cityList.get(i);
				Row row = provinceSheet.createRow(i);
				// 每行的第一个单元格写入城市
				row.createCell(0).setCellValue(city.getMenuName());
				List<MenuData> districtList = city.getChildrens();
				int districtSize;
				if (CollectionUtils.isEmpty(districtList)) {
					// 如果没有区县，默认10空单元格
					districtSize = 10;
				} else {
					districtSize = districtList.size();
					for (int j = 0; j < districtSize; j++) {
						// 每行从第二个单元格开始写入区县
						row.createCell(j + 1).setCellValue(districtList.get(j).getMenuName());
					}
				}
				Name cityName = wb.createName();
				// 名称格式：省份_城市
				cityName.setNameName(String.join("_", province.getMenuName(), city.getMenuName()));
				cityName.setRefersToFormula(
						provinceSheet.getSheetName() + '!' + this.calcRange(1, i + 1, districtSize));
			}
			Name provinceName = wb.createName();
			// 将省份做为名称管理器
			provinceName.setNameName(provinceSheet.getSheetName());
			provinceName.setRefersToFormula(provinceSheet.getSheetName() + "!$A$1:$A$" + cityList.size());
			// 将该sheet页隐藏
			wb.setSheetHidden(wb.getSheetIndex(provinceSheet), true);
		}
		// 在第二列添加数据验证
		this.addFormulaListValidationData(sheet, "INDIRECT($A2)", 1);
		// 在第三列添加数据验证
		this.addFormulaListValidationData(sheet, "INDIRECT($A2&\"_\"&$B2)", 2);
	}

	/**
	 * 模拟数据
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午8:22:54
	 * @return
	 */
	private List<MenuData> findMenuList() {
		try (InputStream is = new ClassPathResource("menu-list.json").getInputStream()) {
			return json.readValue(is, json.getTypeFactory().constructParametricType(List.class, MenuData.class));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return Collections.emptyList();
		}
	}
}
