package com.ps.cricstats.utils;

public final class CrisStatsResBuilderUtility {
	
	private CrisStatsResBuilderUtility() {/** extra caution for immutability **/ }
	
	public static CricStatsResponse decorateResponse(final String teamA , final String teamB , final String winningTeamScore , final String roundRotVal)
	{
		return new CricStatsResponse(teamA,teamB,"",winningTeamScore,roundRotVal); 
	}

}
