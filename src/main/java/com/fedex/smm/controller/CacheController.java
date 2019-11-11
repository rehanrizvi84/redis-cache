package com.fedex.smm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.smm.dto.LoadCacheResponse;
import com.fedex.smm.service.CacheService;

@RestController
public class CacheController {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheController.class);
	
	@Autowired
	private CacheService cacheService;

	@GetMapping(value = "loadCache")
	public LoadCacheResponse loadCache() {
		LoadCacheResponse cacheResponse = cacheService.loadCache();
		logger.info("Loaded All table information to Cache");
		return cacheResponse;

	}
}
