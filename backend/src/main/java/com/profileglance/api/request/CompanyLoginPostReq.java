package com.profileglance.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CompanyLoginPostReq")
public class CompanyLoginPostReq {
	@ApiModelProperty(name="기업회원 아이디", example="samsung01")
	String companyId;
	@ApiModelProperty(name="기업회원 Password", example="password")
	String companyPassword;
}
