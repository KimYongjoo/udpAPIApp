package com.ntels.udp.api.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.udp.api.util.RestTempletUtil;


@Controller
public class ThingPlugController {
	private static final Logger logger = LoggerFactory.getLogger(ThingPlugController.class);
	
	@Autowired
	RestTempletUtil restUtil;
	
	/**
	 * 1. 
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(method = RequestMethod.POST, value = "/rule")
    @ResponseBody
    public JSONObject toThingPlug(@RequestBody JSONObject param) throws Exception {    	
		logger.info("=========================================");
		logger.info("Start control device through ThingPlug");		
		logger.info("RequestBody : " + param);
		logger.info("=========================================");

        String path = "http://localhost:8080//udp/v1/parking";
        JSONObject resultObj = new JSONObject();
        
        resultObj = restUtil.restRequestService(HttpMethod.GET, path, resultObj, JSONArray.class);

        return resultObj;
    }

}
