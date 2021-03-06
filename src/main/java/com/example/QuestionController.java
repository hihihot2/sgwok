package com.example;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtil;

@Controller
public class QuestionController {
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/")
	public String Main(Model model){
		log.debug(">>main");
		model.addAttribute("qnalist", questionRepository.findAll());
		return "/index";
	}
	
	@PostMapping("/qna/create")
	public String getQna(String title, String contents, HttpSession session){
		if(!HttpSessionUtil.isLoginUser(session)){
			throw new IllegalStateException("You aren't Login User");
		}else{
			User user = HttpSessionUtil.GetUserFromSession(session);
			Question newQuestion = new Question(user, title, contents);
			questionRepository.save(newQuestion);
			log.debug("Question Data : " + newQuestion);
		}
		return "redirect:/";
	}
	
	
}