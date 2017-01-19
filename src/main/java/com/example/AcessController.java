package com.example;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.utils.HttpSessionUtil;

@Controller
public class AcessController {
	private static final Logger log = LoggerFactory.getLogger(AcessController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping("/acesslogin")
	public String login(){
		return "/user/login";
	}
	
	@RequestMapping("/acessform")
	public String form(){
		return "/user/form";
	}
	
	@RequestMapping("/acessloginfail")
	public String loginFail(){
		return "/user/login_failed";
	}
	
	@RequestMapping("/acessprofile")
	public String profile(String userId, Model model){
		log.info(">>profile : " + userId);
		model.addAttribute("userId", userId);
		return "/user/profile";
	}
	
	@RequestMapping("/acessQna")
	public String qna(Model model, HttpSession session){
		if(!HttpSessionUtil.isLoginUser(session)){
			return "redirect:/";
		}else{
			User tmpUser = (User) session.getAttribute(HttpSessionUtil.LOGIN_USER);
			log.debug(">>allowedQna : " + tmpUser.toString());
			model.addAttribute("myinfo", tmpUser);
			return "/qna/form";
		}
	}
	
	@RequestMapping("/acessshow")
	public String show(Long id, Model model, HttpSession session){
		Question question = questionRepository.findOne(id);
		log.info(">>QNA show : " + question.toString());
		model.addAttribute("quesinfo", question);
		return "/qna/show";
	}
}
