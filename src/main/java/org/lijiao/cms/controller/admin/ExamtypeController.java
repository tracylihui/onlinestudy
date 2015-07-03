package org.lijiao.cms.controller.admin;

import javax.inject.Inject;

import org.lijiao.cms.model.Examtype;
import org.lijiao.cms.service.IExamtypeService;
import org.lijiao.cms.service.IQuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/examtype")
@Controller
public class ExamtypeController {
	private IExamtypeService examtypeService;
	private IQuestionService questionService;

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

	@RequestMapping("/examtypes")
	public String list(Model model) {
		model.addAttribute("datas", examtypeService.findExamtype());
		return "examtype/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new Examtype());
		return "examtype/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Validated Examtype examtype, BindingResult br) {
		if (br.hasErrors()) {
			return "examtype/add";
		}
		examtypeService.add(examtype);
		return "redirect:/admin/examtype/examtypes";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, Model model) {
		model.addAttribute(examtypeService.load(id));
		return "examtype/update";
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
		examtypeService.delete(id);
		return "redirect:/admin/examtype/examtypes";
	}

	@RequestMapping("/{id}")
	public String show(@PathVariable int id, Model model) {
		model.addAttribute("datas", questionService.findExamtypeQuestions(id));
		return "question/list";
	}
	//
	// @RequestMapping("/clearUsers/{id}")
	// public String clearGroupUsers(@PathVariable int id) {
	// groupService.deleteGroupUsers(id);
	// return "redirect:/admin/group/groups";
	// }
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
