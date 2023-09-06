package com.layer.dataCollection.controller;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.layer.dataCollection.service.DataService;

@RestController
@RequestMapping("/data-service")
@CrossOrigin
public class DataCollectionController {
	
	@Autowired
	DataService dataService;
	
	@GetMapping(path="/save")
	public ResponseEntity<?> putDetails(@RequestParam Map<String,String> query,HttpServletRequest servletRequest) {
		dataService.saveConnectionDetails(query, servletRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<?> getAllDetails(HttpServletRequest servletRequest) {
		return new ResponseEntity<>(dataService.getAllConnections(),HttpStatus.OK);
	}
	
	@GetMapping(path="/delete")
	public ResponseEntity<?> deleteAll(@RequestParam(required = false,name="key") String key,  HttpServletRequest servletRequest) {
		if(Objects.nonNull(key)&&key.equals("test")) {
			dataService.deleteAll(null);
			return new ResponseEntity<>("Delete Success",HttpStatus.OK);	
		}else {
			return new ResponseEntity<>("Invalid Key",HttpStatus.OK);
		}
		
	}
	

}
