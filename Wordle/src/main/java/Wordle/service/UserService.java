package Wordle.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import Wordle.model.CharacterValue;
import Wordle.model.Game;
import Wordle.model.GameStatus;
import Wordle.model.GuessResponse;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Scope(value = SCOPE_SINGLETON)
@Service
public class UserService {
	ConcurrentHashMap<String,Game> userGames = new ConcurrentHashMap<>();
	
	public String addGameToDataStore(String word) {
		String userKey = UUID.randomUUID().toString(); //randomly generated user key
		userGames.put(userKey, new Game(word,0)); //and game word and tries done
		return userKey;
	}
	
	public GuessResponse submitGuess(String requestWord, String userToken ) {
		Game userGame = Optional.of(userGames.get(userToken)).orElseThrow(()-> new RuntimeException("session doesn't exist"));
		if(userGame.getCurrentTries()>5) {
			throw new RuntimeException("game finished already");
		}
		
		int currentTry = userGame.getCurrentTries();
		userGame.setCurrentTries(++currentTry);
		String userWord = userGame.getWord();
		Map<String,CharacterValue> characterValueMap = new HashMap<>();
		
		if(userWord.equalsIgnoreCase(requestWord)) {
			userGames.remove(userToken);
			return new GuessResponse(currentTry, requestWord, null, GameStatus.WIN);
		}
		else {
			Set<Character> checkingSet = new HashSet<>();
			for(int i=0;i<userWord.length();i++)
				checkingSet.add(userWord.charAt(i));
			for(int i=0;i<userWord.length();i++)
			{
				if(userWord.charAt(i)==requestWord.charAt(i)) {
					characterValueMap.put(String.valueOf(requestWord.charAt(i)), CharacterValue.CORRECT);
				}
				else if(checkingSet.contains(requestWord.charAt(i))) {
					characterValueMap.put(String.valueOf(requestWord.charAt(i)), CharacterValue.PRESENT_BUT_MISPLACED);
				}
				else {
					characterValueMap.put(String.valueOf(requestWord.charAt(i)), CharacterValue.NOT_PRESENT);
				}
			}
			userGames.replace(userToken, userGame);
			return new GuessResponse(currentTry, requestWord, null, GameStatus.IN_PROGESS);
		}
	}
}
