package org.lijiao.cms.controller.admin;

import javax.servlet.http.HttpSession;

import org.lijiao.cms.auth.AuthMethod;
import org.lijiao.cms.web.CmsSessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping("/admin")
	public String index(HttpSession session) {
		if(session.getAttribute("loginUser") == null){
			return "redirect:/login";
		}else{
			return "admin/index";
		}
	}

	@AuthMethod
	@RequestMapping("/admin/logout")
	public String logout(HttpSession session) {
		CmsSessionContext.removeSession(session);
		session.invalidate();
		return "redirect:/login";
	}
}
