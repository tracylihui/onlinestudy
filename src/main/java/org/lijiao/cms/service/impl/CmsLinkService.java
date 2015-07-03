package org.lijiao.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.lijiao.basic.model.Pager;
import org.lijiao.cms.dao.ICmsLinkDao;
import org.lijiao.cms.model.CmsLink;
import org.lijiao.cms.service.ICmsLinkService;
import org.springframework.stereotype.Service;

@Service("cmsLinkService")
public class CmsLinkService implements ICmsLinkService {
	private ICmsLinkDao cmsLinkDao;
	
	

	public ICmsLinkDao getCmsLinkDao() {
		return cmsLinkDao;
	}

	@Inject
	public void setCmsLinkDao(ICmsLinkDao cmsLinkDao) {
		this.cmsLinkDao = cmsLinkDao;
	}

	@Override
	public void add(CmsLink cl) {
		cmsLinkDao.add(cl);
	}

	@Override
	public void delete(int id) {
		cmsLinkDao.delete(id);
	}

	@Override
	public void update(CmsLink cl) {
		cmsLinkDao.update(cl);
	}

	@Override
	public CmsLink load(int id) {
		return cmsLinkDao.load(id);
	}

	@Override
	public Pager<CmsLink> findByType(String type) {
		return cmsLinkDao.findByType(type);
	}

	@Override
	public List<CmsLink> listByType(String type) {
		return cmsLinkDao.listByType(type);
	}

	@Override
	public List<String> listAllType() {
		return cmsLinkDao.listAllType();
	}

	@Override
	public Map<String, Integer> getMinAndMaxPos() {
		return cmsLinkDao.getMinAndMaxPos();
	}

	@Override
	public void updatePos(int id, int oldPos, int newPos) {
		cmsLinkDao.updatePos(id, oldPos, newPos);
	}

	@Override
	public List<CmsLink> listAllLink() {
		return cmsLinkDao.listAllLink();
	}

}
