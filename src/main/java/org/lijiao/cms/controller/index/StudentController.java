package org.lijiao.cms.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.lijiao.cms.dto.QuestionDto;
import org.lijiao.cms.model.Question;
import org.lijiao.cms.model.Student;
import org.lijiao.cms.service.IExamtypeService;
import org.lijiao.cms.service.IQuestionService;
import org.lijiao.cms.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
	private IStudentService studentService;
	private IExamtypeService examtypeService;
	private IQuestionService questionService;

	public IQuestionService getQuestionService() {
		return questionService;
	}

	@Inject
	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	public IExamtypeService getExamtypeService() {
		return examtypeService;
	}

	@Inject
	public void setExamtypeService(IExamtypeService examtypeService) {
		this.examtypeService = examtypeService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	@Inject
	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping("/register")
	public String register() {
		return "index/register";
	}

	@RequestMapping("/student")
	public String student(Model model, HttpSession session) {
		if (session.getAttribute("indexLogin") == null) {
			return "redirect:/index_login";
		} else {
			model.addAttribute("student", session.getAttribute("indexLogin"));
			model.addAttribute("examtypes", examtypeService.listExamtype());
			return "index/student";
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public void update(@PathVariable int id, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Student student = studentService.load(id);
		student.setNickname(request.getParameter("nickname"));
		student.setEmail(request.getParameter("email"));
		student.setPhone(request.getParameter("phone"));
		student.setQq(request.getParameter("qq"));
		studentService.update(student);
		session.removeAttribute("indexLogin");
		session.setAttribute("indexLogin", studentService.load(id));
		model.addAttribute("student", session.getAttribute("indexLogin"));
		JSONObject data = new JSONObject();
		data.put("status", "y");
		data.put("info", "更新成功");
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/updatepass/{id}", method = RequestMethod.POST)
	public void updatePass(@PathVariable int id, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		JSONObject data = new JSONObject();
		Student student = studentService.load(id);
		String oldPass = request.getParameter("oldpass");
		if(oldPass.equals(student.getPassword())){
			student.setPassword(request.getParameter("pass"));
			studentService.update(student);
			session.removeAttribute("indexLogin");
			session.setAttribute("indexLogin", studentService.load(id));
			model.addAttribute("student", session.getAttribute("indexLogin"));
			
			data.put("status", "y");
			data.put("info", "更新成功");
		}else{
			data.put("status", "n");
			data.put("info", "原密码不正确");
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
	@RequestMapping("/beginexam")
	public String beginexam(HttpServletRequest request, Model model,
			HttpSession session) {
		if (session.getAttribute("indexLogin") == null) {
			return "redirect:/index_login";
		} else {
			int eid = Integer.parseInt(request.getParameter("eid"));
			List<Question> questions = questionService
					.listExamtypeQuestions(eid);
			List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
			for (Question question : questions) {
				QuestionDto questionDto = new QuestionDto();
				questionDto.setQuestion(question);
				questionDto.setAnswers(Arrays.asList(question.getAnswer()
						.split("/")));
				questionDtos.add(questionDto);
			}
			model.addAttribute("questionDtos", questionDtos);
			model.addAttribute("examtype", examtypeService.load(eid));
			return "index/exam";
		}
	}

	@RequestMapping("/exam")
	public String exam(HttpServletRequest request, Model model) {
		int eid = Integer.parseInt(request.getParameter("eid"));
		List<Question> questions = questionService.listExamtypeQuestions(eid);
		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		int grade = 0;
		for (int i = 0; i < questions.size(); i++) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.setQuestion(questions.get(i));
			questionDto.setAnswers(Arrays.asList(questions.get(i).getAnswer()
					.split("/")));
			String[] answers = request.getParameterValues("answer" + i);
			if (answers != null) {
				String realAnswer = questions.get(i).getTheoption();
				String answer = StringUtils.join(answers, "/");
				if (realAnswer.equals(answer)) {
					grade += Integer.parseInt(questions.get(i).getScore());
				}
			}
			questionDto.setYourOption(StringUtils.join(answers, "/"));
			questionDtos.add(questionDto);
		}
		model.addAttribute("questionDtos", questionDtos);
		model.addAttribute("grade", grade);
		model.addAttribute("examtype", examtypeService.load(eid));
		return "index/result";
	}
	@RequestMapping("/checkusername")
	public void checkUsername(HttpServletRequest reqeuest,HttpServletResponse response){
		String username = reqeuest.getParameter("param");
		Student student = studentService.loadByUsername(username);
		JSONObject data = new JSONObject();
		if(student == null){
			data.put("status", "y");
			data.put("info", "验证通过");
		}else{
			data.put("status", "n");
			data.put("info", "该用户名已注册");
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
	@RequestMapping("/insert")
	public void insert(HttpServletRequest reqeuest,HttpServletResponse response){
		String username = reqeuest.getParameter("username");
		String nickname = reqeuest.getParameter("nickname");
		String password = reqeuest.getParameter("password");
		String phone = reqeuest.getParameter("phone");
		String qq = reqeuest.getParameter("qq");
		String email = reqeuest.getParameter("email");
		
		Student student = new Student();
		student.setEmail(email);
		student.setNickname(nickname);
		student.setPassword(password);
		student.setPhone(phone);
		student.setQq(qq);
		student.setUsername(username);
		studentService.add(student);
		JSONObject data = new JSONObject();
		data.put("status", "y");
		data.put("info", "添加成功");
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(data);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
