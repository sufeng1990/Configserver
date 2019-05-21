package com.neo.entity;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@ApiModel(value = "单据")
public class CodeInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "单据ID")
	private Integer id;
	@ApiModelProperty(value = "备注")
	private Integer flag;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "时间")
	private String time;
	@ApiModelProperty(value = "编码")
	private String code;
	@ApiModelProperty(value = "单据ID")
	private String descr;
	@ApiModelProperty(value = "名称1")
	private String descrE;
	@ApiModelProperty(value = "名称2")
	private String createdBy;
	@ApiModelProperty(value = "名称3")
	private String createdAt;
	@ApiModelProperty(value = "名称4")
	private Long date;
	@ApiModelProperty(value = "名称5")
	private String tagCode;
	@ApiModelProperty(value = "单据ID")
	private String tagName;

}