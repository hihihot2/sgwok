package com.example.web;

import javax.servlet.http.HttpSession;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.domain.UserRepository;
import com.example.utils.HttpSessionUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired //자동으로 변수에 할당, 어노테이션 안해주면 null값밖에 안뜬다
	private UserRepository userRepository;
	
	@PostMapping("/create")
	public String create(User user){
		userRepository.save(user);
		//System.out.println("getUserData : " + user.toString());
		log.debug("user : " + user.toString());
		//System.out.println("totalUserCount : " + users.size());
		return "redirect:/";
	}
	
	@GetMapping("/list2")
	public String list(Model model){
		log.debug(">>list");
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@PostMapping("/loginchk")
	public String login(String userId, String password, HttpSession session){
		log.debug(">>loginchk");
		User user = userRepository.findByUserId(userId);
		
		if(user == null){
			log.info("It's not User");
			return "redirect:/acessloginfail";
		}else{
			log.info("user's pwd : " + user.getPassword() + ", " + password);
			if(user.matchPassword(password)){
				log.info("login ok");
				session.setAttribute("loginUser", user);
				return "redirect:/";
			}else
				return "redirect:/acessloginfail";
		}
		//로그인 실패시 redirect:/user/login.html로 이동
		//return "redirect:/";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable long id, Model model, HttpSession session){
		User loginUser = (User) session.getAttribute("loginUser");
		
		if(loginUser == null){
			throw new IllegalStateException("You aren't Login User");
		}
		log.info(">>Modify");
		model.addAttribute("myinfo", userRepository.findOne(id));
		return "/user/update_form";
	}
	
	@PostMapping("/{id}/modify")
	public String modifyInfo(@PathVariable long id, User user,HttpSession session){
		User loginUser = (User) session.getAttribute(HttpSessionUtil.LOGIN_USER);
		
		if(!HttpSessionUtil.isLoginUser(session)){
			throw new IllegalStateException("You aren't Login User");
		}else if(loginUser.matchId(id)){
			log.info(">>Modify : " + user.toString());
			User temp = userRepository.findOne(id);
			if(user.matchPassword(temp.getPassword())){
				userRepository.save(user);
				return "redirect:/";
			}else
				return "redirect:/user/{id}/form";
		}else
			return "/";
	}
	
	@GetMapping("/{id}/logout")
	public String logout(@PathVariable long id, HttpSession session){
		User loginUser = (User) session.getAttribute(HttpSessionUtil.LOGIN_USER);
		
		if(!HttpSessionUtil.isLoginUser(session)){
			throw new IllegalStateException("You aren't Login User");
		}else{
			log.info(">>Logout");
			session.invalidate();
		}
		return "redirect:/";
	}
}