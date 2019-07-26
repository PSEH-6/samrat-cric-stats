package com.ps.cricstats.utils;

public class CricStatsResponse {
	
	private String teamA;
	private String teamB;
	//private String winningTeam;
	private String winningTeamScore;
	private String winningTeamRoundRotScore;
	
	/**
	 * @param teamA
	 * @param teamB
	 * @param winningTeam
	 * @param winningTeamScore
	 * @param winningTeamRoundRotScore
	 */
	public CricStatsResponse(String teamA, String teamB, String winningTeam, String winningTeamScore,
			String winningTeamRoundRotScore) {
		super();
		this.teamA = teamA;
		this.teamB = teamB;
		//this.winningTeam = winningTeam;
		this.winningTeamScore = winningTeamScore;
		this.winningTeamRoundRotScore = winningTeamRoundRotScore;
	}
	public String getTeamA() {
		return teamA;
	}
	
	public String getTeamB() {
		return teamB;
	}
	
//	public String getWinningTeam() {
//		return winningTeam;
//	}
	
	public String getWinningTeamScore() {
		return winningTeamScore;
	}
	
	public String getWinningTeamRoundRotScore() {
		return winningTeamRoundRotScore;
	}
	
	
	
	
}
