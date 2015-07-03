package org.lijiao.cms.controller.admin;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.lijiao.cms.model.BaseInfo;
import org.lijiao.cms.service.IAttachmentService;
import org.lijiao.cms.web.BaseInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/system")
@Controller
public class SystemController {
	private IAttachmentService attachmentService;
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	@Inject
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}


	@RequestMapping("/baseinfo")
	public String showBaseInfo() {
		return "system/showBaseInfo";
	}
	
	@RequestMapping(value="/baseinfo/update",method=RequestMethod.GET)
	public String updateBaseInfo(HttpSession session,Model model) {
		model.addAttribute("baseInfo", session.getServletContext().getAttribute("baseInfo"));
		return "system/updateBaseInfo";
	}
	@RequestMapping(value="/baseinfo/update",method=RequestMethod.POST)
	public String updateBaseInfo(@Validated BaseInfo baseInfo,BindingResult br,HttpSession session) {
		if(br.hasErrors()) {
			return "system/updateBaseInfo";
		}
		BaseInfo bi = BaseInfoUtil.getInstacne().write(baseInfo);
		session.getServletContext().setAttribute("baseInfo", bi);
		return "redirect:/admin/system/baseinfo";
	}
}
