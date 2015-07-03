package org.lijiao.cms.controller.index;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lijiao.basic.model.Pager;
import org.lijiao.basic.model.SystemContext;
import org.lijiao.cms.model.Attachment;
import org.lijiao.cms.model.Channel;
import org.lijiao.cms.model.ChannelType;
import org.lijiao.cms.model.Topic;
import org.lijiao.cms.service.IAttachmentService;
import org.lijiao.cms.service.IChannelService;
import org.lijiao.cms.service.ICmsLinkService;
import org.lijiao.cms.service.IKeywordService;
import org.lijiao.cms.service.ITopicService;
import org.lijiao.cms.web.BaseInfoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	private IChannelService channelService;
	private ITopicService topicService;
	private IAttachmentService attachmentService;
	private IKeywordService keywordService;
	private ICmsLinkService cmslinkService;

	public ICmsLinkService getCmslinkService() {
		return cmslinkService;
	}
	@Inject
	public void setCmslinkService(ICmsLinkService cmslinkService) {
		this.cmslinkService = cmslinkService;
	}

	public IKeywordService getKeywordService() {
		return keywordService;
	}

	@Inject
	public void setKeywordService(IKeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	@Inject
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
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

	@RequestMapping({ "/", "/index" })
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("indexLogin") == null) {
			return "redirect:/index_login";
		} else {
			model.addAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
			model.addAttribute("cs", channelService.listAllIndexChannel());
			model.addAttribute("kws", keywordService.getMaxTimesKeyword(9));
			model.addAttribute("link",cmslinkService.listAllLink());
			model.addAttribute("t1",
					topicService.listTopicByChannelAndNumber(1, 7));
			model.addAttribute("t2",
					topicService.listTopicByChannelAndNumber(2, 6));
			model.addAttribute("t3",
					topicService.listTopicByChannelAndNumber(3, 6));
			model.addAttribute("t4",
					topicService.listTopicByChannelAndNumber(4, 6));
			return "index/index";
		}
	}

	@RequestMapping("/channel/{cid}")
	public String showChannel(@PathVariable int cid, Model model,
			HttpServletResponse resp, HttpServletRequest req,
			HttpSession session) throws IOException {
		if (session.getAttribute("indexLogin") == null) {
			return "redirect:/index_login";
		} else {
			Channel c = channelService.load(cid);
			SystemContext.setSort("t.publishDate");
			SystemContext.setOrder("desc");
			// System.out.println(c.getType());
			model.addAttribute("datas", topicService.find(c.getId(), null, 1));
			SystemContext.removeSort();
			SystemContext.removeOrder();
			model.addAttribute("cs", channelService.listAllIndexChannel());
			model.addAttribute("channel", c);
			return "index/channel";
		}
	}

	@RequestMapping("/topic/{tid}")
	public String showTopic(@PathVariable int tid, Model model,
			HttpSession session) {
		if (session.getAttribute("indexLogin") == null) {
			return "redirect:/index_login";
		} else {
			Topic t = topicService.load(tid);
			String keywords = t.getKeyword();
			model.addAttribute("topic", t);
			if (keywords == null || "".equals(keywords.trim())
					|| "\\|".equals(keywords.trim())) {
				model.addAttribute("hasKey", false);
			} else {
				String[] kws = keywords.split("\\|");
				model.addAttribute("hasKey", true);
				model.addAttribute("kws", kws);
			}
			List<Attachment> atts = attachmentService.listAttachByTopic(tid);
			if (atts.size() > 0) {
				model.addAttribute("hasAtts", true);
				model.addAttribute("atts", atts);
			} else {
				model.addAttribute("hasAtts", false);
			}
			model.addAttribute("cs", channelService.listAllIndexChannel());
			return "index/topic";
		}
	}

	@RequestMapping("/search/{con}")
	public String search(@PathVariable String con, Model model,
			HttpSession session) {
		if (session.getAttribute("indexLogin") == null) {
			return "redirect:/index_login";
		} else {
			SystemContext.setOrder("asc");
			SystemContext.setSort("c.orders");
			model.addAttribute("cs",
					channelService.listChannelByType(ChannelType.NAV_CHANNEL));
			SystemContext.setOrder("desc");
			SystemContext.setSort("t.publishDate");
			Pager<Topic> topics = topicService.searchTopic(con);
			SystemContext.removeOrder();
			SystemContext.removeSort();
			emp(topics, con);
			model.addAttribute("datas", topics);
			model.addAttribute("con", con);
			model.addAttribute("cs", channelService.listAllIndexChannel());
			return "index/search";
		}
	}

	@RequestMapping("/keyword/{con}")
	public String keyword(@PathVariable String con, Model model,
			HttpSession session) {
		if (session.getAttribute("indexLogin") == null) {
			return "redirect:/index_login";
		} else {
			model.addAttribute("kws", keywordService.getMaxTimesKeyword(9));
			SystemContext.setOrder("desc");
			SystemContext.setSort("t.publishDate");
			Pager<Topic> topics = topicService.searchTopicByKeyword(con);
			SystemContext.removeOrder();
			SystemContext.removeSort();
			emp(topics, con);
			model.addAttribute("datas", topics);
			model.addAttribute("con", con);
			model.addAttribute("cs", channelService.listAllIndexChannel());
			return "index/keyword";
		}
	}

	private void emp(Pager<Topic> topics, String con) {
		for (Topic t : topics.getDatas()) {
			if (t.getTitle().contains(con)) {
				String tt = t.getTitle().replaceAll(con,
						"<span class='emp'>" + con + "</span>");
				t.setTitle(tt);
			}
		}
	}
}
