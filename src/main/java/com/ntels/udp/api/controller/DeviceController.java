package com.ntels.udp.api.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceMethod;
import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

@Controller
public class DeviceController {
	private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);	
	
	
	/**
	 * 1. 디바이스 제어
	 * 
	 * @param param
	 * @return
	 * @throws IotHubException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.POST, value = "/udp/v1/device/control")
	@ResponseBody
	public void controlDevice(@RequestBody JSONObject param) throws IotHubException {
		logger.info("=========================================");
		logger.info("Start controlDevice");		
		logger.info("RequestBody : " + param);
		
		String device_id = "parking_simulator";
		String method_name = "SetTelemetryInterval";
		int payload = 10;
		String iotHubConnectionString = "HostName=UDPIoTHub01.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=cyGwNJ+tiAQ3gJEL+J/k6f538ex9W0Ojhu7VNTBFLdQ=";
		Long responseTimeout = TimeUnit.SECONDS.toSeconds(30);
		Long connectTimeout = TimeUnit.SECONDS.toSeconds(5);
		 
		try {
			
			DeviceMethod methodClient = DeviceMethod.createFromConnectionString(iotHubConnectionString);
			MethodResult result = methodClient.invoke(device_id, method_name, responseTimeout, connectTimeout, payload);
			
			if(result == null) {
				throw new IOException("Direct method invoke returns null");
			}
			
			logger.info("Status : " + result.getStatus());
			logger.info("Status : " + result.getPayload());			
			logger.info("=========================================");
			
		} catch (IOException e) {
			logger.info(e.getMessage());
			
		} catch (IotHubException e) {
			logger.info(e.getMessage());
		}		
	}

}
