package io.example.demo.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

/**
 * MenuExcelComponent
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午11:05:12
 */
public class MenuExcelComponent implements ExcelComponent {

	@Override
	public void write(OutputStream os) {
		try (Workbook wb = new XSSFWorkbook()) {
			Sheet sheet = wb.createSheet("系统菜单");
			String[] headers = { "一级菜单", "二级菜单", "三级菜单" };
			this.createHeaderRow(wb, sheet, headers);
			List<MenuData> provinceList = this.findMenuList();
			if (!CollectionUtils.isEmpty(provinceList)) {
				Map<String, List<MenuData>> menuMap = provinceList.stream()
						.collect(Collectors.groupingBy(MenuData::getComponent));
				this.createMenuSheet(wb, sheet, menuMap);
				this.createSubMenuSheet(wb, sheet, menuMap);
			}
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
	 * @param wb      {@link Workbook}
	 * @param sheet   {@link Sheet}
	 * @param menuMap {@link MenuData}
	 */
	private void createMenuSheet(Workbook wb, Sheet sheet, Map<String, List<MenuData>> menuMap) {
		Sheet menuSheet = wb.createSheet("所有系统");
		List<String> menuList = menuMap.keySet().stream().collect(Collectors.toList());
		for (int i = 0; i < menuList.size(); i++) {
			String value = menuList.get(i);
			// 每行的第一个单元格写入省份
			menuSheet.createRow(i).createCell(0).setCellValue(value);
			this.setCellWidth(menuSheet, i, value);
		}
		Name menuName = wb.createName();
		menuName.setNameName(menuSheet.getSheetName());
		menuName.setRefersToFormula(menuSheet.getSheetName() + "!$A$1:$A$" + menuList.size());
		// 在第一列添加数据验证
		this.addFormulaListValidationData(sheet, menuName.getNameName(), 0);
		// 将该sheet页隐藏
		wb.setSheetHidden(wb.getSheetIndex(menuSheet), true);
	}

	/**
	 * 为每个省份创建一个单独的sheet页；<br>
	 * 将省份做为sheet页的名称；<br>
	 * 每行的第一个单元格写入城市；<br>
	 * 每行从第二个单元格开始写入区县；
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:05:22
	 * @param wb      {@link Workbook}
	 * @param sheet   {@link Sheet}
	 * @param menuMap {@link MenuData}
	 */
	private void createSubMenuSheet(Workbook wb, Sheet sheet, Map<String, List<MenuData>> menuMap) {
		Set<Entry<String, List<MenuData>>> menuEntrySet = menuMap.entrySet();
		// 为每个省份创建一个单独的sheet页
		for (Entry<String, List<MenuData>> entry : menuEntrySet) {
			List<MenuData> menuList = entry.getValue();
			if (CollectionUtils.isEmpty(menuList)) {
				// 如果该省份没有城市，不创建sheet页
				continue;
			}
			// 将省份做为sheet页的名称
			Sheet menuSheet = wb.createSheet(entry.getKey());
			for (int i = 0; i < menuList.size(); i++) {
				MenuData menu = menuList.get(i);
				Row row = menuSheet.createRow(i);
				// 每行的第一个单元格写入城市
				row.createCell(0).setCellValue(menu.getMenuName());
				this.setCellWidth(menuSheet, i, menu.getMenuName());
				List<MenuData> subMenuList = menu.getChildrens();
				if (CollectionUtils.isEmpty(subMenuList)) {
					// 如果没有区县，直接跳过
					continue;
				}
				for (int j = 0; j < subMenuList.size(); j++) {
					String value = subMenuList.get(j).getMenuName();
					// 每行从第二个单元格开始写入区县
					row.createCell(j + 1).setCellValue(value);
					this.setCellWidth(menuSheet, j + 1, value);
				}
				Name menuName = wb.createName();
				// 名称格式：省份_城市
				menuName.setNameName(String.join("_", entry.getKey(), menu.getMenuName()));
				menuName.setRefersToFormula(
						menuSheet.getSheetName() + '!' + this.calcRange(1, i + 1, subMenuList.size()));
			}
			Name menuName = wb.createName();
			// 将省份做为名称管理器
			menuName.setNameName(menuSheet.getSheetName());
			menuName.setRefersToFormula(menuSheet.getSheetName() + "!$A$1:$A$" + menuList.size());
			// 将该sheet页隐藏
			wb.setSheetHidden(wb.getSheetIndex(menuSheet), true);
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
	 * @return {@link MenuData}
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
