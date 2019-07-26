/**
 * 
 */
package com.ps.cricstats.service;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.cricstats.utils.CricStatsResponse;
import com.ps.cricstats.utils.CrisStatsResBuilderUtility;

/**
 * @author samrat
 *
 */
@Service("CricStatsServiceImpl")
public class CricStatsServiceImpl implements ICricStatsService {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment springEnvironment;
	
	@Autowired
	private JSONObject extServReq;
	
	private static final String EXT_SERV_UNIQUE_ID_KEY  = "unique_id";
	private static final String EXT_SERV_API_KEY  = "apikey";
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(CricStatsServiceImpl.class);
	
	public CricStatsResponse fetchCricStats(final String matchUniqueId) {
		// TODO Auto-generated method stub
		final String extServiceUrl = springEnvironment.getProperty("ext-service-url");
		final String extServiceApiKey = springEnvironment.getProperty("ext-service-api-key");
		CricStatsResponse cricStatsResp = null;
		try
		{
		extServReq.put(EXT_SERV_UNIQUE_ID_KEY, matchUniqueId);
		extServReq.put(EXT_SERV_API_KEY, extServiceApiKey);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>(extServReq.toString(), headers);

	    // send request and parse result
	    ResponseEntity<String> extResponse = restTemplate.exchange(extServiceUrl, HttpMethod.POST, entity, String.class);
	    
	    if (extResponse.getStatusCode() == HttpStatus.OK) {
	      
	      ObjectMapper mapper = new ObjectMapper();
	      JsonNode rootNode = mapper.readTree(extResponse.getBody());
	      
	      final String teamA= rootNode.get("team-1").asText();
	      final String teamB = rootNode.get("team-2").asText();
	      final String scoreDesc = rootNode.get("score").asText();
	      final String[] teamsScoreDesc = scoreDesc.split("v");
	      final String teamAScore = teamsScoreDesc[0].split(teamA)[1];
	      final String teamBScore = teamsScoreDesc[1].split(teamB)[1].split(" ")[1];
	      final String winningTeamScore = teamAScore.split("/")[0].compareTo(teamBScore.split("/")[0]) > 0  ? teamAScore : teamBScore;
	      final String roundRotation = winningTeamScore.split("/")[1].concat(winningTeamScore.split("/")[0]).concat("/");
	      
	      cricStatsResp =  CrisStatsResBuilderUtility.decorateResponse(teamA, teamB, winningTeamScore, roundRotation);
		}
	    else
	    {
	    	LOGGER.info("Unable to fetch data from external service ");
	    }
			    
		}
		catch(JSONException | IOException exception)
		{
			LOGGER.error("JSONException | IOException occured while fetching cric stats " , exception);
		}
		return cricStatsResp;
	}

}
