package com.ps.cricstats.service;

import com.ps.cricstats.utils.CricStatsResponse;

public interface ICricStatsService {
	
	public CricStatsResponse fetchCricStats(final String matchUniqueId);
}
