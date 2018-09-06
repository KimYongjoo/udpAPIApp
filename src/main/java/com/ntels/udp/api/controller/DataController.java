package com.ntels.udp.api.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntels.udp.api.util.SQLConnectionUtil;

@Controller
public class DataController {
	private static final Logger logger = LoggerFactory.getLogger(DataController.class);
	
	@Autowired
	SQLConnectionUtil sqlUtil;

	/**
	 * 1. UDP 데이터 조회
	 * 
	 * @param floor
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET, value = "/udp/v1/parking")
	@ResponseBody
	public JSONArray getDataFromUDP() throws Exception {
		logger.info("=========================================");
		logger.info("Start getDataFromUDP");		
		logger.info("RequestParam Floor : ");
		
		Connection connection = null;
		PreparedStatement pstat = null;
		JSONArray arr = new JSONArray();

		try {
			connection = sqlUtil.getConnection();
			logger.info("Successful connection ");
			logger.info("=========================================");
			
			String selectSql = "SELECT "
					+ "TOP 10 * "
					+ "FROM ParkingService ";

			pstat = connection.prepareStatement(selectSql);	
			//pstat.setString(1,  floor);
			
			ResultSet rs = pstat.executeQuery();			
			
			logger.info("SELECT SQL : " + selectSql);

			while (rs.next()) {
				JSONObject obj = new JSONObject();
				String parking_id = rs.getString("PARKING_ID");
				String parking_floor = rs.getString("PARKING_FLOOR");
				String parking_area = rs.getString("PARKING_AREA");
				String parking_number = rs.getString("PARKING_NUMBER");

				obj.put("parking_id", parking_id);
				obj.put("parking_floor", parking_floor);
				obj.put("parking_area", parking_area);
				obj.put("parking_number", parking_number);
				arr.add(obj);				
			}
			

		} catch (Exception e) {
			logger.info("Exception " + e.getMessage());

		} finally {
			if (connection != null) {
				connection.close();
			}
		}

		logger.info("ParingService List : " + arr);
		return arr;

	}

}