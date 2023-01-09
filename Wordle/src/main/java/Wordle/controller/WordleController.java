package Wordle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Wordle.model.GuessResponse;
import Wordle.service.UserService;
import Wordle.service.WordService;

@RestController
public class WordleController {
	
	private UserService userService;
	private WordService wordsService;
	
	@Autowired
	public WordleController(UserService userService, WordService wordsService) {
		super();
		this.userService = userService;
		this.wordsService = wordsService;
	}
	
	@GetMapping("/startGame")
	public String startGame() {
		String word = wordsService.getRandomWord();
		System.out.println(word);
		return userService.addGameToDataStore(word);
	}
	
	@PostMapping("/submitGuess")
	public GuessResponse submitGuess(@RequestParam String guess, @RequestParam String userToken) {
		return userService.submitGuess(guess, userToken);
	}
}
