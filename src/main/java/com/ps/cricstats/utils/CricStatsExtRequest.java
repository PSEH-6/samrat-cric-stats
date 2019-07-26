package com.ps.cricstats.utils;

public class CricStatsExtRequest {
	
	private String unique_id;
	private String apikey;
	
	public CricStatsExtRequest(final String matchUniqueId , final String apiKey)
	{
		this.unique_id = matchUniqueId;
		this.apikey = apiKey;
	}
	
	public String getUniqueId() {
		return unique_id;
	}

	public String getApiKey() {
		return apikey;
	}
}
