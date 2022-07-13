package com.example.test.excel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoExcelComponent implements ExcelComponent {
	private ObjectMapper json = new ObjectMapper();

	@Override
	public void write(OutputStream os) {
		try (Workbook wb = new XSSFWorkbook()) {
			Set<Entry<String, List<MenuData>>> entries = this.findMenuMap().entrySet();
			for (Entry<String, List<MenuData>> entry : entries) {
				Sheet sheet = wb.createSheet(entry.getKey());
				Row firstRow = sheet.createRow(0);
				List<MenuData> menuList = entry.getValue();
				for (int i = 0; i < menuList.size(); i++) {
					MenuData menu = menuList.get(i);
					firstRow.createCell(i).setCellValue(menu.getMenuName());
					List<MenuData> subMenuList = menu.getChildrens();
					if (!CollectionUtils.isEmpty(subMenuList)) {
						for (int j = 0; j < subMenuList.size(); j++) {
							int rownum = j + 1;
							Row row = i == 0 ? sheet.createRow(rownum) : sheet.getRow(rownum);
							if (row == null) {
								row = sheet.createRow(rownum);
							}
							row.createCell(i).setCellValue(subMenuList.get(j).getMenuName());
						}
					}
				}
			}
			wb.write(os);
			log.info("成功写入工作簿中电子表格的数量: {}", wb.getNumberOfSheets());
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}

	private Map<String, List<MenuData>> findMenuMap() {
		try (InputStream is = new ClassPathResource("menu-list.json").getInputStream()) {
			List<MenuData> menuList = json.readValue(is,
					json.getTypeFactory().constructParametricType(List.class, MenuData.class));
			Map<String, List<MenuData>> menuMap = menuList.stream()
					.collect(Collectors.groupingBy(MenuData::getComponent));
			return menuMap;
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			return Collections.emptyMap();
		}
	}
}
