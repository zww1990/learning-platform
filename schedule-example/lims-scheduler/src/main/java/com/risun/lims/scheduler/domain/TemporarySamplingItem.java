package com.risun.lims.scheduler.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 数据库表映射实体类
 * @author 张维维
 * @since 2024-11-26 15:17:01
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class TemporarySamplingItem {
    private String tempCode;
    private String tempName;
    private String material;
    private String fullAnalysis;
    private String gvalue;
    private String yvalue;
    private String lithofacies;
    private String ironBoxCoal;
    private String ironBoxCoke;
    private String ddpm;
    private String aos;
    private String arnu;
}
