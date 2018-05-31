package com.crystal.spring_web_test.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crystal.spring_web_test.domain.GameLog;

@Controller
public class CSVController {
	private Map<Integer, String> fields = new HashMap<Integer, String>();
	
	@PostMapping(value="csv", consumes="text/csv")
	public @ResponseBody GameLog insertGameLog(@RequestBody String content) {
		GameLog gameLog = new GameLog();
		String[] dataArr = content.split(",");
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).equals("logId")) gameLog.setLogId(new BigInteger(dataArr[i]));
			else if (fields.get(i).equals("characterId")) gameLog.setCharacterId(Integer.valueOf(dataArr[i]));
		}
		return gameLog;
	}
	
	@PostConstruct
	private void init() {
		fields.put(0, "logId");
		fields.put(1, "characterId");
	}
}
