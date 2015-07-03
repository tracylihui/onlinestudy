package org.lijiao.cms.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.lijiao.cms.model.Examtype;
import org.lijiao.cms.model.Question;
import org.lijiao.cms.service.IExamtypeService;
import org.lijiao.cms.service.IQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/question")
@Controller
public class QuestionController {
	private int eid;
	private IExamtypeService examtypeService;
	private IQuestionService questionService;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public IExamtypeService getExamtypeService() {
		return examtypeService;
	}

	@Inject
	public void setExamtypeService(IExamtypeService examtypeService) {
		this.examtypeService = examtypeService;
	}

	public IQuestionService getQuestionService() {
		return questionService;
	}

	@Inject
	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	@RequestMapping("/{id}")
	public String list(@PathVariable int id, Model model) {
		model.addAttribute("examtype", examtypeService.load(id));
		this.setEid(id);
		model.addAttribute("datas", questionService.findExamtypeQuestions(id));
		return "question/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Question());
		model.addAttribute("examtypes", examtypeService.listExamtype());
		model.addAttribute("eid", this.getEid());
		return "question/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated Question question, BindingResult br,
			HttpServletRequest request) {
		if (br.hasErrors()) {
			return "question/add";
		}
		int eid = Integer.parseInt(request.getParameter("eid"));
		// System.out.println(eid);
		// System.out.println(question);
		String answer = StringUtils.join(request.getParameterValues("answer"),
				"/");
		String theoption = StringUtils.join(
				request.getParameterValues("theoption"), "/");
		question.setAnswer(answer);
		question.setTheoption(theoption);
		questionService.add(question, eid);
		return "redirect:/admin/question/" + eid;
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, Model model) {
		model.addAttribute(examtypeService.load(id));
		return "question/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable int id, @Validated Examtype examtype,
			BindingResult br) {
		if (br.hasErrors()) {
			return "examtype/update";
		}
		Examtype et = examtypeService.load(id);
		et.setDescr(examtype.getDescr());
		et.setName(examtype.getName());
		examtypeService.update(et);
		return "redirect:/admin/examtype/examtypes";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		questionService.delete(id);
		return "redirect:/admin/question/" + this.getEid();
	}

	@RequestMapping("/clearQuestions/{id}")
	public String clearGroupUsers(@PathVariable int id) {
		examtypeService.deleteExamtypeQuestions(id);
		return "redirect:/admin/examtype/examtypes";
	}
	//
	// @RequestMapping("/listChannels/{gid}")
	// public String listChannels(@PathVariable int gid,Model model) {
	// model.addAttribute(groupService.load(gid));
	// return "/group/listChannel";
	// }
	//
	// @RequestMapping("/groupTree/{gid}")
	// public @ResponseBody List<ChannelTree> groupTree(@PathVariable Integer
	// gid) {
	// return groupService.generateGroupChannelTree(gid);
	// }
	//
	// @RequestMapping("/setChannels/{gid}")
	// public String setChannels(@PathVariable int gid,Model model) {
	// model.addAttribute(groupService.load(gid));
	// model.addAttribute("cids",groupService.listGroupChannelIds(gid));
	// return "/group/setChannel";
	// }
}
