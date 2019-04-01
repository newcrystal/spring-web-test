package com.crystal.spring_web_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Simple {
	@Test
	public void collect() {
		List<GameCharacter> characters = new ArrayList<>();
		characters.addAll(characters());
		List<List<GameCharacter>> groupByServerIdList = characters.stream()
			.collect(Collectors.groupingBy(GameCharacter::getServerId, Collectors.toList()))
			.values().stream().collect(Collectors.toList());
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(groupByServerIdList));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	private class GameCharacter {
		private int serverId;
		private String serverName;

		public GameCharacter(int serverId, String serverName) {
			super();
			this.serverId = serverId;
			this.serverName = serverName;
		}

		public int getServerId() {
			return serverId;
		}

		public String getServerName() {
			return serverName;
		}

		public void setServerId(int serverId) {
			this.serverId = serverId;
		}

		public void setServerName(String serverName) {
			this.serverName = serverName;
		}
		
	}
	
	private List<GameCharacter> characters() {
		List<GameCharacter> characters = new ArrayList<>();
		for (int i=0; i<10; i++) {
			for (int j=0; j<8; j++) {
				if (i!=2) characters.add(new GameCharacter(i, "캐릭터"+j));
				else  characters.add(new GameCharacter(i, "캐릭터2"+j));
			}
		}	
		return characters;
	}
}
