package com.wiwj.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import com.wiwj.test.Detail.Description;
import com.wiwj.test.Detail.ProjectCode;
import com.wiwj.test.Detail.TypeCode;

public class ExcelTest {
	private static final String parent = "E:\\projects";
	private static final String child = "加班明细-技术中心201712.xlsx";
	private static final double myID = 8143969D;// 员工号
	private static final int idIndex = 1;// 员工号索引
	private static final int nameIndex = 2;// 员工姓名索引
	private static final int deptIndex = 3;// 部门索引
	private static final int approvalIndex = 6;// 审批单号索引
	private static final int overtimeIndex = 8;// 加班日期索引
	private static final int deadlineIndex = 10;// 加班截止时间索引
	private static final int empPageIndex = 0;// 员工加班记录明细页索引
	private static final int myPageIndex = 1;// 个人加班记录明细页索引
	private static final String TAXI_TIME = "21:00:00";// 规定打车开始时间
	private static final String datePattern = "yyyy/MM/dd";// 格式化日期样式
	private static final String timePattern = "HH:mm:ss";// 格式化时间样式

	@Test
	public void readExcel() {
		String myName = "";// 员工姓名
		String sheetName = "";// sheet页名称
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		SimpleDateFormat timeFormat = new SimpleDateFormat(timePattern);
		File file = new File(parent, child);
		List<Detail> details = new ArrayList<>();
		List<String> header = new ArrayList<>();
		try (Workbook wb = WorkbookFactory.create(file)) {
			long taxiTime = timeFormat.parse(TAXI_TIME).getTime();
			Sheet sheet = wb.getSheetAt(empPageIndex);// 员工加班记录明细页索引
			int rowNum = sheet.getLastRowNum();// 获取sheet页最后一行的索引位置，从0开始
			Row row = null;
			int cellNum = 0;
			Cell cell = null;
			Detail detail = null;
			for (int i = 1; i <= rowNum; i++) {// 设置i=1，跳过表头行
				row = sheet.getRow(i);
				cell = row.getCell(idIndex);// 员工号索引
				if (cell.getNumericCellValue() != myID) {// 员工号
					continue;// 如果不是指定的员工号就跳过
				}
				cellNum = row.getLastCellNum();// 获取每行的单元格总数
				detail = new Detail();
				for (int j = 0; j < cellNum; j++) {
					cell = row.getCell(j);
					if (j == nameIndex) {// 员工姓名索引
						myName = cell.getStringCellValue();
						detail.setDescription(myName + Description.MealFee.getName());
					} else if (j == deptIndex) {// 部门索引
						detail.setDepartment(cell.getStringCellValue());
					} else if (j == approvalIndex) {// 审批单号索引
						detail.setApprovalNumber(cell.getStringCellValue());
					} else if (j == overtimeIndex) {// 加班日期索引
						detail.setOvertime(dateFormat.format(cell.getDateCellValue()));
					} else if (j == deadlineIndex) {// 加班截止时间索引
						detail.setDeadline(timeFormat.format(cell.getDateCellValue()));
					}
				}
				details.add(detail);
				long deadlineTime = timeFormat.parse(detail.getDeadline()).getTime();
				if (deadlineTime >= taxiTime) {// 超过21点，添加打车记录
					Detail copy = new Detail();
					copy.setDescription(myName + Description.TrafficFee.getName());
					copy.setDepartment(detail.getDepartment());
					copy.setApprovalNumber(detail.getApprovalNumber());
					copy.setOvertime(detail.getOvertime());
					copy.setDeadline(detail.getDeadline());
					copy.setTypeCode(TypeCode.T01107.getName());
					copy.setProjectCode(ProjectCode.I00084.getName());
					copy.setStartPoint(Detail.START_POINT);
					copy.setEndPoint(Detail.END_POINT);
					copy.setAmount(0);
					details.add(copy);
				}
			}
			sheet = wb.getSheetAt(myPageIndex);// 个人加班记录明细页索引
			sheetName = sheet.getSheetName();
			row = sheet.getRow(0);// 表头行
			row.forEach(c -> header.add(c.getStringCellValue()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println(myName + "的加班记录总数=" + details.size());
		this.writeExcel(myName, sheetName, details, header);// 写入文件
	}

	public void writeExcel(String myName, String sheetName, List<Detail> details, List<String> header) {
		File file = new File(parent, myName + "-" + child);
		try (SXSSFWorkbook wb = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE); // 在内存中保留100行，超出行将被刷新到磁盘
				OutputStream os = new FileOutputStream(file)) {
			wb.setCompressTempFiles(true);// 临时文件将被gzip压缩
			SXSSFSheet sheet = wb.createSheet(sheetName);// 创建sheet页
			SXSSFRow row = sheet.createRow(0);// 创建表头行
			for (int i = 0, len = header.size(); i < len; i++) {
				row.createCell(i).setCellValue(header.get(i));
			}
			Detail detail = null;
			for (int i = 0, len = details.size(); i < len; i++) {
				row = sheet.createRow(i + 1);// 数据行＋1，第一行已被表头占用
				detail = details.get(i);
				row.createCell(0).setCellValue(detail.getDepartment());// 部门
				row.createCell(1).setCellValue(detail.getApprovalNumber());// 流程审批单号
				row.createCell(2).setCellValue(detail.getDescription());// 说明
				row.createCell(3).setCellValue(detail.getUnitCode());// 核算单位代码
				row.createCell(4).setCellValue(detail.getTypeCode());// 报销类型代码
				row.createCell(5).setCellValue(detail.getProjectCode());// 费用项目代码
				row.createCell(6).setCellValue(detail.getQuantity());// 数量
				row.createCell(7).setCellValue(detail.getAmount());// 报销金额
				row.createCell(8).setCellValue(detail.getTaxCode());// 税率代码
				row.createCell(9).setCellValue(detail.getTax());// 税额
				row.createCell(10).setCellValue(detail.getBusinessPeriod());// 业务期间
				row.createCell(11).setCellValue(detail.getDepartmentCode());// 部门代码
				row.createCell(12).setCellValue(detail.getResellerCode());// 报销人代码
				row.createCell(13).setCellValue(detail.getContractNo());// 合同编号
				row.createCell(14).setCellValue(detail.getPlanLine());// 合同资金计划行
				row.createCell(15).setCellValue(detail.getProject());// 项目
				row.createCell(16).setCellValue(detail.getBusinessType());// 业务类型
				row.createCell(17).setCellValue(detail.getOvertime());// 加班日期
				row.createCell(18).setCellValue(detail.getDeadline());// 加班截止时间
				row.createCell(19).setCellValue(detail.getStartPoint());// 上车地点
				row.createCell(20).setCellValue(detail.getStartTime());// 上车时间
				row.createCell(21).setCellValue(detail.getEndPoint());// 下车地点
			}
			wb.write(os);// 写入文件
			wb.dispose();// 处理在磁盘上备份此工作簿的临时文件
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("文件位置>>" + file);
	}
}
