package org.lijiao.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.lijiao.cms.dao.IChannelDao;
import org.lijiao.cms.model.Channel;
import org.lijiao.cms.model.ChannelTree;
import org.lijiao.cms.model.ChannelType;
import org.lijiao.cms.model.CmsException;
import org.lijiao.cms.model.NewChannel;
import org.lijiao.cms.model.Topic;
import org.lijiao.cms.service.IChannelService;
import org.lijiao.cms.service.ITopicService;
import org.springframework.stereotype.Service;

@Service("channelService")
public class ChannelService implements IChannelService {
	private IChannelDao channelDao;
	private ITopicService topicService;

	public ITopicService getTopicService() {
		return topicService;
	}

	@Inject
	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}

	public IChannelDao getChannelDao() {
		return channelDao;
	}

	@Inject
	public void setChannelDao(IChannelDao channelDao) {
		this.channelDao = channelDao;
	}

	public void add(Channel channel, Integer pid) {
		Integer orders = channelDao.getMaxOrderByParent(pid);
		if (pid != null && pid > 0) {
			Channel pc = channelDao.load(pid);
			if (pc == null)
				throw new CmsException("要添加栏目的父类对象不正确!");
			channel.setParent(pc);
		}
		channel.setOrders(orders + 1);
		channelDao.add(channel);
	}

	public void update(Channel channel) {
		channelDao.update(channel);
	}

	public void delete(int id) {
		// 1、需要判断是否存在子栏目
		List<Channel> cs = channelDao.listByParent(id);
		if (cs != null && cs.size() > 0)
			throw new CmsException("要删除的栏目还有子栏目，无法删除");
		// 2、需要判断是否存在文章
		List<Topic> ts = topicService.listTopicByChannel(id);
		if (ts.size() > 0) {
			throw new CmsException("该栏目还有相应的文章信息，不能删除");
		}
		// 3、需要删除和组的关联关系
		channelDao.deleteChannelGroups(id);
		channelDao.delete(id);
	}

	public void clearTopic(int id) {
		List<Topic> tops = topicService.listTopicByChannel(id);
		for (Topic t : tops) {
			topicService.delete(t.getId());
		}
	}

	public Channel load(int id) {
		return channelDao.load(id);
	}

	public List<Channel> listByParent(Integer pid) {
		return channelDao.listByParent(pid);
	}

	@Override
	public List<ChannelTree> generateTree() {
		return channelDao.generateTree();
	}

	@Override
	public List<ChannelTree> generateTreeByParent(Integer pid) {
		return channelDao.generateTreeByParent(pid);
	}

	@Override
	public void updateSort(Integer[] ids) {
		channelDao.updateSort(ids);
	}

	@Override
	public List<Channel> listPublishChannel() {
		return channelDao.listPublishChannel();
	}

	@Override
	public List<Channel> listTopNavChannel() {
		return channelDao.listTopNavChannel();
	}

	@Override
	public List<NewChannel> listAllIndexChannel() {
		List<NewChannel> result = new ArrayList<NewChannel>();
		List<Channel> cList = channelDao.listAllIndexChannel();
		for (Channel parent : cList) {
			NewChannel nc = new NewChannel();
			nc.setParent(parent);
			nc.setChild(channelDao.listByParent(parent.getId()));
			result.add(nc);
		}
		return result;

	}

	@Override
	public Channel loadFirstChannelByNav(int cid) {
		return channelDao.loadFirstChannelByNav(cid);
	}

	@Override
	public List<Channel> listUseChannelByParent(Integer cid) {
		return channelDao.listUseChannelByParent(cid);
	}

	@Override
	public List<Channel> listChannelByType(ChannelType ct) {
		return channelDao.listChannelByType(ct);
	}

	public static void main(String[] args) {
		System.out.println(ChannelType.NAV_CHANNEL.ordinal());
	}

}
