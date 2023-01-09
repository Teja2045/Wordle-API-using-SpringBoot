package Wordle.model;

import java.util.Map;

public class GuessResponse {
	
	private int currentTries;
	private String word;
	private Map<String,CharacterValue>characterValueMap;
	private GameStatus status;
	
	
	
	public GuessResponse(int currentTries, String word, Map<String, CharacterValue> characterValueMap,
			GameStatus status) {
		super();
		this.currentTries = currentTries;
		this.word = word;
		this.characterValueMap = characterValueMap;
		this.status = status;
	}
	public int getCurrentTries() {
		return currentTries;
	}
	public void setCurrentTries(int currentTries) {
		this.currentTries = currentTries;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public GameStatus getStatus() {
		return status;
	}
	public void setStatus(GameStatus status) {
		this.status = status;
	}
	public Map<String, CharacterValue> getCharacterValueMap() {
		return characterValueMap;
	}
	
	public void setCharacterValueMap(Map<String, CharacterValue> characterValueMap) {
		this.characterValueMap = characterValueMap;
	}
	
}
