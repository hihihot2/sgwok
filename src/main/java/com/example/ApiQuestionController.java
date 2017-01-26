package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class ApiQuestionController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("")
	public List<Question> list(){
		return (List<Question>) questionRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Question getQuestion(@PathVariable long id){
		return questionRepository.findOne(id);
	}
}
