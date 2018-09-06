package com.ntels.udp.api.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTempletUtil {
	private static final Logger logger = LoggerFactory.getLogger(RestTempletUtil.class);
	@SuppressWarnings("rawtypes")
	ResponseEntity responseEntity = null;
	RestTemplate restTemplate = new RestTemplate();
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused", "unlikely-arg-type" })
	public JSONObject restRequestService(HttpMethod method, String path, JSONObject param, Class data_type) {
		String url = "" + path;
		HttpEntity entity = new HttpEntity(param);		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        
		String request_type = null;
		if(data_type != null) {
            request_type = data_type.getSimpleName();
        }
		
        logger.debug("----------------------- REST Request -----------------------");
        logger.debug("URL : " + builder.toUriString());
        logger.debug("Method : " + method);
        logger.debug("Param : " + param);
        logger.debug("------------------------------------------------------------");
		
        if(HttpMethod.GET == method && request_type.equals("JSONObject")) {
        	responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class);
        }
        
        if(HttpMethod.GET == method && request_type.equals("JSONArray")) {
        	responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, JSONArray.class);
        }
        
        if(HttpMethod.POST == method) {
        	responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);
        }
        
        if(HttpMethod.PUT == method) {
        	responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, JSONObject.class);
        }
        
        if(HttpMethod.DELETE == method) {
        	responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, entity, JSONObject.class);
        }
        
        JSONObject obj = new JSONObject();
        obj.put("body", responseEntity.getBody());
        obj.put("status_code", responseEntity.getStatusCode());        
        printRestResult();
        
        return obj;		
	}
	
	//log만
	public void printRestResult() {
        logger.debug("----------------------- REST Response -----------------------");
        logger.debug("Status Code : " + responseEntity.getStatusCode());
        logger.debug("Response Body : " + responseEntity.getBody());
        logger.debug("-------------------------------------------------------------");
		
	}
		

}
