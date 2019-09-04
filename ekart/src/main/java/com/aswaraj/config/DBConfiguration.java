package com.aswaraj.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration {

	@Value("${app.message}")
	private String appMessage;
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	
	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	@Profile("dev")
	@Bean
	public String devDatabaseConnection() {
		System.out.println(appMessage);
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB Connection for Dev-H2";
	}
	
	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		System.out.println(appMessage);
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB Connection for Prod-Mysql";
	}
}
