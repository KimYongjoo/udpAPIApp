package com.ntels.udp.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CarController {
	private static final Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = "/in")
	@ResponseBody
	public Map<String, String> test1() throws Exception {
		logger.debug("test1 start ");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("car", "KIA");
		map.put("carName", "K5");
		map.put("time", "2018-01-01");
		
		return map;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/out")
	@ResponseBody
	public Map<String, String> test2() throws Exception {
		logger.debug("test2 start ");
		Map<String, String> map = new HashMap<String, String>();
		map.put("car", "HYUN");
		map.put("carName", "SONATA");
		map.put("time", "2018-02-02");
		
		return map;
		
	}
		
		
}
