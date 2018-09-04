package com.credsystem.timesheet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.credsystem.timesheet.util.MsgProperties;

@Configuration
@ComponentScan(basePackages = "com.credsystem.timesheet")
@PropertySource(value = {"file:src/main/resources/msg.properties"})
public class MenssagemConfig {

	 @Bean(initMethod = "init")
	 public MsgProperties msgPropertieBean() {
	 return new MsgProperties();
	 }
	


}
