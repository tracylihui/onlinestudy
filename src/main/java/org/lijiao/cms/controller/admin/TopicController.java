package org.lijiao.cms.controller.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.lijiao.basic.model.SystemContext;
import org.lijiao.basic.util.JsonUtil;
import org.lijiao.cms.dto.AjaxObj;
import org.lijiao.cms.dto.TopicDto;
import org.lijiao.cms.model.Attachment;
import org.lijiao.cms.model.ChannelTree;
import org.lijiao.cms.model.Topic;
import org.lijiao.cms.model.User;
import org.lijiao.cms.service.IAttachmentService;
import org.lijiao.cms.service.IChannelService;
import org.lijiao.cms.service.IGroupService;
import org.lijiao.cms.service.IKeywordService;
import org.lijiao.cms.service.ITopicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/topic")
public class TopicController {
	private ITopicService topicService;
	private IChannelService channelService;
	private IKeywordService keywordService;
	private IAttachmentService attachmentService;
	private IGroupService groupService;
	
	
	
	
	public IGroupService getGroupService() {
		return groupService;
	}
	@Inject
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}

	private final static List<String> imgTypes = Arrays.asList("jpg","jpeg","gif","png");
	
	
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}
	@Inject
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public IKeywordService getKeywordService() {
		return keywordService;
	}

	@Inject
	public void setKeywordService(IKeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public IChannelService getChannelService() {
		return channelService;
	}
	
	@Inject
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}
	public ITopicService getTopicService() {
		return topicService;
	}
	@Inject
	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}
	
	private void initList(String con,Integer cid,Model model,HttpSession session,Integer status) {
		SystemContext.setSort("t.publishDate");
		SystemContext.setOrder("desc");
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		if(isAdmin) {
			model.addAttribute("datas",topicService.find(cid, con, status));
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			model.addAttribute("datas", topicService.find(loginUser.getId(),cid, con, status));
		}
		if(con==null) con="";
		SystemContext.removeOrder();
		SystemContext.removeSort();
		model.addAttribute("con",con);
		model.addAttribute("cid",cid);
		model.addAttribute("cs",channelService.listPublishChannel());
	}

	@RequestMapping("/audits")
	public String auditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,1);
		return "topic/list";
	}
	
	@RequestMapping("/unaudits")
	public String unauditList(@RequestParam(required=false) String con,@RequestParam(required=false) Integer cid,Model model,HttpSession session) {
		initList(con, cid, model, session,0);
		return "topic/list";
	}
	
	@RequestMapping("/changeStatus/{id}")
	public String changeStatus(@PathVariable int id,Integer status) {
		topicService.updateStatus(id);
		Topic t = topicService.load(id);
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
		}
		if(status==0) {
			return "redirect:/admin/topic/unaudits";
		} else {
			return "redirect:/admin/topic/audits";
		}
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id,Integer status) {
		Topic t = topicService.load(id);
		topicService.delete(id);
		if(topicService.isUpdateIndex(t.getChannel().getId())) {
		}
		if(status==0) {
			return "redirect:/admin/topic/unaudits";
		} else {
			return "redirect:/admin/topic/audits";
		}
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		Topic t = new Topic();
		t.setPublishDate(new Date());
		TopicDto td = new TopicDto(t);
		model.addAttribute("topicDto",td);
		return "topic/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/add";
		}
		Topic t = topicDto.getTopic();
		User loginUser = (User)session.getAttribute("loginUser");
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		t.setKeyword(keys.toString());
		topicService.add(t, topicDto.getCid(), loginUser.getId(),aids);
		if(topicDto.getStatus()==1&&topicService.isUpdateIndex(topicDto.getCid())) {
		}
		return "redirect:/jsp/common/addSuc.jsp";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		Topic t = topicService.load(id);
		String keyword = t.getKeyword();
		if(keyword!=null&&!"".equals(keyword.trim()))
			model.addAttribute("keywords",keyword.split("\\|"));
		model.addAttribute("atts",attachmentService.listByTopic(id));
		TopicDto td = new TopicDto(t,t.getChannel().getId());
		model.addAttribute("topicDto",td);
		model.addAttribute("cname",t.getChannel().getName());
		return "topic/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Validated TopicDto topicDto,BindingResult br,String[]aks,Integer[] aids,HttpSession session) {
		if(br.hasErrors()) {
			return "topic/update";
		}
		Topic tt = topicService.load(id);
		Topic t = topicDto.getTopic();
		StringBuffer keys = new StringBuffer();
		if(aks!=null) {
			for(String k:aks) {
				keys.append(k).append("|");
				keywordService.addOrUpdate(k);
			}
		}
		tt.setKeyword(keys.toString());
		tt.setChannelPicId(t.getChannelPicId());
		tt.setContent(t.getContent());
		tt.setPublishDate(t.getPublishDate());
		tt.setRecommend(t.getRecommend());
		tt.setStatus(t.getStatus());
		tt.setSummary(t.getSummary());
		tt.setTitle(t.getTitle());
		topicService.update(tt, topicDto.getCid(),aids);
		if(topicService.isUpdateIndex(topicDto.getCid())) {
		}
		return "redirect:/jsp/common/addSuc.jsp";
	}
	
	@RequestMapping("/{id}")
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(topicService.load(id));
		model.addAttribute("atts",attachmentService.listByTopic(id));
		return "topic/show";
	}
	
	@RequestMapping(value="/searchKeyword")
	public @ResponseBody List<String> searchKeyword(String term) {
		return keywordService.listKeywordStringByCon(term);
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)//返回的是json类型的值，而uploadify只能接受字符串
	public void upload(MultipartFile attach,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain;charset=utf-8");
		AjaxObj ao = null;
		try {
			Attachment att = new Attachment();
			String ext = FilenameUtils.getExtension(attach.getOriginalFilename());
//			System.out.println(ext);
			att.setIsAttach(0);
			if(imgTypes.contains(ext)) att.setIsImg(1);
			else att.setIsImg(0);
			att.setIsIndexPic(0);
			att.setNewName(String.valueOf(new Date().getTime())+"."+ext);
			att.setOldName(FilenameUtils.getBaseName(attach.getOriginalFilename()));
			att.setSuffix(ext);
			att.setType(attach.getContentType());
			att.setTopic(null);
			att.setSize(attach.getSize());
			attachmentService.add(att, attach.getInputStream());
			ao = new AjaxObj(1,null,att);
		} catch (IOException e) {
			ao = new AjaxObj(0,e.getMessage());
		}
		resp.getWriter().write(JsonUtil.getInstance().obj2json(ao));
	}
	
	@RequestMapping("/treeAll")
	public @ResponseBody List<ChannelTree> tree(HttpSession session) {
		boolean isAdmin = (Boolean)session.getAttribute("isAdmin");
		User loginUser = (User)session.getAttribute("loginUser");
		if(isAdmin)
			return channelService.generateTree();
		else
			return groupService.generateUserChannelTree(loginUser.getId());
	}
}
