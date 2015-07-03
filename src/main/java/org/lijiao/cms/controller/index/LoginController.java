package org.lijiao.cms.controller.index;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.lijiao.cms.model.Student;
import org.lijiao.cms.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("indexLoginController")
public class LoginController {
	private IStudentService studentService;
	
	public IStudentService getStudentService() {
		return studentService;
	}
	@Inject
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping("/index_login")
	public String login() {
		return "index/login";
	}

	@RequestMapping("/dologin")
	public void dologin(String username, String password,
			HttpServletResponse response,HttpSession session) {
		Student student = studentService.login(username, password);
		JSONObject data = new JSONObject();
		if(student==null){
			data.put("status", "n");
			data.put("info", "用户名或者密码不正确");
		}else if(!password.equals(student.getPassword())){
			data.put("status", "n");
			data.put("info", "用户名或者密码不正确");
		}else{
			data.put("status", "y");
			session.setAttribute("indexLogin", student);
		}
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("indexLogin");
		return "redirect:/index_login";
	}
}
