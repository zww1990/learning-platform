package cn.net.yzl.oa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class SqlExecQueryDTO {
	private String command;
	private Integer sourceId;
}
