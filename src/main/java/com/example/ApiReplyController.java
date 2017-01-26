package com.example;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.Reply;
import com.example.domain.ReplyRepository;
import com.example.domain.User;
import com.example.utils.HttpSessionUtil;

@RestController
@RequestMapping("/api")
public class ApiReplyController {
	private static final Logger log = LoggerFactory.getLogger(ApiReplyController.class);
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("/questions/{questionId}/reply")
	public Reply create(@PathVariable long questionId, String repcontents, HttpSession session){
		User loginUser = HttpSessionUtil.GetUserFromSession(session);
		Question question = questionRepository.findOne(questionId);
		Reply reply = new Reply(loginUser, question, repcontents);
		log.debug(">>Create Reply : "+reply);
		
		return replyRepository.save(reply);
	}
}
