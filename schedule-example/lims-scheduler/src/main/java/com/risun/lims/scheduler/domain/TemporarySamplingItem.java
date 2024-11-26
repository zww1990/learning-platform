package com.risun.lims.scheduler.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
