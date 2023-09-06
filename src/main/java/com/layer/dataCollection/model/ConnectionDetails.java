package com.layer.dataCollection.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Document(collection = "data_collection_db")
public class ConnectionDetails {
	
	@Id
	String id;
	@Getter @Setter
	String ip;
	@Getter @Setter
	Date date;
	@Getter @Setter
	String dateString;
	@Getter @Setter
	String userAgent;
	@Getter @Setter
	String remoteUser;
	@Getter @Setter
	String remoteHost;
	@Getter @Setter
	String remotePort;
	@Getter @Setter
	String browser;
	@Getter @Setter
	String browserVersion;
	@Getter @Setter
	String osVersion;
	@Getter @Setter
	String referrer;

}
