package cn.net.yzl.oa.entity;

public class SqlExecQueryDTO {
	private String command;
	private Integer sourceId;

	public String getCommand() {
		return command;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public SqlExecQueryDTO setCommand(String command) {
		this.command = command;
		return this;
	}

	public SqlExecQueryDTO setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	@Override
	public String toString() {
		return String.format("SqlExecQueryDTO [command=%s, sourceId=%s]", command, sourceId);
	}
}
