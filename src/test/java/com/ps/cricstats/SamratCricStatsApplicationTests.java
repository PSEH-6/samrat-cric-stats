package com.ps.cricstats;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SamratCricStatsApplicationTests {
	
	
	@Autowired
	private RestTemplate restTemplate;
		
	
	@Autowired
	private Environment springEnvironment;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	public void testFetchCricStats() throws JSONException, IOException
	{
		final String matchUniqueId = "1136617";
		final String extServiceUrl = springEnvironment.getProperty("ext-service-url");
		final String extServiceApiKey = springEnvironment.getProperty("ext-service-api-key");

	    JSONObject request1 = new JSONObject();
	    request1.put("unique_id", "1136617");
	    request1.put("apikey", "WmPJrX2s3KMyZVPFwlm1vxXLXKw1");
	    
	 // set headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<String> entity = new HttpEntity<String>(request1.toString(), headers);

	    // send request and parse result
	    ResponseEntity<String> loginResponse = restTemplate
	      .exchange(extServiceUrl, HttpMethod.POST, entity, String.class);
	    if (loginResponse.getStatusCode() == HttpStatus.OK) {
	      //JSONObject userJson = new JSONObject(loginResponse.getBody());
	      
	      ObjectMapper mapper = new ObjectMapper();
	      JsonNode rootNode = mapper.readTree(loginResponse.getBody());
	      
	      final String teamA= rootNode.get("team-1").asText();
	      final String teamB = rootNode.get("team-2").asText();
	      final String scoreDesc = rootNode.get("score").asText();
	      final String[] teamsScoreDesc = scoreDesc.split("v");
	      final String score = teamsScoreDesc[0].split(teamA)[1];
	      final String score1 = teamsScoreDesc[1].split(teamB)[1].split(" ")[1];
	      final String winningTeamScore = score.split("/")[0].compareTo(score1.split("/")[0]) > 0  ? score : score1;
	      final String roundRotation = winningTeamScore.split("/")[1].concat(winningTeamScore.split("/")[0]).concat("/");
	      System.out.println();
	      System.out.println();
	    } else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	      // nono... bad credentials
	    }
	    
	}
}
