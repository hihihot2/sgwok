package com.example.utils;

import javax.servlet.http.HttpSession;
import com.example.domain.User;

public class HttpSessionUtil {
	public static final String LOGIN_USER = "loginUser";

	public static boolean isLoginUser(HttpSession session){
		return session.getAttribute(LOGIN_USER) != null;
	}
	
	public static User GetUserFromSession(HttpSession session){
		if(!isLoginUser(session))
			return null;
		else{
			return (User) session.getAttribute(LOGIN_USER);
		}
	}
}
