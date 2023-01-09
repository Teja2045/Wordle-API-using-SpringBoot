package Wordle.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

@Service
public class WordService {
	
	private List<String> words;
	@PostConstruct
	private void loadWords() {
		try {
			words = FileUtils.readLines(new File("C:\\Users\\User\\Documents\\Wordle\\src\\main\\resources\\words.bit"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public String getRandomWord() {
		Random random = new Random();
		return words.get(random.nextInt(words.size()));
	}
}
