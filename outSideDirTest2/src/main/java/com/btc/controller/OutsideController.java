package com.btc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OutsideController {

	@RequestMapping(value="/imgLoad", method=RequestMethod.GET)
	public String imgLoad() throws Exception {
		return "/imgLoad";
	}
}
