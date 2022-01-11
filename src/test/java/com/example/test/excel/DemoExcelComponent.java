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

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoExcelComponent implements ExcelComponent {
	private ObjectMapper json = new ObjectMapper();

	@Override
	public void write(OutputStream os) {
		try (Workbook wb = new XSSFWorkbook()) {
			Sheet sheet = wb.createSheet("系统菜单");
			Set<Entry<String, List<MenuData>>> entries = this.findMenuMap().entrySet();
			int i = 0;
			Row firstRow = sheet.createRow(0);
			for (Entry<String, List<MenuData>> entry : entries) {
				firstRow.createCell(i).setCellValue(entry.getKey());
				List<MenuData> value = entry.getValue();
				for (int j = 0; j < value.size(); j++) {
					Row row = i == 0 ? sheet.createRow(j + 1) : sheet.getRow(j + 1);
					if (row == null) {
						row = sheet.createRow(j + 1);
					}
					row.createCell(i).setCellValue(value.get(j).getMenuName());
				}
				i++;
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
