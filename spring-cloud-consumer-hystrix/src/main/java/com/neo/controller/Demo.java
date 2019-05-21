package com.neo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Lowi on 2018/11/17 0017.
 */
@Api(tags = {"demo接口"})
@RestController
@RequestMapping(value = "/demo")
public class Demo {

	@ApiOperation(value = "待确认雇佣消息")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(@ApiIgnore HttpServletRequest request) {

		return "dasdasdadsadasdadasd";
	}
}
