package com.layer.dataCollection.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.layer.dataCollection.model.ConnectionDetails;
import com.layer.dataCollection.repository.ConnectionDetailsRepo;

import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataService {
	
	@Autowired
	ConnectionDetailsRepo connectionsRepo;
	
	public List<ConnectionDetails> getAllConnections() {
		List<ConnectionDetails> details = connectionsRepo.findAll();
		log.info("All connections : {}",details.toString());
		return details;
	}
	
	public String decodeReferrer(String referrer) {
		return referrer.chars().mapToObj(map->(map=='_')?"z":(map=='-')?" ":String.valueOf((char)(map-1))).collect(Collectors.joining());
	}
	
	public void saveConnectionDetails(Map<String,String> query,HttpServletRequest servletRequest) {
		System.out.println(query);
		String referrer = query.containsKey("referrer")?query.get("referrer"):null;
		referrer = decodeReferrer(referrer);
		ConnectionDetails connDetails = new ConnectionDetails();
		connDetails.setIp(servletRequest.getRemoteAddr());
		connDetails.setBrowser(UserAgent.parseUserAgentString(servletRequest.getHeader("User-Agent")).getBrowser()+"");
		connDetails.setBrowserVersion(UserAgent.parseUserAgentString(servletRequest.getHeader("User-Agent")).getBrowserVersion()+"");
		connDetails.setOsVersion(UserAgent.parseUserAgentString(servletRequest.getHeader("User-Agent")).getOperatingSystem()+"");
		connDetails.setRemoteHost(servletRequest.getRemoteHost());
		connDetails.setRemotePort(servletRequest.getRemotePort()+"");
		connDetails.setRemoteUser(servletRequest.getRemoteUser());
		connDetails.setUserAgent(servletRequest.getHeader("User-Agent"));
		connDetails.setReferrer(referrer);
		
		LocalDateTime localDateTime = LocalDateTime.now();
		log.info(localDateTime.toString());
		Date dat = Date.from(localDateTime.toInstant(ZoneOffset.ofHoursMinutes(5, 30)));
		log.info(new Date().toString());
		connDetails.setDate(new Date());
		connDetails.setDateString(dat.toString());
		log.info(connDetails.getDate().toString());
		saveConnection(connDetails);
	}
	
	public void saveConnection(ConnectionDetails connDetails) {
		connectionsRepo.save(connDetails);
		log.info("Saved connection detail : {}",connDetails);
	}
	
	public void deleteAll(ConnectionDetails connDetails) {
		connectionsRepo.deleteAll();
		log.info("Deleted all connections : {}",connDetails);
	}
	
	

}
