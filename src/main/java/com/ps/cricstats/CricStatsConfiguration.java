package com.ps.cricstats;

import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CricStatsConfiguration {
	
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean
	public JSONObject jsonObject()
	{
		return new JSONObject();
	}
}
