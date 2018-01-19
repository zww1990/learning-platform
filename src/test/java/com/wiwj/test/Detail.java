package com.wiwj.test;

public class Detail {
	private String department;// 部门
	private String approvalNumber;// 审批单号
	private String description;// 说明
	private String unitCode = "5001";// 核算单位代码
	private String typeCode = TypeCode.T01106.name;// 报销类型代码
	private String projectCode = ProjectCode.I00002.name;// 费用项目代码
	private String quantity = "1";// 数量
	private int amount = 50;// 金额
	private String taxCode = "";// 税率代码
	private String tax = "";// 税额
	private String businessPeriod = "2017-09";// 业务期间
	private String departmentCode = "9001000000089";// 部门代码
	private String resellerCode = "647158";// 报销人代码
	private String contractNo = "";// 合同编号
	private String planLine = "";// 合同资金计划行
	private String project = "0";// 项目
	private String businessType = "990000";// 业务类型
	private String overtime;// 加班日期，格式：年月日
	private String deadline;// 加班截止时间，格式：时分秒
	private String startPoint = "";// 上车地点
	private String startTime = "";// 上车时间
	private String endPoint = "";// 下车地点
	private String endTime = "";// 下车时间
	private String startMileage = "";// 起始里程
	private String endMileage = "";// 结束里程
	private String totalMileage = "";// 总里程
	private String spent = "";// 每公里油耗
	public static final String END_POINT = "家（西城区阜成门内）";// 下车地点
	public static final String START_POINT = "公司（来广营朝来科技园）";// 上车地点

	public enum ProjectCode {
		I00002("I00002"), I00084("I00084");
		private String name;

		private ProjectCode(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum TypeCode {
		T01106("T01106"), T01107("T01107");
		private String name;

		private TypeCode(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum Description {
		MealFee("-餐费"), TrafficFee("-交通");
		private String name;

		private Description(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public Detail() {
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getBusinessPeriod() {
		return businessPeriod;
	}

	public void setBusinessPeriod(String businessPeriod) {
		this.businessPeriod = businessPeriod;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getResellerCode() {
		return resellerCode;
	}

	public void setResellerCode(String resellerCode) {
		this.resellerCode = resellerCode;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getPlanLine() {
		return planLine;
	}

	public void setPlanLine(String planLine) {
		this.planLine = planLine;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartMileage() {
		return startMileage;
	}

	public void setStartMileage(String startMileage) {
		this.startMileage = startMileage;
	}

	public String getEndMileage() {
		return endMileage;
	}

	public void setEndMileage(String endMileage) {
		this.endMileage = endMileage;
	}

	public String getTotalMileage() {
		return totalMileage;
	}

	public void setTotalMileage(String totalMileage) {
		this.totalMileage = totalMileage;
	}

	public String getSpent() {
		return spent;
	}

	public void setSpent(String spent) {
		this.spent = spent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append("department=").append(department).append(", approvalNumber=").append(approvalNumber)
				.append(", description=").append(description).append(", unitCode=").append(unitCode)
				.append(", typeCode=").append(typeCode).append(", projectCode=").append(projectCode)
				.append(", quantity=").append(quantity).append(", amount=").append(amount).append(", taxCode=")
				.append(taxCode).append(", tax=").append(tax).append(", businessPeriod=").append(businessPeriod)
				.append(", departmentCode=").append(departmentCode).append(", resellerCode=").append(resellerCode)
				.append(", contractNo=").append(contractNo).append(", planLine=").append(planLine).append(", project=")
				.append(project).append(", businessType=").append(businessType).append(", overtime=").append(overtime)
				.append(", deadline=").append(deadline).append(", startPoint=").append(startPoint)
				.append(", startTime=").append(startTime).append(", endPoint=").append(endPoint).append(", endTime=")
				.append(endTime).append(", startMileage=").append(startMileage).append(", endMileage=")
				.append(endMileage).append(", totalMileage=").append(totalMileage).append(", spent=").append(spent)
				.append("]");
		return builder.toString();
	}

}
