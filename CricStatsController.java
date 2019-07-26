package com.ps.cricstats.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ps.cricstats.service.ICricStatsService;
import com.ps.cricstats.utils.CricStatsResponse;

@RestController
public class CricStatsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CricStatsController.class);
	
	@Autowired
	private ICricStatsService cricStatsServiceImpl;
	
	@GetMapping(value="/cricstats/{matchUniqueId}" , produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public CricStatsResponse fetchCricStats(@PathVariable(value ="matchUniqueId" ) final String matchUniqueId )
	{	
		CricStatsResponse response = null; 
		LOGGER.info(" Fetching cric stats for match Unique Id = {}" , matchUniqueId);
		response = cricStatsServiceImpl.fetchCricStats(matchUniqueId);
		if ( null  != response )
			LOGGER.info(" Successfully fetched cric stats for match Unique Id = {}" , matchUniqueId);
		else LOGGER.info(" Unable to fetch cric stats for match Unique Id = {}" , matchUniqueId);
		return response;
	}
}
